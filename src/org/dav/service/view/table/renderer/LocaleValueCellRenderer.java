package org.dav.service.view.table.renderer;

import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Locale;

public class LocaleValueCellRenderer extends DefaultTableCellRenderer
{
	private ResourceManager resourceManager;
	
	public LocaleValueCellRenderer(ResourceManager resourceManager)
	{
		this.resourceManager = resourceManager;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (value != null)
		{
			String valueClassName = value.getClass().getSimpleName();
			
			if (Constants.CLASS_NAME_LOCALE.equals(valueClassName))
			{
				Locale locale = (Locale) value;
				
				label.setText(locale.getDisplayName(resourceManager.getCurrentLocale()));
				
				String country = locale.getCountry();
				
				if (country.equalsIgnoreCase("RU"))
					label.setIcon(resourceManager.getImageIcon(Constants.ICON_NAME_RUS));
				else if (country.equalsIgnoreCase("US"))
					label.setIcon(resourceManager.getImageIcon(Constants.ICON_NAME_USA));
			}
		}
		
		return label;
	}
}
