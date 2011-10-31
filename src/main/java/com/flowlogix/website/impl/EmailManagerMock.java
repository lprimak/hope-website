package com.flowlogix.website.impl;

import javax.ejb.Stateless;

import com.flowlogix.website.EmailManagerLocal;


@Stateless
public class EmailManagerMock implements EmailManagerLocal
{
    @Override
    public void eraseFolder(String folderName)
    {
        // just a fake test
    }

    
    @Override
    public void sendDrafts(String draftFolderName)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Override
    public boolean isMock()
    {
        return true;
    }
}
