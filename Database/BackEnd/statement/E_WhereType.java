package statement;

/**
 * Enum defining possible whereclause comparators
 * @author Dave
 *
 */
public enum E_WhereType 
{
	/**Used for EQUALS Comparison*/
	EQUALS(" = "),
	/**Used for GREATER THAN Comparison*/
	GREATER(" > "),
	/**Used for LESS THAN Comparison*/
	LESS(" < "),
	/**Used for GREATER THAN or EQUAL_Comparison*/
	GREATER_EQUAL(" >= "),
	/**Used for LESS THAN or EQUAL Comparison*/
	LESS_EQUAL(" <= "),
	/**Used for NOT_EQUAL Comparison*/
	NOT_EQUAL(" <> "),
	/**Used for LIKE Comparison*/
	LIKE(" LIKE "),
	/**Used for NOT_LIKE Comparison*/
	NOT_LIKE(" NOT LIKE "),
	/**Used for IN Comparison*/
	IN(" IN ");
	
	/**String thats containing the comparator*/
	private String key;
	
	/**
	 * Constructor
	 * @param key the comparator
	 */
	E_WhereType(String key){
		this.key = key;
	}
	
	/**
	 * used to access the comparator
	 * @return a string
	 */
	public String getKey() {return this.key;}
	
}
