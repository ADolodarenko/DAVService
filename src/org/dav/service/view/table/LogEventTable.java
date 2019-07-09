package org.dav.service.view.table;

import org.dav.service.util.Constants;
import org.dav.service.view.table.renderer.DateTimeCellRenderer;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Enumeration;

public class LogEventTable extends JTable
{
	public static final int DATETIME_COLUMN_MIN_WIDTH = 100;
	public static final int DATETIME_COLUMN_MAX_WIDTH = 160;

	public LogEventTable(TableModel model)
	{
		super(model);

		setHeaderAppearance();
		setColumnAppearance();
		setSelectionStrategy();
		
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		setRowHeight((int) (getRowHeight() * 1.3));
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	protected void setHeaderAppearance()
	{
		JTableHeader header = getTableHeader();
		header.setReorderingAllowed(false);
	}

	public void setColumnAppearance()
	{
		setColumnAppearance(DATETIME_COLUMN_MIN_WIDTH, DATETIME_COLUMN_MAX_WIDTH);
	}
	
	public void setColumnAppearance(int dateTimeColumnMinWidth, int dateTimeColumnMaxWidth)
	{
		Enumeration<TableColumn> columns = getColumnModel().getColumns();
		
		while (columns.hasMoreElements())
		{
			TableColumn column = columns.nextElement();
			
			if (column.getModelIndex() == 0)
			{
				column.setCellRenderer(new DateTimeCellRenderer());
				column.setMinWidth(dateTimeColumnMinWidth);
				column.setMaxWidth(dateTimeColumnMaxWidth);
				column.setPreferredWidth( (dateTimeColumnMinWidth + dateTimeColumnMaxWidth) / 2 );
				
				break;
			}
		}
	}

	private void setSelectionStrategy()
	{
		setCellSelectionEnabled(true);
		getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	public LogEvent getLogEvent(int row, int column)
	{
		int modelColumnIndex = convertColumnIndexToModel(column);

		if (modelColumnIndex > 0)
		{
			int modelRowIndex = convertRowIndexToModel(row);
			TableModel model = getModel();
			String modelClassName = model.getClass().getSimpleName();

			if (Constants.CLASS_NAME_LOGEVENTTABLEMODEL.equals(modelClassName))
			{
				LogEventTableModel thisModel = (LogEventTableModel) model;

				if (thisModel.getRowCount() > modelRowIndex)
					return thisModel.getRow(modelRowIndex);
			}
		}

		return null;
	}
}

