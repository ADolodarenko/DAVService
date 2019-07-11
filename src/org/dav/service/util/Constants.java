package org.dav.service.util;

/**
 * A list of strings for exceptions, keys and messages.
 */
public class Constants
{
	//Exceptions of any kind
	public static final String EXCPT_RESOURCE_MANAGER_EMPTY = "Resource manager is empty.";
	public static final String EXCPT_RESOURCE_BUNDLE_EMPTY = "The resource bundle is empty.";
	public static final String EXCPT_TABLE_EDITOR_FACTORY_EMPTY = "Table cell editor factory is empty.";
	public static final String EXCPT_TABLE_RENDERER_FACTORY_EMPTY = "Table cell renderer factory is empty.";
	public static final String EXCPT_VALUE_TYPE_WRONG = "Wrong value type.";
	public static final String EXCPT_TITLE_KEY_EMPTY = "The title key is empty.";

	//Exceptions for Parameter class
	public static final String EXCPT_PARAM_KEY_EMPTY = "Parameter key is empty.";
	public static final String EXCPT_PARAM_VALUE_EMPTY = "Parameter value is empty.";
	public static final String EXCPT_PARAM_VALUE_WRONG = "Parameter value %s is wrong.";
	public static final String EXCPT_PARAM_TYPE_EMPTY = "Parameter type is empty.";

	//Exceptions for the FileSource hierarchy
	public static final String EXCPT_FILE_SETTINGS_EMPTY = "File settings are empty.";
	public static final String EXCPT_FILE_SETTINGS_WRONG = "Wrong file settings.";
	public static final String EXCPT_FILE_SOURCE_WRITES = "This file source is for writing only.";
	public static final String EXCPT_FILE_SOURCE_READS = "This file source is for reading only.";
	public static final String EXCPT_FILE_SOURCE_INCORRECT = "This file source has an incorrect format.";

	//Exceptions for the DataSource hierarchy
	public static final String EXCPT_DATABASE_SETTINGS_EMPTY = "Database settings are empty.";
	public static final String EXCPT_DATABASE_SETTINGS_WRONG = "Wrong database settings.";
	public static final String EXCPT_DATABASE_WITHOUT_SP_SUPPORT = "This database doesn't support stored procedures.";
	public static final String EXCPT_CANT_INSERT_TO_TMP_TABLE = "Can't insert a row in the temporary table.";

	//Exceptions for access objects
	public static final String EXCPT_FILE_SOURCE_EMPTY = "Empty file source.";
	public static final String EXCPT_DATA_SOURCE_EMPTY = "Empty data source.";
	public static final String EXCPT_PACK_SIZE_WRONG = "Wrong pack size.";

	//Exceptions for workers
	public static final String EXCPT_FILE_OBJECT_EMPTY = "File access object is empty.";
	public static final String EXCPT_DATA_OBJECT_EMPTY = "Data access object is empty.";
	public static final String EXCPT_URI_EMPTY = "The link to a file is empty.";

	public static final String EXCPT_TEXT_PATTERN_EMPTY = "Text pattern is empty.";

	public static final String EXCPT_TITLE_ID_WRONG = "Illegal titleId = %d.";
	public static final String EXCPT_PRODUCT_CODE_EMPTY = "Empty productCode.";

	//Class names
	public static final String CLASS_NAME_BOOLEAN = "Boolean";
	public static final String CLASS_NAME_INTEGER = "Integer";
	public static final String CLASS_NAME_DOUBLE = "Double";
	public static final String CLASS_NAME_DATE = "Date";
	public static final String CLASS_NAME_LOCALE = "Locale";
	public static final String CLASS_NAME_CHARSET = "Charset";
	public static final String CLASS_NAME_FILE = "File";
	public static final String CLASS_NAME_STRING = "String";
	public static final String CLASS_NAME_FILESETTINGS = "FileSettings";
	public static final String CLASS_NAME_DATABASESETTINGS = "DatabaseSettings";
	public static final String CLASS_NAME_SETTINGSTABLEMODEL = "SettingsTableModel";
	public static final String CLASS_NAME_LOGEVENTTABLEMODEL = "LogEventTableModel";
	public static final String CLASS_NAME_FILENAMEEXTENSIONFILTER = "FileNameExtensionFilter";
	public static final String CLASS_NAME_LOGEVENTTABLE = "LogEventTable";
	public static final String CLASS_NAME_PASSWORD = "Password";
	public static final String CLASS_NAME_LOCALDATETIME = "LocalDateTime";
	public static final String CLASS_NAME_SQLWARNING = "SQLWarning";
	public static final String CLASS_NAME_JLABEL = "JLabel";
	public static final String CLASS_NAME_JBUTTON = "JButton";
	public static final String CLASS_NAME_JCHECKBOX = "JCheckBox";
	public static final String CLASS_NAME_JMENUITEM = "JMenuItem";
	public static final String CLASS_NAME_JPANEL = "JPanel";
	public static final String CLASS_NAME_TITLEDBORDER = "TitledBorder";

