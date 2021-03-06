package org.dav.service.view.table.renderer;

import org.dav.service.util.ResourceManager;
import org.dav.service.util.Constants;

import javax.swing.table.TableCellRenderer;
import java.util.HashMap;
import java.util.Map;

public class TableCellRendererFactory
{
	protected ResourceManager resourceManager;
	private Map<String, TableCellRenderer> renderers;

	public TableCellRendererFactory(ResourceManager resourceManager)
	{
		this.resourceManager = resourceManager;

		this.renderers = new HashMap<>();
	}

	public TableCellRenderer getRenderer(Class<?> dataClass)
	{
		TableCellRenderer renderer;

		String dataClassName = dataClass.getSimpleName();

		if (renderers.containsKey(dataClassName))
			renderer = renderers.get(dataClassName);
		else
			renderer = createRenderer(dataClassName);

		return renderer;
	}

	private TableCellRenderer createRenderer(String forClassName)
	{
		TableCellRenderer renderer = null;

		switch (forClassName)
		{
			case Constants.CLASS_NAME_BOOLEAN:
				break;
			case Constants.CLASS_NAME_INTEGER:
				break;
			case Constants.CLASS_NAME_DOUBLE:
				break;
			case Constants.CLASS_NAME_STRING:
				break;
			case Constants.CLASS_NAME_LOCALE:
				renderer = new LocaleValueCellRenderer(resourceManager);
				break;
			case Constants.CLASS_NAME_PASSWORD:
				renderer = new PasswordCellRenderer();
				break;
			case Constants.CLASS_NAME_CHARSET:
				renderer = new CharsetCellRenderer();
		}

		if (renderer != null)
			renderers.put(forClassName, renderer);

		return renderer;
	}
}
