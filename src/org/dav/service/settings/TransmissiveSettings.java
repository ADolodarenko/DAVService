package org.dav.service.settings;

import org.dav.service.settings.parameter.Parameter;
import org.dav.service.settings.parameter.ParameterHeader;
import org.dav.service.settings.type.Password;
import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;
import org.dav.service.view.Title;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

public abstract class TransmissiveSettings implements Settings
{
	protected ResourceManager resourceManager;
	protected Map<String, Parameter> paramMap;
	protected ParameterHeader[] headers;

	protected TransmissiveSettings(ResourceManager resourceManager)
	{
		if (resourceManager == null)
			throw new IllegalArgumentException(Constants.EXCPT_RESOURCE_MANAGER_EMPTY);

		this.resourceManager = resourceManager;
		paramMap = new HashMap<>();
	}

	@Override
	public void init() throws Exception
	{
		setParameters(false);
	}

	@Override
	public void init(Settings settings) throws Exception
	{
		if (settings == null)
			throw new IllegalArgumentException(Constants.EXCPT_SETTINGS_EMPTY);

		if ( !getClass().equals(settings.getClass()) )
			throw new IllegalArgumentException(Constants.EXCPT_SETTINGS_WRONG);

		TransmissiveSettings thatSettings = (TransmissiveSettings) settings;

		setParameters(thatSettings);
	}

	@Override
	public void load() throws Exception
	{
		setParameters(true);
	}

	private void setParameters(boolean load) throws Exception
	{
		if (load)
			SettingsManager.loadSettings(resourceManager.getConfig());

		for (int i = 0; i < headers.length; i++)
			setParameter(load, headers[i]);
	}

	private void setParameters(TransmissiveSettings settings) throws Exception
	{
		for (int i = 0; i < headers.length; i++)
			setParameter(headers[i], settings);
	}

	protected void setParameter(boolean load, ParameterHeader header) throws Exception
	{
		if (load)
			paramMap.put(header.getKeyString(), getParameter(header));
		else
			paramMap.put(header.getKeyString(), initParameter(header));
	}

	protected void setParameter(ParameterHeader header, TransmissiveSettings settings) throws Exception
	{
		paramMap.put(header.getKeyString(), getParameterCopy(header, settings));
	}

	private Parameter initParameter(ParameterHeader header) throws Exception
	{
		String keyString = header.getKeyString();
		Title key = new Title(resourceManager, keyString);

		Class<?> cl = header.getType();

		Object value = header.getInitialValue();

		return new Parameter(key, value, cl);
	}

	private Parameter getParameterCopy(ParameterHeader header, TransmissiveSettings settings) throws Exception
	{
		String keyString = header.getKeyString();
		Class<?> cl = header.getType();

		Title key = new Title(resourceManager, keyString);

		Parameter parameter = settings.paramMap.get(keyString);

		if (parameter == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_EMPTY);

		if ( !cl.equals(parameter.getType()) )
			throw new IllegalArgumentException(Constants.EXCPT_VALUE_TYPE_WRONG);

		Object rawValue = parameter.getValue();

		if (rawValue == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_VALUE_EMPTY);

		if ( !cl.isAssignableFrom(rawValue.getClass()) )
			throw new IllegalArgumentException(Constants.EXCPT_VALUE_TYPE_WRONG);

		Object value = null;
		String className = cl.getSimpleName();

		if (Constants.CLASS_NAME_BOOLEAN.equals(className))
			value = new Boolean(((Boolean) rawValue).booleanValue());
		else if (Constants.CLASS_NAME_INTEGER.equals(className))
			value = new Integer(((Integer) rawValue).intValue());
		else if (Constants.CLASS_NAME_DOUBLE.equals(className))
			value = new Double(((Double) rawValue).doubleValue());
		else if (Constants.CLASS_NAME_STRING.equals(className))
			value = rawValue;
		else if (Constants.CLASS_NAME_LOCALE.equals(className))
			value = ((Locale) rawValue).clone();
		else if (Constants.CLASS_NAME_FILE.equals(className))
			value = new File(((File) rawValue).getAbsolutePath());
		else if (Constants.CLASS_NAME_PASSWORD.equals(className))
			value = new Password(((Password) rawValue).getKey());
		else if (Constants.CLASS_NAME_CHARSET.equals(className))
			value = Charset.forName(((Charset) rawValue).name());

		if (value == null)
			throw new Exception(Constants.EXCPT_VALUE_TYPE_WRONG);

		return new Parameter(key, value, cl);
	}

	private Parameter getParameter(ParameterHeader header) throws Exception
	{
		String keyString = header.getKeyString();
		Class<?> cl = header.getType();

		Title key = new Title(resourceManager, keyString);
		Object value = null;

		String className = cl.getSimpleName();

		if (Constants.CLASS_NAME_BOOLEAN.equals(className))
			value = SettingsManager.getBooleanValue(keyString);
		else if (Constants.CLASS_NAME_INTEGER.equals(className))
		{
			int defaultValue = ((Integer) header.getInitialValue()).intValue();

			value = SettingsManager.getIntValue(keyString, defaultValue);
		}
		else if (Constants.CLASS_NAME_DOUBLE.equals(className))
		{
			double defaultValue = ((Double) header.getInitialValue()).doubleValue();

			value = SettingsManager.getDoubleValue(keyString, defaultValue);
		}
		else if (Constants.CLASS_NAME_STRING.equals(className))
		{
			value = SettingsManager.getStringValue(keyString);

			if (value == null)
				value = "";
		}
		else if (Constants.CLASS_NAME_LOCALE.equals(className))
		{
			String localeName = SettingsManager.getStringValue(keyString);

			value = findLocale(localeName);

			if (value == null)
				value = resourceManager.getCurrentLocale();
		}
		else if (Constants.CLASS_NAME_FILE.equals(className))
		{
			String fileName = SettingsManager.getStringValue(keyString);

			if (fileName == null)
				fileName = Constants.MESS_CURRENT_PATH;

			value = new File(fileName);
		}
		else if (Constants.CLASS_NAME_PASSWORD.equals(className))
		{
			String secret = SettingsManager.getStringValue(keyString);

			value = new Password(secret);
		}
		else if (Constants.CLASS_NAME_CHARSET.equals(className))
		{
			String charsetName = SettingsManager.getStringValue(keyString);

			if (charsetName != null && !charsetName.isEmpty())
				value = Charset.forName(charsetName);

			if (value == null)
				value = Charset.defaultCharset();
		}

		if (value == null)
			throw new Exception(Constants.EXCPT_VALUE_TYPE_WRONG);

		return new Parameter(key, value, cl);
	}

	private Locale findLocale(String localeName)
	{
		if (localeName == null || localeName.isEmpty())
			return null;

		for (Locale locale : resourceManager.getAvailableLocales())
			if (locale.toString().equals(localeName))
				return locale;

		return null;
	}

	public List<Parameter> getParameterList()
	{
		return new ArrayList<>(paramMap.values());
	}
}
