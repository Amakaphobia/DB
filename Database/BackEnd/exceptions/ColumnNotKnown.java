package exceptions;

import dbmodel.ColumnIdentifier;

/**
 * Exception thrown if you try to access a column thats unknown to the Datamodel
 * @author Dave
 *
 */
public class ColumnNotKnown extends Exception 
{
	/**?*/
	private static final long serialVersionUID = 1324122255966387520L;

	/**
	 * Constructor
	 * @param ciP the column thats unknown
	 */
	public ColumnNotKnown(ColumnIdentifier ciP)
	{
		super(String.format("The Column %s is not known.", ciP.toString()));
	}
	
	/**
	 * Constructor 
	 * @param ciP the column thats unknown
	 */
	public ColumnNotKnown(String ciP)
	{
		super(String.format("The Column %s is not known.", ciP));
	}
}