	//Messages
	public static final String MESS_ROWS_AFFECTED = "(%d row(s) affected)";
	public static final String MESS_TRUE = "TRUE";
	public static final String MESS_AHREF_BEGINNING = "<a href=\"";
	public static final String MESS_FILE_REF_BEGINNING = "file:/";
	public static final String MESS_FILE_DEFAULT_PATTERN = "./file.txt";
	public static final String MESS_CURRENT_PATH = ".";
	public static final String MESS_LOG_FILE_DEFAULT_PATTERN = "./log.txt";
	public static final String MESS_SETTINGS_DESCRIPTION = "Program settings";

	//Titles for main frame
	public static final String KEY_MAIN_FRAME = "MainFrame";
	public static final String KEY_LOG_PANEL = "LogPanel";
	public static final String KEY_COLUMN_DATETIME = "ColumnDateTime";
	public static final String KEY_COLUMN_TEXT = "ColumnText";
	public static final String KEY_BUTTON_SETTINGS = "SettingsButton";

	public static final String KEY_COLUMN_PARAM_NAME = "ColumnParamName";
	public static final String KEY_COLUMN_PARAM_VALUE = "ColumnParamValue";

	//For Settings hierarchy
	public static final String KEY_PARAM_APP_LOCALE = "ApplicationLocale";
	public static final String KEY_PARAM_MAIN_WIN_X = "MainWindowX";
	public static final String KEY_PARAM_MAIN_WIN_Y = "MainWindowY";
	public static final String KEY_PARAM_MAIN_WIN_MAXIMIZED = "MainWindowMaximized";
	public static final String KEY_PARAM_MAIN_WIN_WIDTH = "MainWindowWidth";
	public static final String KEY_PARAM_MAIN_WIN_HEIGHT = "MainWindowHeight";
	public static final String KEY_PARAM_DB_DRIVER = "DatabaseDriver";
	public static final String KEY_PARAM_DB_CONN_PREF = "DatabaseConnectionPref";
	public static final String KEY_PARAM_DB_HOST = "DatabaseHost";
	public static final String KEY_PARAM_DB_PORT = "DatabasePort";
	public static final String KEY_PARAM_DB_CATALOG = "DatabaseCatalog";
	public static final String KEY_PARAM_DB_USER = "DatabaseUser";
	public static final String KEY_PARAM_DB_PASSWORD = "DatabasePassword";
	public static final String KEY_PARAM_DB_TABLE = "DatabaseTable";
	public static final String KEY_PARAM_DB_SP = "DatabaseStoredProcedure";
	public static final String KEY_PARAM_CHANNEL = "ChannelId";
	public static final String KEY_PARAM_PRICE_LIST = "PriceListId";
	public static final String KEY_PARAM_PACK_SIZE = "PacketSize";
	public static final String KEY_PARAM_SP_LOG_PATTERN = "StoredProcedureLogPattern";
	public static final String KEY_PARAM_FIELD_DELIMITER = "FieldDelimiter";
	public static final String KEY_PARAM_CHARSET = "Charset";

	//For SettingsDialog
	public static final String KEY_SETTINGS_DIALOG = "SettingsDialog";

