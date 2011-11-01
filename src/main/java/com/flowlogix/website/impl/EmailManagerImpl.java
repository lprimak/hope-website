package com.flowlogix.website.impl;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.flowlogix.website.EmailManagerLocal;

import com.flowlogix.website.services.security.UserAuth;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Local;
import javax.mail.Address;
import javax.mail.Transport;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;

@Stateless
@Local(EmailManagerLocal.class)
public class EmailManagerImpl implements EmailManagerLocal
{
    @SneakyThrows(MessagingException.class)
    @Override
    public void eraseFolder(String folderName)
    {
        @Cleanup Folder folder = new Folder(folderName, javax.mail.Folder.READ_WRITE);
        for (Message msg : folder.getFolder().getMessages())
        {
            msg.setFlag(Flag.DELETED, true);
        }
    }
    
    
    @Override
    @SneakyThrows(MessagingException.class)
    public int sendDrafts(String draftFolderName)
    {
        @Cleanup Folder folder = new Folder(draftFolderName, javax.mail.Folder.READ_WRITE);
        @Cleanup Transport transport = mailSession.getTransport("smtp");
        int numSent = 0;
        for(Message msg : folder.getFolder().getMessages())
        {
            List<Address> addrs = new LinkedList<Address>();
            addAddresses(addrs, msg, Message.RecipientType.TO);
            addAddresses(addrs, msg, Message.RecipientType.CC);
            addAddresses(addrs, msg, Message.RecipientType.BCC);
            Address[] addrArr = new Address[addrs.size()];
            transport.sendMessage(msg, addrs.toArray(addrArr));
            msg.setFlag(Flag.DELETED, true);
            ++numSent;
        }
        return numSent;
    }

    
    @Override
    public boolean isMock()
    {
        return false;
    }

    
    private void addAddresses(List<Address> addrs, Message msg, RecipientType type) throws MessagingException
    {
        Address[] addrArray = msg.getRecipients(type);
        if(addrArray != null)
        {
            addrs.addAll(Arrays.asList(addrArray));
        }
    }
    
    
    private class Folder
    {
        public Folder(String folderName, int options) throws MessagingException
        {
            store = mailSession.getStore();
            try
            {
                log.fine(mailSession.getProperties().toString());
                UserAuth user = (UserAuth) SecurityUtils.getSubject().getPrincipal();
                store.connect(user.getUserName(), user.getPassword());
                folder = store.getFolder(folderName);
                folder.open(options);
            }
            catch(MessagingException e)
            {
                store.close();
                throw e;
            }
        }
        
        
        public void close() throws MessagingException
        {
            folder.close(true);
            store.close();
        }
        
        
        private @Getter final javax.mail.Folder folder;
        private final Store store;
    }
    
    
    @Resource(name = "mail/HopeMail")
    private Session mailSession;
    private static final Logger log = Logger.getLogger(EmailManagerImpl.class.getName());
}
