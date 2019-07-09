package org.dav.service.view.table.renderer;

import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class LocaleValueComboRenderer extends JLabel implements ListCellRenderer
{
	private ResourceManager resourceManager;

	public LocaleValueComboRenderer(ResourceManager resourceManager)
	{
		this.resourceManager = resourceManager;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

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
