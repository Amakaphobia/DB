package exceptions;

import dbmodel.ColumnIdentifier;

/**
 * Exception thrown if you try to add a Column to the Datamodel thats already known
 * @author Dave
 *
 */
public class ColumnAlreadyKnown extends Exception 
{
	/**?*/
	private static final long serialVersionUID = 2374764371190658476L;

	/**
	 * Constructor
	 * @param ci the column thats already known
	 */
	public ColumnAlreadyKnown(ColumnIdentifier ci)
	{
		super(String.format("The Column %s is already in this Table.",ci.toString()));
	}
	
	/**
	 * Constructor
	 * @param ColName the Column thats already known
	 */
	public ColumnAlreadyKnown(String ColName) {
		super(String.format("The Column %s is already in this Table.", ColName));
	}
}
