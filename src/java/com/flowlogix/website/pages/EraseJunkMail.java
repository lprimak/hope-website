package com.flowlogix.website.pages;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.SneakyThrows;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Secure;

import com.flowlogix.website.impl.JunkMailEraser;

@Secure
public class EraseJunkMail
{
	@SneakyThrows(NamingException.class)
	public EraseJunkMail() 
	{
		eraser = (JunkMailEraser)new InitialContext().lookup("java:global/hope_website/JunkMailEraser");
	}
	
	
    @SuppressWarnings("unused")
    @OnEvent(component="eraseJunkMail", value="clicked")
    private void eraseJunkMail()
    {    	
    	eraser.erase();
        junkMailErased = "Erased!";
    }
    
    
    @SuppressWarnings("unused")
	@AfterRender
    private void afterRender()
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
