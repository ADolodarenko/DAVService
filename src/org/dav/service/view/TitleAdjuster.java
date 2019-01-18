package org.dav.service.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to adjust components with titles.
 */
public class TitleAdjuster
{
	private Map<Component, Title> titledComponents;
	
	public TitleAdjuster()
	{
		titledComponents = new HashMap<>();
	}
	
	public void setComponentTitle(Component component, Title title)
	{
		if (component == null || title == null)
			return;
		
		String className = component.getClass().getSimpleName();
		String text = title.getText();
		
		if (Constants.CLASS_NAME_JLABEL.equals(className))
			((JLabel) component).setText(text);
		else if (Constants.CLASS_NAME_JBUTTON.equals(className))
			((JButton) component).setText(text);
		else if (Constants.CLASS_NAME_JCHECKBOX.equals(className))
			((JCheckBox) component).setText(text);
		else if (Constants.CLASS_NAME_JMENUITEM.equals(className))
			((JMenuItem) component).setText(text);
		else if (Constants.CLASS_NAME_JPANEL.equals(className))
		{
			Border border = ((JPanel) component).getBorder();
			
			if (border != null)
			{
				String borderClassName = border.getClass().getSimpleName();
				
				if (Constants.CLASS_NAME_TITLEDBORDER.equals(borderClassName))
				{
					((TitledBorder) border).setTitle(text);
					
					component.repaint();
				}
			}
		}
		else if (component instanceof JFrame)
			((JFrame) component).setTitle(text);
		else if (component instanceof JDialog)
			((JDialog) component).setTitle(text);
	}
	
    public void changeComponentTitle(Component component, Title title)
	{
		setComponentTitle(component, title);
		registerComponent(component, title);
	}
	
    public void registerComponent(Component component, Title title)
	{
		if (component == null || title == null)
			return;
		
		titledComponents.put(component, title);
	}
	
    public void resetComponents()
	{
		for (Map.Entry<Component, Title> entry : titledComponents.entrySet())
		{
			Component component = entry.getKey();
			Title title = entry.getValue();
			
			setComponentTitle(component, title);
		}
	}
}
