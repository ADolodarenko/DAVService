package org.dav.service.util;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This interface describes methods for working with locales, language bundles and icons.
 */
public interface ResourceManager
{
    List<Locale> getAvailableLocales();
    Locale getCurrentLocale();
    void setCurrentLocale(Locale locale);
    ResourceBundle getBundle();
    File getConfig();
    ImageIcon getImageIcon(String name);
}
