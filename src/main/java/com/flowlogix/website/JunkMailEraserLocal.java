package com.flowlogix.website;

import javax.ejb.Local;

@Local
public interface JunkMailEraserLocal 
{
	public void erase(String folderName);
	public boolean isMock();
}
