package org.dav.service.view.table.renderer;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.Charset;

public class CharsetComboRenderer extends JLabel implements ListCellRenderer
{
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value != null && (value instanceof Charset))
		{
			Charset charset = (Charset) value;

			label.setText(charset.displayName());
		}

		return label;
	}
}
