package org.dav.service.settings;

import org.dav.service.settings.parameter.ParameterHeader;
import org.dav.service.util.Constants;
import org.dav.service.util.ResourceManager;

import java.awt.*;
import java.util.Locale;

public class ViewSettings extends TransmissiveSettings
{
	private static final int PARAM_COUNT = 1;
	
    private boolean mainWindowMaximized;
    private Point mainWindowPosition;
    private Dimension mainWindowSize;
    
    private Dimension mainWindowPreferredSize;
    
    public ViewSettings(ResourceManager resourceManager, Dimension mainWindowPreferredSize) throws Exception
	{
		super(resourceManager);
		
		headers = new ParameterHeader[PARAM_COUNT];
		headers[0] = new ParameterHeader(Constants.KEY_PARAM_APP_LOCALE, Locale.class, resourceManager.getCurrentLocale());
		
		this.mainWindowPreferredSize = mainWindowPreferredSize;
		
		mainWindowMaximized = false;
		mainWindowPosition = new Point(0, 0);
		mainWindowSize = new Dimension(this.mainWindowPreferredSize);
		
		init();
	}
	
	@Override
    public void load() throws Exception
    {
        super.load();
        
		loadMainWindowMaximized();
		loadMainWindowPosition();
		loadMainWindowSize();
	}
	
	@Override
    public void save() throws Exception
    {
		SettingsManager.setStringValue(headers[0].getKeyString(), getAppLocale().toString());

    	SettingsManager.setStringValue(Constants.KEY_PARAM_MAIN_WIN_MAXIMIZED, String.valueOf(mainWindowMaximized));

    	SettingsManager.setIntValue(Constants.KEY_PARAM_MAIN_WIN_X, mainWindowPosition.x);
    	SettingsManager.setIntValue(Constants.KEY_PARAM_MAIN_WIN_Y, mainWindowPosition.y);

    	SettingsManager.setIntValue(Constants.KEY_PARAM_MAIN_WIN_WIDTH, mainWindowSize.width);
    	SettingsManager.setIntValue(Constants.KEY_PARAM_MAIN_WIN_HEIGHT, mainWindowSize.height);

    	SettingsManager.saveSettings(resourceManager.getConfig());
    }
		
	private void loadMainWindowMaximized()
	{
		String maximizedString = SettingsManager.getStringValue(Constants.KEY_PARAM_MAIN_WIN_MAXIMIZED);
		
		if (Constants.MESS_TRUE.equalsIgnoreCase(maximizedString))
			mainWindowMaximized = true;
		else
			mainWindowMaximized = false;
	}
	
	private void loadMainWindowPosition()
	{
		int x = 0;
		if (SettingsManager.hasValue(Constants.KEY_PARAM_MAIN_WIN_X))
			x = SettingsManager.getIntValue(Constants.KEY_PARAM_MAIN_WIN_X, x);
		
		int y = 0;
		if (SettingsManager.hasValue(Constants.KEY_PARAM_MAIN_WIN_Y))
			y = SettingsManager.getIntValue(Constants.KEY_PARAM_MAIN_WIN_Y, y);
		
		mainWindowPosition = new Point(x, y);
	}
	
	private void loadMainWindowSize()
	{
		int width = 0;
		if (SettingsManager.hasValue(Constants.KEY_PARAM_MAIN_WIN_WIDTH))
			width = SettingsManager.getIntValue(Constants.KEY_PARAM_MAIN_WIN_WIDTH, width);
		
		int height = 0;
		if (SettingsManager.hasValue(Constants.KEY_PARAM_MAIN_WIN_HEIGHT))
			height = SettingsManager.getIntValue(Constants.KEY_PARAM_MAIN_WIN_HEIGHT, height);
		
		if (width > 0 && height > 0)
			mainWindowSize = new Dimension(width, height);
		else
			mainWindowSize = mainWindowPreferredSize;
	}

    public Locale getAppLocale()
    {
		return ((Locale) paramMap.get(headers[0].getKeyString()).getValue());
    }
    
    public boolean isMainWindowMaximized()
    {
        return mainWindowMaximized;
    }

    public Point getMainWindowPosition()
    {
        return mainWindowPosition;
    }

    public Dimension getMainWindowSize()
    {
        return mainWindowSize;
    }

	public void setMainWindowMaximized(boolean mainWindowMaximized)
	{
		this.mainWindowMaximized = mainWindowMaximized;
	}

	public void setMainWindowPosition(Point mainWindowPosition)
	{
		this.mainWindowPosition = mainWindowPosition;
	}

	public void setMainWindowSize(Dimension mainWindowSize)
	{
		this.mainWindowSize = mainWindowSize;
	}
}
