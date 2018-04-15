package statement;

import java.util.List;
import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;
import exceptions.IllegalClauseValue;

/**
 * Class used to Hold the different Where clauses a Statement can contain with sanitizing userinputs
 * @author Dave
 *
 */
public class PreppedWhereClause extends A_WhereClause {

	/**
	 * Constructor
	 * @param ciP column to be compared
	 * @param typeP type of comparison
	 * @param subquerryP Table to be used
	 */
	public PreppedWhereClause(ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP) {
		super(ciP, typeP, subquerryP);
	}
	/**
	 * Constructor
	 * @param andP boolean to determine if its a And comparison
	 * @param ciP column to be compared
	 * @param typeP type of comparison
	 * @param subquerryP Table to be used
	 */
	public PreppedWhereClause(boolean andP,ColumnIdentifier ciP, E_WhereType typeP, Statement subquerryP) {
		super(andP, ciP, typeP, subquerryP);
	}
	
	/**
	 * Constructor
	 * @param andP boolean to determine if its a And comparison
	 * @param columnP column to be compared
	 * @param typeP type of comparison
	 * @param valueP value thats getting compared
	 * @throws IllegalClauseValue if you violated input rules
	 */
	public PreppedWhereClause(boolean andP, ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<String> valueP) throws IllegalClauseValue {
		super(andP, columnP, typeP, valueP);
		
		List<String> attacker = this.checkInput(valueP.getValue());
		if(attacker.size() > 0)
			throw new IllegalClauseValue(attacker.toString());		
	}

	/**
	 * Constructor
	 * @param columnP column to be compared
	 * @param typeP type of comparison
	 * @param valueP value thats getting compared
	 * @throws IllegalClauseValue if you violated input rules
	 */
	public PreppedWhereClause(ColumnIdentifier columnP, E_WhereType typeP, GenericContainer<String> valueP) throws IllegalClauseValue {
		super(columnP, typeP, valueP);
		
		List<String> attacker = this.checkInput(valueP.getValue());
		if(attacker.size() > 0)
			throw new IllegalClauseValue(attacker.toString());			
	}
	
	/**
	 * Checks the input for forbidden sequences
	 * @param input the input to be checked
	 * @return a list containing all violations
	 */
	private List<String> checkInput(String input)
	{
		return SQLInputValidation.check(input);
	}
	
	/**
	 * used to get the value as a String
	 * @return a String containing the value
	 */
	public String getValueString(){
		return this.value.getValue().toString();
	}

	@Override
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		
		strb.append(this.column.toString())
		    .append(this.type.getKey());
		
		strb.append(this.value.getValue() instanceof Statement 
						? String.format("(%s)", SQLInputValidation.delimiter)
						: SQLInputValidation.delimiter);
		return strb.toString();
	}
	@Override
	public String toStringSingle() {
		StringBuilder strb = new StringBuilder();
		
		strb.append(this.column.getColumnName())
		    .append(this.type.getKey());
		
		strb.append(this.value.getValue() instanceof Statement 
						? String.format("(%s)", SQLInputValidation.delimiter)
						: SQLInputValidation.delimiter);
		return strb.toString();
	}
}