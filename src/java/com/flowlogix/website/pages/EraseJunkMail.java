package com.flowlogix.website.pages;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.Getter;
import lombok.SneakyThrows;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.annotations.SetupRender;

import com.flowlogix.website.JunkMailEraser;

@Secure
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
    @OnEvent(component="eraseJunkMail", value="clicked")
    private void eraseJunkMail()
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
    }
        
    
    @Persist("flash")
    @Getter private String junkMailErased;    
    private final JunkMailEraser eraser;
}    
