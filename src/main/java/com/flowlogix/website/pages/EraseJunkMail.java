package com.flowlogix.website.pages;

import lombok.Getter;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.corelib.components.Zone;

import com.flowlogix.website.JunkMailEraserLocal;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;


@Secure
public class EraseJunkMail
{
    @SetupRender
    void init()
    {
        if(junkMailErased == null)
        {
            junkMailErased = "<None>";
        }
    }
	
	
    @SuppressWarnings("unused")
    @OnEvent(value="erase")
    private Object eraseJunkMail()
    {    	
    	eraser.erase();
    	if(eraser.isMock())
    	{
    		junkMailErased = "Erased Mock!";
    	}
    	else
    	{
    		junkMailErased = "Erased!";
    	}
        if(request.isXHR())
        {
            cr.discardPersistentFieldChanges();
            return junkStatus.getBody();
        }
        else
        {
            return this;
        }
    }
    
    
    @SuppressWarnings("unused")
    @OnEvent(value="updatestatus", component="junkStatus")
    private Block updateJunkStatus()
    {
        init();
        return junkStatus.getBody();
    }
   
    
    @Getter @Persist("flash") private String junkMailErased;  
    @Inject private JunkMailEraserLocal eraser;
    @InjectComponent private Zone junkStatus;
    @Inject private Request request;
    @Inject private ComponentResources cr;
}
