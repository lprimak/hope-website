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
    public int sendDrafts(String draftFolderName, String sentFolderName)
    {
        return 0;
    }
    
    
    @Override
    public boolean isMock()
    {
        return true;
    }
}
