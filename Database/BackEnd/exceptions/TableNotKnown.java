package exceptions;

import dbmodel.TableIdentifier;

/**
 * Exception thrown when a unknown table is encountered
 * @author Dave
 *
 */
public class TableNotKnown extends Exception 
{
	/**?*/
	private static final long serialVersionUID = -1015289825101355700L;

	/**
	 * Constructor
	 * @param tiP the table thats not known
	 */
	public TableNotKnown(TableIdentifier tiP)
	{
		super(String.format("The Table %s is not known", tiP.toString()));
	}
	
	/**
	 * Constructor
	 * @param tableNameP the tablename thats not known
	 */
	public TableNotKnown(String tableNameP)
	{
		super(String.format("The Table %s is not known", tableNameP));
	}
}
