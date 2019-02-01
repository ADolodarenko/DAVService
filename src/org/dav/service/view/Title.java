package org.dav.service.view;

import org.dav.service.util.Constants;
import org.dav.service.util.ResourceManager;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A title for a visual component. Returns a string for the component to display using a key string and a resource bundle.
 */
public class Title
{
	private ResourceManager manager;
	private String key;
	private List<Object> params;
	
	public static String getTitleString(ResourceBundle bundle, String titleKey)
	{
		if (bundle == null)
			throw new IllegalArgumentException(Constants.EXCPT_RESOURCE_BUNDLE_EMPTY);

		if (titleKey == null)
			throw new IllegalArgumentException(Constants.EXCPT_TITLE_KEY_EMPTY);

		String result = null;
		
		try
		{
			result = bundle.getString(titleKey);
		}
		catch (MissingResourceException e)
		{}
		
		return result;
	}
	
	public Title(ResourceManager manager, String key)
	{
		if (manager == null)
			throw new IllegalArgumentException(Constants.EXCPT_RESOURCE_MANAGER_EMPTY);

		if (key == null)
			throw new IllegalArgumentException(Constants.EXCPT_TITLE_KEY_EMPTY);

		this.manager = manager;
		this.key = key;
		this.params = new LinkedList<>();
	}
	
	public Title(ResourceManager manager, String key, Object... params)
	{
		this(manager, key);
		
		for (Object param : params)
			this.params.add(param);
	}
	
	public String getKey()
	{
		return key;
	}
	
	public String getText()
	{
		String text = getTitleString(manager.getBundle(), key);
		
		if (text == null)
			return key;
		else if (params.isEmpty())
			return text;
		else
			return String.format(text, params.toArray());
	}
}
