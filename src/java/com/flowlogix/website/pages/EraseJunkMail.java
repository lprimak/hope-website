package com.flowlogix.website.pages;

import lombok.Getter;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;

public class EraseJunkMail
{
    @SuppressWarnings("unused")
    @OnEvent(component="eraseJunkMail", value="clicked")
    private void eraseJunkMail()
    {
        junkMailErased = "yes!";
    }

    
    @SuppressWarnings("unused")
    @OnEvent(component="reset", value="clicked")
    private void resetJunkMail()
    {
        junkMailErased = "no!";
    }

    
    
    @Persist
    @Getter private String junkMailErased;
}
