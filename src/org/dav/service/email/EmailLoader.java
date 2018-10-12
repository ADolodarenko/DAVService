package org.dav.service.email;

import org.dav.service.exceptions.WrongEmailsException;

import java.util.List;

/**
 * This interface describes the method for loading emails, wherever they are.
 */
public interface EmailLoader
{
    List<Email> loadEmails() throws WrongEmailsException;
}
