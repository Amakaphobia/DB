package exceptions;

/**
 * Exception thrown if you try to attack the statement
 * @author Dave
 *
 */
public class IllegalClauseValue extends Exception 
{
	/**?*/
	private static final long serialVersionUID = -6050498838079877786L;
	
	/**
	 * Constructor
	 * @param foundChars the Stringsequence that triggered the attackdetection
	 */
	public IllegalClauseValue(String foundChars) {
		super(String.format("The Squence %s is not allowed", foundChars));
	}

	

}
