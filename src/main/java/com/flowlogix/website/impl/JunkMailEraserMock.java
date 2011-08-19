package com.flowlogix.website.impl;

import javax.ejb.Stateless;

import com.flowlogix.website.JunkMailEraser;

@Stateless
public class JunkMailEraserMock implements JunkMailEraser 
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
