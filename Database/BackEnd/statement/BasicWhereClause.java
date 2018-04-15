package statement;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;

/**
 * Class used to Hold the different Where clauses a Statement can contain
 * @author Dave
 *
 */
public class BasicWhereClause extends A_WhereClause
{	
	/**
	 * Constructor
	 * @param andP true for and, false for or
	 * @param columnP column to compare
	 * @param typeP type of comparison
	 * @param valueP value for comparison
	 */
	public BasicWhereClause(boolean andP, ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<?> valueP) 
	{
		super(andP, columnP, typeP, valueP);
	}

	/**
	 * Constructor
	 * @param ciP column to compare
	 * @param typeP type of comparison
	 * @param subquerryP Querry used to generate table
	 */
	public BasicWhereClause(ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP)
	{
		super(ciP, typeP, subquerryP);
	}
	
	/**
	 * Constructor
	 * @param andP true for and, false for or
	 * @param ciP column to compare
	 * @param typeP type of comparison
	 * @param subquerryP Querry used to generate table
	 */
	public BasicWhereClause(boolean andP, ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP)
	{
		super(andP, ciP, typeP, subquerryP);
	}
	
	/**
	 * Constructor
	 * @param columnP column to compare
	 * @param typeP type of comparison
	 * @param valueP value for comparison
	 */
	public BasicWhereClause(ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<?> valueP)
	{
		super(columnP,typeP,valueP);
	}
	
	@Override
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		
		strb.append(this.column.toString())
		    .append(this.type.getKey());
		
		strb.append(
				this.value.getValue() instanceof Statement
				? String.format("( %s )", value.getValue().toString())
				: value.getValue().toString());
		return strb.toString();
	}

	@Override
	public String toStringSingle() {
		StringBuilder strb = new StringBuilder();
		
		strb.append(this.column.getColumnName())
		    .append(this.type.getKey());

		strb.append(
				this.value.getValue() instanceof Statement
				? String.format("( %s )", value.getValue().toString())
				: value.getValue().toString());
		return strb.toString();
	}
	
}
