package com.flowlogix.website;

import javax.ejb.Local;

@Local
public interface JunkMailEraserLocal 
{
	public void erase();
	public boolean isMock();
}
