package org.dav.service.settings.parameter;

import org.dav.service.settings.type.Password;
import org.dav.service.util.Constants;
import org.dav.service.util.Core;
import org.dav.service.view.Title;

import java.io.File;

public class Parameter
{
	public static String[] getTitleKeys()
	{
		return new String[] { Constants.KEY_COLUMN_PARAM_NAME, Constants.KEY_COLUMN_PARAM_VALUE};
	}

	private Title key;
	private Object value;
	private Class<?> type;

	public Parameter(Title key, Object value, Class<?> type)
	{
		if (key == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_KEY_EMPTY);

		if (value == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_VALUE_EMPTY);

		if (type == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_TYPE_EMPTY);

		this.key = key;
		this.value = value;
		this.type = type;
	}

	public String getDisplayName()
	{
		return key.getText();
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value) throws IllegalArgumentException
	{
		if (value == null)
			throw new IllegalArgumentException(Constants.EXCPT_PARAM_VALUE_EMPTY);

		Class<?> thisClass = getType();
		Class<?> thatClass = value.getClass();

		String thisClassName = thisClass.getSimpleName();
		String thatClassName = thatClass.getSimpleName();

		if (thisClassName.equals(thatClassName))
			this.value = value;
		else if (Constants.CLASS_NAME_STRING.equals(thisClassName))
			this.value = value.toString();
		else if (Constants.CLASS_NAME_STRING.equals(thatClassName))
		{
			String stringValue = (String) value;

			switch (thisClassName)
			{
				case Constants.CLASS_NAME_BOOLEAN:
					this.value = Boolean.valueOf(stringValue);
					break;
				case Constants.CLASS_NAME_INTEGER:
					this.value = Integer.parseInt(stringValue);
					break;
				case Constants.CLASS_NAME_DOUBLE:
					this.value = Double.parseDouble(stringValue);
					break;
				case Constants.CLASS_NAME_FILE:
					this.value = new File(stringValue);
					break;
				case Constants.CLASS_NAME_PASSWORD:
					this.value = new Password(stringValue);
				default:
					throw new IllegalArgumentException(String.format(Constants.EXCPT_PARAM_VALUE_WRONG, stringValue));
			}
		}
		else if (Core.amongSuperClasses(thisClass, thatClass))
		{
			this.value = value;
		}
		else
			throw new IllegalArgumentException(String.format(Constants.EXCPT_PARAM_VALUE_WRONG, value.toString()));
	}

	public Class<?> getType()
	{
		return type;
	}
}
