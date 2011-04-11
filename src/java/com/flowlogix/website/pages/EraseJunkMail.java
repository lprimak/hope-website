package com.flowlogix.website.pages;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.SneakyThrows;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageReset;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Secure;

import com.flowlogix.website.JunkMailEraser;

@Secure
public class EraseJunkMail
{
	@SneakyThrows(NamingException.class)
	public EraseJunkMail() 
	{
		final InitialContext ic = new InitialContext();
		final String eraserName = "java:global/hope_website/" + JunkMailEraser.NAME;
		JunkMailEraser eraser = (JunkMailEraser)ic.lookup(eraserName);
		try
		{
			eraser.testConnection();
		}
		catch(EJBException e)
		{
			eraser = (JunkMailEraser)ic.lookup(eraserName + "Mock");	
		}
		this.eraser = eraser;
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
    
    
    @SuppressWarnings("unused")
	@PageReset
    private void pageReset()
    {
    	if(junkMailErased != null)
    	{
    		junkMailErased = null;
    	}
    }
    
    
    public String getJunkMailErased()
    {
    	if(junkMailErased == null)
    	{
    		return "<None>";
    	}
    	else
    	{
    		return junkMailErased;
    	}
    }

    
    @Persist("flash")
    private String junkMailErased;    
    private final JunkMailEraser eraser;
}    
