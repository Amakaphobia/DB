package exceptions;

import dbmodel.TableIdentifier;
import statement.Statement;

/**
 * Exception thrown when you try to add a table to the datamodel thats already added
 * @author Dave
 *
 */
public class TableAlreadyKnown extends Exception 
{
	/***?*/
	private static final long serialVersionUID = 4018453321809546358L;

	/**
	 * Constructor
	 * @param ti the table to be added
	 */
	public TableAlreadyKnown(TableIdentifier ti)
	{
		super(String.format("The Table %s is already known.", ti.toString()));
	}
	
	/**
	 * Constructor
	 * @param tableName the tablename thats to be added
	 */
	public TableAlreadyKnown(String tableName)
	{
		super(String.format("The Table %s is already known.", tableName));
	}
	
	/**
	 * Constructor
	 * @param s statement of the dynamic table
	 * @param table Table name
	 * @param shn table short name
	 */
	public TableAlreadyKnown(Statement s, String table, String shn) {
		super(String.format("The Table %s.%s constructed by \n %s \n is already known", shn, table, s.toString()));
	}
}
