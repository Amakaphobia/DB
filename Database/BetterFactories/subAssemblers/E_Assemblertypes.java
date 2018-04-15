package subAssemblers;

/**
 * Enum describing the usable Assembler types
 * @author Dave
 *
 */
public enum E_Assemblertypes {
	/**Unprepared Where Assembler*/
	BASICWHERE("Where"),
	/**prepared Where Assembler*/
	PREPPEDWHERE("Where"),
	/**Unprepared GroupBy Assembler*/
	GROUPBY("Group By"),
	/**Unprepared ORDERBY Assembler*/
	ORDERBY("Order By"),
	/**Unprepared SELECTIONCOL Assembler*/
	SELECTIONCOL("Selection Collumn"),
	/**Unprepared SELECTIONTAB Assembler*/
	SELECTIONTAB("Selection Table"),
	/**Unprepared TABLENAME Assembler*/
	TABLENAME("Selection Table"),
	/**Unprepared VALUES Assembler*/
	BASICVALUES("VALUES"),
	/**Prepared VALUES Assembler*/
	PREPPEDVALUES("VALUES"),
	/**set assembler*/
	BASICSET("SET"),
	/**Set Assembler*/
	PREPPEDSET("SET");
	
	/**String holding the name of the assembler*/
	private String name;
	
	/**
	 * Constructor
	 * @param name the name of the assembler
	 */
	private E_Assemblertypes(String name) {
		this.name = name;
	}
	
	/**
	 * gets the name
	 * @return the name field
	 */
	public String getName(){
		return this.name;
	}
}
