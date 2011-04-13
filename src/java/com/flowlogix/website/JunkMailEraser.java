package com.flowlogix.website;

import javax.ejb.Local;

@Local
public interface JunkMailEraser 
{
	public void erase();
	public boolean isMock();
	
	public static final String NAME = JunkMailEraser.class.getSimpleName();
}
