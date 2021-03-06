package org.dav.service.view;

import org.dav.service.util.Constants;
import org.dav.service.util.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * A utility class that operates on dialogs and some service data,
 * taking into account language settings of the ResourceManager instance given.
 * You must call the resetResourceManager static method with you current ResourceManager instance given
 * before invoking any other methods of this class.
 */
public class ViewUtils
{
	private static ResourceManager resourceManager;
	private static List<UITextParameter> uiTextParameters;
	private static Component dialogOwner;
	private static JFileChooser fileChooser;

	private ViewUtils(){}

	/**
	 * Sets the current resource manager. The first method to call on this class.
	 * @param manager your ResourceManager instance
	 */
	public static void resetResourceManager(ResourceManager manager)
	{
		if (resourceManager != manager)
		{
			resourceManager = manager;

			rebuildTextParameters();
		}
	}

	private static void rebuildTextParameters()
	{
		uiTextParameters = new LinkedList<>();
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_OPTION_PANE_BUTTON_YES,
				new Title(resourceManager, Constants.KEY_BUTTON_YES)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_OPTION_PANE_BUTTON_NO,
				new Title(resourceManager, Constants.KEY_BUTTON_NO)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TITLE_OPEN,
				new Title(resourceManager, Constants.KEY_CAPTION_OPEN)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TITLE_SAVE,
				new Title(resourceManager, Constants.KEY_CAPTION_SAVE)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_LOOK_IN,
				new Title(resourceManager, Constants.KEY_LABEL_LOOK_IN)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_SAVE_IN,
				new Title(resourceManager, Constants.KEY_LABEL_SAVE_IN)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_UP_FOLDER,
				new Title(resourceManager, Constants.KEY_TOOLTIP_UP_FOLDER)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_NEW_FOLDER,
				new Title(resourceManager, Constants.KEY_TOOLTIP_NEW_FOLDER)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_VIEW_MENU,
				new Title(resourceManager, Constants.KEY_TOOLTIP_VIEW_MENU)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_LIST_VIEW,
				new Title(resourceManager, Constants.KEY_TOOLTIP_LIST_VIEW)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_DETAILS_VIEW,
				new Title(resourceManager, Constants.KEY_TOOLTIP_DETAILS_VIEW)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_FILE_NAME,
				new Title(resourceManager, Constants.KEY_LABEL_FILE_NAME)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_LABEL_FILE_TYPES,
				new Title(resourceManager, Constants.KEY_LABEL_FILE_TYPES)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_BUTTON_OPEN,
				new Title(resourceManager, Constants.KEY_CAPTION_OPEN)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_BUTTON_DIRECTORY_OPEN,
				new Title(resourceManager, Constants.KEY_CAPTION_OPEN)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_BUTTON_SAVE,
				new Title(resourceManager, Constants.KEY_CAPTION_SAVE)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_BUTTON_CANCEL,
				new Title(resourceManager, Constants.KEY_BUTTON_CANCEL)));

		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_OPEN,
				new Title(resourceManager, Constants.KEY_TOOLTIP_OPEN_FILE)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_DIRECTORY_OPEN,
				new Title(resourceManager, Constants.KEY_TOOLTIP_OPEN_DIRECTORY)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_SAVE,
				new Title(resourceManager, Constants.KEY_TOOLTIP_SAVE_FILE)));
		uiTextParameters.add(new UITextParameter(Constants.KEY_UI_FILE_CHOOSER_TOOLTIP_CANCEL,
				new Title(resourceManager, Constants.KEY_TOOLTIP_CANCEL)));
	}

	public static void setDialogOwner(Component owner)
	{
		dialogOwner = owner;
	}

	public static Component getDialogOwner()
	{
		return dialogOwner;
	}

	private static void initFileChooser()
	{
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	public static Object confirmedValue(Object oldValue, Object newValue)
	{
		String title = new Title(resourceManager, Constants.KEY_CONFIRMATION_TITLE).getText();
		String message = new Title(resourceManager, Constants.KEY_CONFIRMATION_MESSAGE).getText();

		if (JOptionPane.showConfirmDialog(null, message, title,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return newValue;
		else
			return oldValue;
	}

	public static void adjustDialogs()
	{
		for (UITextParameter parameter : uiTextParameters)
			UIManager.put(parameter.getKey(), parameter.getValue().getText());

		File directory = getFileChooserDirectory(null);

		initFileChooser();

		fileChooser.setCurrentDirectory(directory);
	}

	private static File getFileChooserDirectory(File currentDirectory)
	{
		File directory = currentDirectory;

		if (directory == null)
			if (fileChooser != null)
				directory = fileChooser.getCurrentDirectory();
			else
				directory = new File(".");

		return directory;
	}

	public static JFileChooser getFileChooser(File currentDirectory)
	{
		File directory = getFileChooserDirectory(currentDirectory);

		if (fileChooser == null)
			initFileChooser();

		fileChooser.setCurrentDirectory(directory);

		return fileChooser;
	}

	public static String getAssemblyInformationString(Object targetObject, String dataDelimiter, ExtensionInfoType... infoTypes)
	{
		if (targetObject == null || infoTypes == null || infoTypes.length < 1)
			return null;

		Package targetPackage = targetObject.getClass().getPackage();

		if (dataDelimiter == null || dataDelimiter.length() < 1)
			dataDelimiter = " ";

		StringBuilder builder = new StringBuilder();
		for (ExtensionInfoType infoType : infoTypes)
		{
			if (builder.length() > 0)
				builder.append(dataDelimiter);

			String info = getAssemblyInfoByType(targetPackage, infoType);
			if (info == null)
				info = "";
			builder.append(info);
		}

		return builder.toString();
	}

	public static void resetAction(AbstractAction action,
								   ResourceManager resourceManager,
								   String nameKey,
								   String shortDescriptionKey,
								   String actionIconName)
	{
		if (action != null)
		{
			ResourceManager resManager = ViewUtils.resourceManager;
			if (resourceManager != null)
				resManager = resourceManager;

			if (resManager == null)
				throw new IllegalArgumentException(Constants.EXCPT_RESOURCE_MANAGER_EMPTY);

			if (nameKey != null)
				action.putValue(Action.NAME, new Title(resManager, nameKey).getText());
			if (shortDescriptionKey != null)
				action.putValue(Action.SHORT_DESCRIPTION, new Title(resManager, shortDescriptionKey).getText());
			action.putValue(Action.SMALL_ICON, resManager.getImageIcon(actionIconName));
		}
	}

	private static String getAssemblyInfoByType(Package targetPackage, ExtensionInfoType infoType)
	{
		switch (infoType)
		{
			case SPECIFICATION_TITLE:
				return targetPackage.getSpecificationTitle();
			case SPECIFICATION_VERSION:
				return targetPackage.getSpecificationVersion();
			case SPECIFICATION_VENDOR:
				return targetPackage.getSpecificationVendor();
			case IMPLEMENTATION_TITLE:
				return targetPackage.getImplementationTitle();
			case IMPLEMENTATION_VERSION:
				return targetPackage.getImplementationVersion();
			case IMPLEMENTATION_VENDOR:
				return targetPackage.getImplementationVendor();
			default:
				return null;
		}
	}
}