	//For dialogs of any kind
	public static final String KEY_CONFIRMATION_TITLE = "ConfirmationTitle";
	public static final String KEY_CONFIRMATION_MESSAGE = "ConfirmationMessage";
	public static final String KEY_BUTTON_OK = "OkButton";
	public static final String KEY_BUTTON_CANCEL = "CancelButton";
	public static final String KEY_BUTTON_YES = "YesButton";
	public static final String KEY_BUTTON_NO = "NoButton";
	public static final String KEY_CAPTION_OPEN = "FileChooserOpenCaption";
	public static final String KEY_CAPTION_SAVE = "FileChooserSaveCaption";
	public static final String KEY_LABEL_LOOK_IN = "FileChooserLookInLabel";
	public static final String KEY_LABEL_SAVE_IN = "FileChooserSaveInLabel";
	public static final String KEY_TOOLTIP_UP_FOLDER = "FileChooserUpFolderToolTip";
	public static final String KEY_TOOLTIP_NEW_FOLDER = "FileChooserNewFolderToolTip";
	public static final String KEY_TOOLTIP_VIEW_MENU = "FileChooserViewMenuToolTip";
	public static final String KEY_TOOLTIP_LIST_VIEW = "FileChooserListViewButtonToolTip";
	public static final String KEY_TOOLTIP_DETAILS_VIEW = "FileChooserDetailsViewButtonToolTip";
	public static final String KEY_LABEL_FILE_NAME = "FileChooserFileNameLabel";
	public static final String KEY_LABEL_FILE_TYPES = "FileChooserFilesOfTypeLabel";
	public static final String KEY_TOOLTIP_OPEN_FILE = "FileChooserOpenFileToolTip";
	public static final String KEY_TOOLTIP_OPEN_DIRECTORY = "FileChooserOpenDirectoryToolTip";
	public static final String KEY_TOOLTIP_SAVE_FILE = "FileChooserSaveToolTip";
	public static final String KEY_TOOLTIP_CANCEL = "FileChooserCancelToolTip";

	//Keys for UIManager parameters
	public static final String KEY_UI_OPTION_PANE_BUTTON_YES = "OptionPane.yesButtonText";
	public static final String KEY_UI_OPTION_PANE_BUTTON_NO = "OptionPane.noButtonText";
	public static final String KEY_UI_FILE_CHOOSER_TITLE_OPEN = "FileChooser.openDialogTitleText";
	public static final String KEY_UI_FILE_CHOOSER_TITLE_SAVE= "FileChooser.saveDialogTitleText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_LOOK_IN = "FileChooser.lookInLabelText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_SAVE_IN = "FileChooser.saveInLabelText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_UP_FOLDER = "FileChooser.upFolderToolTipText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_NEW_FOLDER = "FileChooser.newFolderToolTipText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_VIEW_MENU = "FileChooser.viewMenuLabelText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_LIST_VIEW = "FileChooser.listViewActionLabelText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_DETAILS_VIEW = "FileChooser.detailsViewActionLabelText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_FILE_NAME = "FileChooser.fileNameLabelText";
	public static final String KEY_UI_FILE_CHOOSER_LABEL_FILE_TYPES = "FileChooser.filesOfTypeLabelText";
	public static final String KEY_UI_FILE_CHOOSER_BUTTON_OPEN = "FileChooser.openButtonText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_OPEN = "FileChooser.openButtonToolTipText";
	public static final String KEY_UI_FILE_CHOOSER_BUTTON_DIRECTORY_OPEN = "FileChooser.directoryOpenButtonText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_DIRECTORY_OPEN = "FileChooser.directoryOpenButtonToolTipText";
	public static final String KEY_UI_FILE_CHOOSER_BUTTON_SAVE = "FileChooser.saveButtonText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_SAVE = "FileChooser.saveButtonToolTipText";
	public static final String KEY_UI_FILE_CHOOSER_BUTTON_CANCEL = "FileChooser.cancelButtonText";
	public static final String KEY_UI_FILE_CHOOSER_TOOLTIP_CANCEL = "FileChooser.cancelButtonToolTipText";
	public static final String KEY_UI_TEXTFIELD_BORDER = "TextField.border";
	public static final String KEY_UI_TEXT_INACTIVETEXT = "textInactiveText";

	//Icons names
	public static final String ICON_NAME_OK = "ok_16.png";
	public static final String ICON_NAME_CANCEL = "cancel_16.png";
	public static final String ICON_NAME_USA = "american_16.png";
	public static final String ICON_NAME_RUS = "russian_16.png";

	private Constants(){}
}
