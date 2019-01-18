package org.dav.service.view.table.editor;

import org.dav.service.data.DataUtils;
import org.dav.service.view.Constants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private JTextField editor;

	private boolean confirmationRequired;
	private Object oldValue;

	public FileCellEditor(boolean confirmationRequired)
	{
		editor = new JTextField();
		editor.setEditable(false);
		editor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JFileChooser fileChooser = ViewUtils.getFileChooser();

				fileChooser.resetChoosableFileFilters();

				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT", "TXT"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV", "CSV"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XLS", "XLS"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XLSX", "XLSX"));

				if (fileChooser.showSaveDialog(ViewUtils.getDialogOwner()) == JFileChooser.APPROVE_OPTION)
					editor.setText(DataUtils.getSelectedFileWithExtension(fileChooser).getAbsolutePath());
			}
		});

		this.confirmationRequired = confirmationRequired;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (confirmationRequired)
			oldValue = value;

		editor.setText("");

		if (value != null)
		{
			String valueClassName = value.getClass().getSimpleName();

			if (Constants.CLASS_NAME_FILE.equals(valueClassName))
				editor.setText( ((File) value).getAbsolutePath() );
		}

		return editor;
	}

	@Override
	public Object getCellEditorValue()
	{
		Object newValue = editor.getText();

		if (confirmationRequired && !newValue.equals(oldValue))
			newValue = ViewUtils.confirmedValue(oldValue, newValue);

		return newValue;
	}
}
