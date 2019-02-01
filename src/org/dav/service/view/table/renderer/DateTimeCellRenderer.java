package org.dav.service.view.table.renderer;

import org.dav.service.util.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeCellRenderer extends DefaultTableCellRenderer
{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (value != null)
		{
			String valueClassName = value.getClass().getSimpleName();
			
			if (Constants.CLASS_NAME_LOCALDATETIME.equals(valueClassName))
			{
				LocalDateTime time = (LocalDateTime) value;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
				
				setText(time.format(formatter));
			}
		}
		
		return this;
	}
}
