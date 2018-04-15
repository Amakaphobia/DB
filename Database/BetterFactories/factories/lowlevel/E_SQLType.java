package factories.lowlevel;

/**
 * Enum containing all SQL-Statement Types
 * @author Dave
 *
 */
public enum E_SQLType 
{
	/**SELECT Type-Statement*/
	SELECT("Select"),
	/**INSERT Type-Statement*/
	INSERT("Insert"),
	/**DELETE Type-Statement*/
	DELETE("Delete"),
	/**UPDATE Type-Statement*/
	UPDATE("Update");
	
	/**String containing the name of the statement type*/
	private String name;
	
	/**
	 * Constructor
	 * @param nameP the name of the statementtype
	 */
	E_SQLType(String nameP)
	{
		this.name = nameP;
	}

	/**
	 * Method used to access the name of the statement
	 * @return a String
	 */
	public String getName() {
		return name;
	}

	
}
