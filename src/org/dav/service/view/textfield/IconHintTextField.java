package org.dav.service.view.textfield;

import org.dav.service.util.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IconHintTextField extends JTextField implements FocusListener
{
	private Icon icon;
	private String hint;
	private Insets dummyInsets;

	public IconHintTextField()
	{
		this(null, null);
	}

	public IconHintTextField(Icon icon)
	{
		this(icon, null);
	}

	public IconHintTextField(String hint)
	{
		this(null, hint);
	}

	public IconHintTextField(Icon icon, String hint)
	{
		this.icon = icon;
		this.hint = hint;

		Border border = UIManager.getBorder(Constants.KEY_UI_TEXTFIELD_BORDER);
		JTextField dummy = new JTextField();
		this.dummyInsets = border.getBorderInsets(dummy);

		addFocusListener(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int textX = 2;

		if (this.icon != null)
		{
			int iconWidth = icon.getIconWidth();
			int iconHeight = icon.getIconHeight();
			int x = dummyInsets.left + 5; 		//this is our icon's x
			textX = x + iconWidth + 2;       	//this is the x where text should start
			int y = (this.getHeight() - iconHeight)/2;
			icon.paintIcon(this, g, x, y);
		}

		setMargin(new Insets(2, textX, 2, 2));

		if ( hint != null && !hint.isEmpty() && !hasFocus() && getText().equals("") )
		{
			int height = getHeight();
			Font prev = g.getFont();
			Font italic = prev.deriveFont(Font.ITALIC);
			Color prevColor = g.getColor();
			g.setFont(italic);
			g.setColor(UIManager.getColor(Constants.KEY_UI_TEXT_INACTIVETEXT));
			int h = g.getFontMetrics().getHeight();
			int textBottom = (height - h) / 2 + h - 4;
			int x = this.getInsets().left;
			Graphics2D g2d = (Graphics2D) g;
			RenderingHints hints = g2d.getRenderingHints();
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.drawString(hint, x, textBottom);
			g2d.setRenderingHints(hints);
			g.setFont(prev);
			g.setColor(prevColor);
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		repaint();
	}

	public Icon getIcon()
	{
		return icon;
	}

	public void setIcon(Icon icon)
	{
		this.icon = icon;
	}

	public String getHint()
	{
		return hint;
	}

	public void setHint(String hint)
	{
		this.hint = hint;
	}
}
