package org.dav.service.view.table.renderer;

import org.dav.service.data.DataUtils;
import org.dav.service.settings.type.Password;
import org.dav.service.view.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PasswordCellRenderer extends DefaultTableCellRenderer
{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (value != null)
		{
			String valueClassName = value.getClass().getSimpleName();
			
			if (Constants.CLASS_NAME_PASSWORD.equals(valueClassName))
			{
				Password password = (Password) value;
				
				label.setText(DataUtils.toAsterisks(password.getSecret()));
			}
		}
		
		return label;
	}
}
