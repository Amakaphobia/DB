package statement;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;

/**
 * Class used by unprepared update statements
 * @author Dave
 *
 */
public class BasicSetValueClause extends A_SetValueClause{

	/**
	 * Constructor
	 * @param ciP identifies the column you want to update
	 * @param valueP the value you want to use
	 */
	public BasicSetValueClause(ColumnIdentifier ciP, GenericContainer<?> valueP) {
		super(ciP, valueP);
	}

	@Override
	public String getSetClauseString() {
		return String.format("%s = %s", 
								this.column.getColumnName(), 
								this.value.getValue().toString());
	}

}
