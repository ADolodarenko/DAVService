package org.dav.service.view.dialog;

import org.dav.service.settings.TransmissiveSettings;
import org.dav.service.settings.parameter.Parameter;
import org.dav.service.util.ResourceManager;
import org.dav.service.view.Constants;
import org.dav.service.view.ParentWindow;
import org.dav.service.view.Title;
import org.dav.service.view.TitleAdjuster;
import org.dav.service.view.table.SettingsTable;
import org.dav.service.view.table.SettingsTableModel;
import org.dav.service.view.table.editor.TableCellEditorFactory;
import org.dav.service.view.table.renderer.TableCellRendererFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SettingsDialog extends JDialog
{
	private static final Dimension BUTTON_MAX_SIZE = new Dimension(100, 30);

	private ParentWindow parent;

	private ResourceManager resourceManager;
	private TitleAdjuster titleAdjuster;

	private List<TransmissiveSettings> settingsList;

	private SettingsTableModel tableModel;
	private SettingsTable table;

	private JButton okButton;
	private JButton cancelButton;

	public SettingsDialog(ParentWindow parent, ResourceManager resourceManager, TransmissiveSettings... settingsArray) throws Exception
	{
		super(parent, "", true);

		this.parent = parent;

		this.resourceManager = resourceManager;
		this.titleAdjuster = new TitleAdjuster();

		settingsList = new LinkedList<>();
		for (int i = 0; i < settingsArray.length; i++)
			settingsList.add(settingsArray[i]);

		initComponents();

		setResizable(false);
	}

	private void initComponents()
	{
		add(initSettingsPanel(), BorderLayout.CENTER);
		add(initCommandPanel(), BorderLayout.SOUTH);

		titleAdjuster.registerComponent(this, new Title(resourceManager, Constants.KEY_SETTINGS_DIALOG));
	}

	private JPanel initSettingsPanel()
	{
		tableModel = new SettingsTableModel(resourceManager, Parameter.getTitleKeys(), null);

		table = new SettingsTable(tableModel,
				new TableCellEditorFactory(resourceManager),
				new TableCellRendererFactory(resourceManager));

		JScrollPane tablePane = new JScrollPane(table);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(tablePane, BorderLayout.CENTER);

		return panel;
	}

	private JPanel initCommandPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());

		initButtons();

		panel.add(okButton);
		panel.add(cancelButton);

		return panel;
	}

	private void initButtons()
	{
		okButton = new JButton();
		titleAdjuster.registerComponent(okButton, new Title(resourceManager, Constants.KEY_BUTTON_OK));
		okButton.setPreferredSize(BUTTON_MAX_SIZE);
		okButton.setMaximumSize(BUTTON_MAX_SIZE);
		okButton.setIcon(resourceManager.getImageIcon(Constants.ICON_NAME_OK));
		okButton.addActionListener(event -> saveAndExit());

		cancelButton = new JButton();
		titleAdjuster.registerComponent(cancelButton, new Title(resourceManager, Constants.KEY_BUTTON_CANCEL));
		cancelButton.setPreferredSize(BUTTON_MAX_SIZE);
		cancelButton.setMaximumSize(BUTTON_MAX_SIZE);
		cancelButton.setIcon(resourceManager.getImageIcon(Constants.ICON_NAME_CANCEL));
		cancelButton.addActionListener(event -> exit());
	}

	@Override
	public void setVisible(boolean b)
	{
		if (b)
		{
			List<Parameter> allSettingsList = new LinkedList<>();
			tableModel.clear();

			for (TransmissiveSettings settings : settingsList)
			{
				try
				{
					settings.load();
				}
				catch (Exception e)
				{
					parent.log(e);
				}

				allSettingsList.addAll(settings.getParameterList());
			}

			if (!allSettingsList.isEmpty())
				tableModel.addAllRows(allSettingsList);

			titleAdjuster.resetComponents();
			tableModel.fireTableStructureChanged();

			pack();
			setLocationRelativeTo(parent);
		}

		super.setVisible(b);
	}

	public void saveAndExit()
	{
		stopTableEditing();

		for (TransmissiveSettings settings : settingsList)
		{
			try
			{
				settings.save();
			}
			catch (Exception e)
			{
				parent.log(e);
			}
		}

		parent.reloadSettings();

		exit();
	}

	public void exit()
	{
		stopTableEditing();

		setVisible(false);
		parent.setFocus();
	}

	private void stopTableEditing()
	{
		if (table != null && table.isEditing())
			table.getCellEditor().stopCellEditing();
	}
}
