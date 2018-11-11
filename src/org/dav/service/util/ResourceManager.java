package org.dav.service.util;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public interface ResourceManager
{
    List<Locale> getAvailableLocales();
    Locale getCurrentLocale();
    void setCurrentLocale(Locale locale);
    ResourceBundle getBundle();
    File getConfig();
    ImageIcon getImageIcon(String name);
}
