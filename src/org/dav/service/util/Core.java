package org.dav.service.util;

/**
 * A service class for various operations with Java Core objects and routines.
 * Consists of static methods.
 */
public class Core
{
	private Core(){}

	/**
	 * This method tells if one class presents in the chain of superclasses of another class.
	 * It also returns true if one class equals to another.
	 * @param applicantClass the testee class (that is suspected to be among the superclasses)
	 * @param successorClass the another class (among whos successor you are looking for)
	 * @return true if applicantClass is among superclasses for successorClass or these two classes are equal.
	 * false - if not.
	 */
	public static boolean amongSuperClasses(Class<?> applicantClass, Class<?> successorClass)
	{
		if (applicantClass == null || successorClass == null)
			return false;

		if (applicantClass.equals(successorClass))
			return true;

		Class<?> superClass = successorClass.getSuperclass();
		while (superClass != null)
		{
			if (applicantClass.equals(superClass))
				return true;

			superClass = superClass.getSuperclass();
		}

		return false;
	}
}
