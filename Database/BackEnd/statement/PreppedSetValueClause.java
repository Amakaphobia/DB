package statement;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;

/**
 * Class used to represent Prepared update or insert values
 * @author Dave
 *
 */
public class PreppedSetValueClause extends A_SetValueClause{

	/**
	 * Constructor
	 * @param ciP the column you want to update
	 * @param valueP the value you want to update
	 */
	public PreppedSetValueClause(ColumnIdentifier ciP, GenericContainer<?> valueP) {
		super(ciP, valueP);
	}

	@Override
	public String getSetClauseString() {
		return String.format("%s = %s", this.column.getColumnName(),SQLInputValidation.delimiter);
	}

}
