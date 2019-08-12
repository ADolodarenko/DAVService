package org.dav.service.view.table.listener;

import org.dav.service.util.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ForEditableCellsSelectionListener implements ListSelectionListener
{
	private JTable table;

	public ForEditableCellsSelectionListener(JTable table)
	{
		if (table == null)
			throw new IllegalArgumentException(Constants.EXCPT_TABLE_EMPTY);

		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int rowCount = table.getRowCount();
		int columnCount = table.getColumnCount();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		if (row > -1 && row < rowCount && column > -1 && column < columnCount)
		{
			while (!table.isCellEditable(row, column))
			{
				column++;

				if (column == columnCount)
				{
					column = 0;
					row++;
				}

				if (row == rowCount)
					row = 0;

				if (row == table.getSelectedRow() && column == table.getSelectedColumn())
					break;
			}

			table.changeSelection(row, column, false, false);
		}
	}
}
