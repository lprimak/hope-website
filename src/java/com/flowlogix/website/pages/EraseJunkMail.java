package com.flowlogix.website.pages;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.Getter;
import lombok.SneakyThrows;

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
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Secure
@Import(library="context:scripts/DeferUpdate.js")
public class EraseJunkMail
{
	@SneakyThrows(NamingException.class)
	public EraseJunkMail() 
	{
		final InitialContext ic = new InitialContext();
		final String eraserName = "java:module/" + JunkMailEraser.NAME;
		JunkMailEraser eraser = (JunkMailEraser)ic.lookup(eraserName);
		try
		{
			// call any method to actually test the bean
			eraser.isMock();
		}
		catch(EJBException e)
		{
			eraser = (JunkMailEraser)ic.lookup(eraserName + "Mock");	
		}
		this.eraser = eraser;
	}
	

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
    private final JunkMailEraser eraser;
    @InjectComponent private Zone junkStatus;
    private @Environmental JavaScriptSupport js;
}
