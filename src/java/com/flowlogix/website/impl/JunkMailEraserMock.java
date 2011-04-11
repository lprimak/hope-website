package com.flowlogix.website.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.flowlogix.website.JunkMailEraser;

@Stateless
@Local
public class JunkMailEraserMock implements JunkMailEraser 
{
	@Override
	public void erase() 
	{
		// just a fake test
	}
	
	
	@Override
	public void testConnection()
	{
		// blank
	}
	
	
	@Override
	public boolean isMock() 
	{
		return true;
	}
}
