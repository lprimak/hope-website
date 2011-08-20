package com.flowlogix.website.impl;

import javax.ejb.Stateless;

import com.flowlogix.website.JunkMailEraserLocal;
import javax.ejb.Local;


@Stateless
@Local(JunkMailEraserLocal.class)
public class JunkMailEraserMock implements JunkMailEraserLocal
{
    @Override
    public void erase()
    {
        // just a fake test
    }

    
    @Override
    public boolean isMock()
    {
        return true;
    }
}
