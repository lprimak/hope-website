package com.flowlogix.website.pages;

import lombok.Getter;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;

import com.flowlogix.website.JunkMailEraser;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Secure
@Import(library="context:scripts/DeferUpdate.js")
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
    private Block eraseJunkMail()
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
    	return junkStatus.getBody();
    }
    
    
    @OnEvent(value="updatestatus", component="junkStatus")
    private Block updateJunkStatus()
    {
        init();
        return junkStatus.getBody();
    }
    
    
    @AfterRender
    void addStatusReset()
    {
        js.addScript("new DeferUpdate('%s');", junkStatus.getClientId());
    }

    
    @Getter private String junkMailErased;  
    @Inject private JunkMailEraser eraser;
    @InjectComponent private Zone junkStatus;
    private @Environmental JavaScriptSupport js;
}
