package org.dav.service.view.dialog;

import org.dav.service.settings.TransmissiveSettings;
import org.dav.service.settings.parameter.Parameter;
import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;
import org.dav.service.view.Title;
import org.dav.service.view.TitleAdjuster;
import org.dav.service.view.ViewUtils;
import org.dav.service.view.table.SettingsTable;
import org.dav.service.view.table.SettingsTableModel;
import org.dav.service.view.table.editor.TableCellEditorFactory;
import org.dav.service.view.table.listener.ForEditableCellsSelectionListener;
import org.dav.service.view.table.renderer.TableCellRendererFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class SettingsDialog extends JDialog
{
	private static final Dimension BUTTON_MAX_SIZE = new Dimension(100, 30);

	private Frame owner;
	private SettingsDialogInvoker invoker;

	private ResourceManager resourceManager;
	private TitleAdjuster titleAdjuster;

	private List<TransmissiveSettings> settingsList;

	private SettingsTableModel tableModel;
	private SettingsTable table;

	private AbstractAction okAction;
	private AbstractAction cancelAction;

	private JButton okButton;
	private JButton cancelButton;

	public SettingsDialog(Frame owner, SettingsDialogInvoker invoker,
						  ResourceManager resourceManager, TransmissiveSettings... settingsArray) throws Exception
	{
		super(owner, "", true);

		this.owner = owner;
		this.invoker = invoker;

		this.resourceManager = resourceManager;
		this.titleAdjuster = new TitleAdjuster();

		this.settingsList = new LinkedList<>();
		resetSettingsList(settingsArray);

		initComponents();

		setResizable(false);
	}

	public void resetSettingsList(TransmissiveSettings... settingsArray)
	{
		settingsList.clear();

		if (settingsArray != null)
			for (int i = 0; i < settingsArray.length; i++)
				settingsList.add(settingsArray[i]);
	}

	@Override
	public void setVisible(boolean b)
	{
		if (b)
		{
			List<Parameter> allSettingsList = new LinkedList<>();
			tableModel.clear();

			for (TransmissiveSettings settings : settingsList)
				allSettingsList.addAll(settings.getParameterList());

			if (!allSettingsList.isEmpty())
				tableModel.addAllRows(allSettingsList);

			titleAdjuster.resetComponents();
			tableModel.fireTableStructureChanged();
			resetActions();

			pack();
			setLocationRelativeTo(owner);
		}

		super.setVisible(b);
	}

	/**
	 * Forces the settings to load themselves from their sources.
	 */
	public void reloadSettings()
	{
		for (TransmissiveSettings settings : settingsList)
			try
			{
				settings.load();
			}
			catch (Exception e)
			{
				if (invoker != null)
					invoker.log(e);
			}
	}

	/**
	 * Saves all changes and closes the settings dialog.
	 */
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
				if (invoker != null)
					invoker.log(e);
			}
		}

		if (invoker != null)
			invoker.reloadSettings();

		exit();
	}

	/**
	 * Closes the settings dialog without saving any changes.
	 */
	public void exit()
	{
		stopTableEditing();

		setVisible(false);

		if (invoker != null)
			invoker.setFocus();
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

		table.getColumnModel().getSelectionModel().addListSelectionListener(new ForEditableCellsSelectionListener(table));

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

		initActions();
		initButtons();

		assignKeyStrokes(panel);

		panel.add(okButton);
		panel.add(cancelButton);

		return panel;
	}

	private void initActions()
	{
		okAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				saveAndExit();
			}
		};

		cancelAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				exit();
			}
		};

		resetActions();
	}

	private void resetActions()
	{
		ViewUtils.resetAction(okAction,
				resourceManager,
				Constants.KEY_BUTTON_OK,
				null,
				Constants.ICON_NAME_OK);

		ViewUtils.resetAction(cancelAction,
				resourceManager,
				Constants.KEY_BUTTON_CANCEL,
				null,
				Constants.ICON_NAME_CANCEL);
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

	private void assignKeyStrokes(JComponent component)
	{
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), Constants.KEY_BUTTON_CANCEL);

		ActionMap actionMap = component.getActionMap();
		actionMap.put(Constants.KEY_BUTTON_CANCEL, cancelAction);
	}

	private void stopTableEditing()
	{
		if (table != null && table.isEditing())
			table.getCellEditor().stopCellEditing();
	}
}
