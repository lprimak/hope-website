package com.flowlogix.website.impl;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.flowlogix.website.EmailManagerLocal;

import com.flowlogix.website.services.security.UserAuth;
import javax.ejb.Local;
import lombok.Cleanup;
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
        @Cleanup Store store = mailSession.getStore();
        log.fine(mailSession.getProperties().toString());
        UserAuth user = (UserAuth)SecurityUtils.getSubject().getPrincipal();
        store.connect(user.getUserName(), user.getPassword());
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        Message[] messages = folder.getMessages();
        for (Message msg : messages)
        {
            msg.setFlag(Flag.DELETED, true);
        }
        folder.close(true);
    }
    
    
    @Override
    public int sendDrafts(String draftFolderName)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public boolean isMock()
    {
        return false;
    }
    
    
    @Resource(name = "mail/HopeMail")
    private Session mailSession;
    private static final Logger log = Logger.getLogger(EmailManagerImpl.class.getName());
}
