package org.dav.service.settings;

import org.dav.service.util.Constants;

import java.io.*;
import java.util.Properties;

public class SettingsManager
{
	private static Properties properties = new Properties();

	public static boolean hasValue(String key)
	{
		return properties.containsKey(key);
	}

	public static String getStringValue(String key)
	{
		return properties.getProperty(key);
	}

	public static void setStringValue(String key, String value)
	{
		properties.setProperty(key, value);
	}

	public static int getIntValue(String key, int defaultValue) throws NumberFormatException
	{
		String stringValue = getStringValue(key);

		if (stringValue != null)
			return Integer.parseInt(stringValue);
		else
			return defaultValue;
	}

	public static void setIntValue(String key, int value)
	{
		setStringValue(key, String.valueOf(value));
	}

	public static Boolean getBooleanValue(String key)
	{
		return Boolean.parseBoolean(getStringValue(key));
	}

	public static void setBooleanValue(String key, boolean value)
	{
		setStringValue(key, String.valueOf(value));
	}

	public static Double getDoubleValue(String key, double defaultValue) throws NumberFormatException
	{
		String stringValue = getStringValue(key);

		if (stringValue != null)
			return Double.parseDouble(stringValue);
		else
			return defaultValue;
	}

	public static void setDoubleValue(String key, double value)
	{
		setStringValue(key, String.valueOf(value));
	}

	public static void loadSettings(File configFile) throws IOException
	{
		try (InputStream input = new FileInputStream(configFile))
		{
			properties.load(input);
		}
	}

	public static void saveSettings(File configFile) throws IOException
	{
		try (OutputStream output = new FileOutputStream(configFile))
		{
			properties.store(output, Constants.MESS_SETTINGS_DESCRIPTION);
		}
	}
}
