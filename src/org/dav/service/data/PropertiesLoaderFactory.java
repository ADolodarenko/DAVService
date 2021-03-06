package org.dav.service.data;

import org.dav.service.exceptions.WrongParametersException;

/**
 * This class is a factory for loading properties.
 */
public class PropertiesLoaderFactory
{
	public static PropertiesLoader getInstance(DataSourceType type, String argument) throws WrongParametersException
	{
		switch (type)
		{
			case FILE:
				return new FilePropertiesLoader(argument);
			default:
				return null;
		}
	}
}
