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

import com.flowlogix.website.JunkMailEraserLocal;

import com.flowlogix.website.services.security.UserAuth;
import javax.ejb.Local;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;

@Stateless
@Local(JunkMailEraserLocal.class)
public class JunkMailEraserImpl implements JunkMailEraserLocal
{
    @SneakyThrows(MessagingException.class)
    @Override
    public void erase()
    {
        @Cleanup Store store = mailSession.getStore();
        log.fine(mailSession.getProperties().toString());
        UserAuth user = (UserAuth)SecurityUtils.getSubject().getPrincipal();
        store.connect(user.getUserName(), user.getPassword());
        Folder junkFolder = store.getFolder("Junk");
        junkFolder.open(Folder.READ_WRITE);
        Message[] messages = junkFolder.getMessages();
        for (Message msg : messages)
        {
            msg.setFlag(Flag.DELETED, true);
        }
        junkFolder.close(true);
    }

    
    @Override
    public boolean isMock()
    {
        return false;
    }
    
    
    @Resource(name = "mail/HopeMail")
    private Session mailSession;
    private static final Logger log = Logger.getLogger(JunkMailEraserImpl.class.getName());
}
