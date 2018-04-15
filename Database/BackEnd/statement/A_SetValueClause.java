package statement;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;

/**
 * Abstract BaseClass for Setvalue Clauses in update statements
 * @author Dave
 *
 */
public abstract class A_SetValueClause 
{
	/**holds the column you want to update*/
	protected ColumnIdentifier column;
	/**holds the value you want to update*/
	protected GenericContainer<?> value;
	
	/**
	 * Constructor
	 * @param ciP the column you want to update
	 * @param valueP the value you want to use
	 */
	public A_SetValueClause(ColumnIdentifier ciP, GenericContainer<?> valueP) {
		this.column = ciP;
		this.value = valueP;
	}

	/**
	 * returns the Column used
	 * @return the columnidentifier field column
	 */
	public ColumnIdentifier getColumn() {
		return this.column;
	}

	/**
	 * return the value container
	 * @return the value field
	 */
	public GenericContainer<?> getValue() {
		return this.value;
	}
	
	/**
	 * Method used to get the column = value string
	 * @return the string used in the set value clause
	 */
	public abstract String getSetClauseString();
}
