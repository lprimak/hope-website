package com.flowlogix.website.impl;

import javax.ejb.Stateless;

import com.flowlogix.website.JunkMailEraserLocal;


@Stateless
public class JunkMailEraserMock implements JunkMailEraserLocal
{
    @Override
    public void erase(String folderName)
    {
        // just a fake test
    }

    
    @Override
    public boolean isMock()
    {
        return true;
    }
}
