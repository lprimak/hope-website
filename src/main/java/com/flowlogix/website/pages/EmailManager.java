package com.flowlogix.website.pages;

import com.flowlogix.web.services.annotations.AJAX;
import lombok.Getter;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.corelib.components.Zone;

import com.flowlogix.website.JunkMailEraserLocal;
import com.flowlogix.website.services.HopeModule;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Request;


@Secure
@RequiresPermissions({"mail:junk:erase", "mail:draft:send"})
public class EmailManager
{
    public EmailManager()
    {
        JunkMailEraserLocal _eraser = eraserImpl;
        try
        {
            eraserImpl.isMock();            
        }
        catch(EJBException e)
        {
            _eraser = eraserMock;
        }
        this.eraser = _eraser;
    }
    
    
    @SetupRender
    void init()
    {
        if(emailStatus == null)
        {
            emailStatus = "<None>";
        }
    }
	
	
    @SuppressWarnings("unused")
    @OnEvent(value = "erase")
    @AJAX(discardAfter = true, requireSession = false)
    private Block eraseJunkMail()
    {
        eraser.erase(junkFolderName);
        final String junkErasedMessage = "Erased Junk Mail";
        if (eraser.isMock())
        {
            emailStatus = junkErasedMessage + " (Mock)";
        } 
        else
        {
            emailStatus = junkErasedMessage;
        }
        return status.getBody();
    }

    
    @SuppressWarnings("unused")
    @OnEvent(value="updatestatus", component="status")
    private Block updateStatus()
    {
        init();
        return status.getBody();
    }
    
    
    @OnEvent(value = "logout")
    private void logout()
    {
        SecurityUtils.getSubject().logout();
    }
   
    
    @Getter @Persist(PersistenceConstants.FLASH) private String emailStatus;  
    @EJB(beanName = "JunkMailEraserImpl") private JunkMailEraserLocal eraserImpl;
    @EJB(beanName = "JunkMailEraserMock") private JunkMailEraserLocal eraserMock;
    private final JunkMailEraserLocal eraser;
    @InjectComponent private Zone status;
    @Inject private Request request;
    @Inject private ComponentResources cr;
    private @Inject @Symbol(HopeModule.JUNK_FOLDER_NAME) String junkFolderName;
}
