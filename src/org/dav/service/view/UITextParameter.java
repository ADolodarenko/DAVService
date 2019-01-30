package org.dav.service.view;

public class UITextParameter
{
	private String key;
	private Title value;

	public UITextParameter(String key, Title value)
	{
		this.key = key;
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public Title getValue()
	{
		return value;
	}
}
