package com.flowlogix.website.impl;

import javax.ejb.Stateless;

import com.flowlogix.website.JunkMailEraser;
import javax.ejb.Local;

@Stateless
@Local(JunkMailEraser.class)
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
