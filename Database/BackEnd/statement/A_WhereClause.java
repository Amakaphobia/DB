package statement;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;

/**
 * Abstract baseclass for Whereclauses
 * @author Dave
 *
 */
public abstract class A_WhereClause 
{
	/**differentiates between and and or*/
	protected boolean and;
	/**identifies the type of comparison*/
	protected E_WhereType type;
	/**Holds the column that gets compared*/
	protected ColumnIdentifier column;
	/**holds the value it gets compared against*/
	protected GenericContainer<?> value;

	/**
	 * Constructor
	 * @param andP true for and, false for or
	 * @param ciP column to compare
	 * @param typeP type of comparison
	 * @param subquerryP Querry used to generate table
	 */
	public A_WhereClause(boolean andP, ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP)
	{
		this.and = andP;
		this.type = typeP;
		this.column = ciP;
		this.value = new GenericContainer<Statement>(subquerryP);
	}
	/**
	 * Constructor
	 * @param ciP column to compare
	 * @param typeP type of comparison
	 * @param subquerryP Querry used to generate table
	 */
	public A_WhereClause(ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP)
	{
		this.and = true;
		this.type = typeP;
		this.column = ciP;
		this.value = new GenericContainer<Statement>(subquerryP);
	}
	
	/**
	 * Constructor
	 * @param andP true for and, false for or
	 * @param columnP column to compare
	 * @param typeP type of comparison
	 * @param valueP value for comparison
	 */
	public A_WhereClause(boolean andP, ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<?> valueP)
	{
		this.and = andP;
		this.type = typeP;
		this.column = columnP;
		this.value = valueP;
	}
	
	/**
	 * Constructor
	 * @param columnP column to compare
	 * @param typeP type of comparison
	 * @param valueP value for comparison
	 */
	public A_WhereClause(ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<?> valueP)
	{
		this.and = true;
		this.type = typeP;
		this.column = columnP;
		this.value = valueP;
	}
	
	/**
	 * Used to Access the and value of the where clause
	 * @return boolean true for and, false for or
	 */
	public boolean getAnd(){ return this.and; }
	
	/**
	 * Used to access the columnidentifier
	 * @return the column identifier of this whereclause
	 */
	public ColumnIdentifier getCol() { return this.column; }
	
	/**
	 * Used to get the Value Container
	 * @return the ValueContainer associated with this whereclause
	 */
	public GenericContainer<?> getValue() { return this.value; }
	
	/**
	 * Method used to get the single table string
	 * @return string where clause without tableshort name usage
	 */
	public abstract String toStringSingle();
}
