package exceptions;

import factories.lowlevel.E_SQLType;
import subAssemblers.E_Assemblertypes;

/**
 * Thrown if you want to add a clause thats not supported by the statement
 * @author Dave
 *
 */
public class ClauseNotSupported extends Exception {
	
	/**svid*/
	private static final long serialVersionUID = 916397017724776186L;

	/**
	 * Constructor
	 * @param name the Subassemblertype you want to add
	 * @param type the sql statenemt type you want to add to
	 */
	public ClauseNotSupported(E_Assemblertypes name, E_SQLType type)
	{
		super(String.format("The Clause %s is not supported in SQLType %s.", name.getName(),type.getName()));
	}

}
