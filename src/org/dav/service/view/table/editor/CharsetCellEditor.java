package org.dav.service.view.table.editor;

import org.dav.service.view.ViewUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.nio.charset.Charset;

public class CharsetCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private JComboBox<Charset> editor;

	private boolean confirmationRequired;
	private Object oldValue;

	public CharsetCellEditor(boolean confirmationRequired)
	{
		DefaultComboBoxModel<Charset> model = new DefaultComboBoxModel<>();

		for (Charset charset : Charset.availableCharsets().values())
			model.addElement(charset);

		editor = new JComboBox<>(model);
		editor.setEditable(false);
		//TODO: Do I have to set an alternative renderer for editor here?

		this.confirmationRequired = confirmationRequired;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (confirmationRequired)
			oldValue = value;

		editor.setSelectedIndex(0);

		if (value != null)
		{
			if (value instanceof Charset)
				editor.setSelectedItem(value);
		}

		return editor;
	}

	@Override
	public Object getCellEditorValue()
	{
		Object newValue = editor.getSelectedItem();

		if (confirmationRequired && !newValue.equals(oldValue))
			newValue = ViewUtils.confirmedValue(oldValue, newValue);

		return newValue;
	}
}
