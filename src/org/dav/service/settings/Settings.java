package org.dav.service.settings;

public interface Settings
{
	void init() throws Exception;
	void load() throws Exception;
	void save() throws Exception;
}
