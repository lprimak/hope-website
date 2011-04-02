package com.flowlogix.website.pages;

import lombok.Getter;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;

public class EraseJunkMail
{
    @SuppressWarnings("unused")
    @OnEvent(component="eraseJunkMail", value="clicked")
    private void eraseJunkMail()
    {
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

    
    @Persist("flash")
    @Getter private String junkMailErased;
}
