package org.dav.service.settings;

import org.dav.service.settings.parameter.ParameterHeader;
import org.dav.service.settings.type.Password;
import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;

public class DatabaseSettings extends TransmissiveSettings
{
	private static final int PARAM_COUNT = 7;

	public DatabaseSettings(ResourceManager resourceManager) throws Exception
	{
		super(resourceManager);

		headers = new ParameterHeader[PARAM_COUNT];

		headers[0] = new ParameterHeader(Constants.KEY_PARAM_DB_DRIVER, String.class, "");
		headers[1] = new ParameterHeader(Constants.KEY_PARAM_DB_CONN_PREF, String.class, "");
		headers[2] = new ParameterHeader(Constants.KEY_PARAM_DB_HOST, String.class, "");
		headers[3] = new ParameterHeader(Constants.KEY_PARAM_DB_PORT, Integer.class, Integer.valueOf(0));
		headers[4] = new ParameterHeader(Constants.KEY_PARAM_DB_CATALOG, String.class, "");
		headers[5] = new ParameterHeader(Constants.KEY_PARAM_DB_USER, String.class, "");
		headers[6] = new ParameterHeader(Constants.KEY_PARAM_DB_PASSWORD, Password.class, new Password(""));

		init();
	}

	@Override
	public void save() throws Exception
	{
		SettingsManager.setStringValue(headers[0].getKeyString(), getDriverName());
		SettingsManager.setStringValue(headers[1].getKeyString(), getConnectionPrefix());
		SettingsManager.setStringValue(headers[2].getKeyString(), getHost());
		SettingsManager.setIntValue(headers[3].getKeyString(), getPort());
		SettingsManager.setStringValue(headers[4].getKeyString(), getCatalog());
		SettingsManager.setStringValue(headers[5].getKeyString(), getUserName());
		SettingsManager.setStringValue(headers[6].getKeyString(), getPassword().getSecret());

		SettingsManager.saveSettings(resourceManager.getConfig());
	}

	public String getDriverName()
	{
		return ((String) paramMap.get(headers[0].getKeyString()).getValue());
	}

	public String getConnectionPrefix()
	{
		return ((String) paramMap.get(headers[1].getKeyString()).getValue());
	}

	public String getHost()
	{
		return ((String) paramMap.get(headers[2].getKeyString()).getValue());
	}

	public int getPort()
	{
		return ((Integer) paramMap.get(headers[3].getKeyString()).getValue());
	}

	public String getCatalog()
	{
		return ((String) paramMap.get(headers[4].getKeyString()).getValue());
	}

	public String getUserName()
	{
		return ((String) paramMap.get(headers[5].getKeyString()).getValue());
	}

	public Password getPassword()
	{
		return ((Password) paramMap.get(headers[6].getKeyString()).getValue());
	}
}
