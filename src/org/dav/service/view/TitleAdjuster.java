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
		
		Class<?> componentClass = component.getClass();
		String text = title.getText();
		
		if (JLabel.class.isAssignableFrom(componentClass))
			((JLabel) component).setText(text);
		else if (JButton.class.isAssignableFrom(componentClass))
			((JButton) component).setText(text);
		else if (JCheckBox.class.isAssignableFrom(componentClass))
			((JCheckBox) component).setText(text);
		else if (JMenuItem.class.isAssignableFrom(componentClass))
			((JMenuItem) component).setText(text);
		else if (JPanel.class.isAssignableFrom(componentClass))
		{
			Border border = ((JPanel) component).getBorder();
			
			if (border != null)
			{
				Class<?> borderClass = border.getClass();
				
				if (TitledBorder.class.isAssignableFrom(borderClass))
				{
					((TitledBorder) border).setTitle(text);
					
					component.repaint();
				}
			}
		}
		else if (JFrame.class.isAssignableFrom(componentClass))
			((JFrame) component).setTitle(text);
		else if (JDialog.class.isAssignableFrom(componentClass))
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
