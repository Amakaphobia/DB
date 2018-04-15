package replacers;
import java.util.function.BiFunction;

import statement.A_SetValueClause;
import statement.A_WhereClause;
import statement.E_ReplacementStrategies;
import statement.SQLInputValidation;
import statement.Statement;

/**
 * Class used if the delimiter is %i and nothing fancy is going on
 * @author Dave
 *
 */
public class StandartReplacer extends A_ReplacementStrategy {

	/**Constructor*/
	public StandartReplacer() {
		this.strategy = E_ReplacementStrategies.Standard;
	}
	
	@Override
	public BiFunction<Statement,String, String> getReplacer() {
		return (statement,string)->{
			StringBuilder CurrentString = new StringBuilder(string);
			
			for(A_SetValueClause e : statement.getUpdateValues()) {
				CurrentString = this.rePlaceNext(CurrentString, e.getValue().getValue().toString());
			}
			
			for(A_WhereClause e : statement.getWhereList()) {
				CurrentString = this.rePlaceNext(CurrentString, e.getValue().getValue().toString());
			}						
	
			return CurrentString.toString();			
		};
	}
	
	/**
	 * Internal Method used to replace the next delimiter
	 * @param replacee The stringbuild containing the thing to be replaced
	 * @param replacer the thing you want to replace it with
	 * @return the new StringBuilder
	 */
	private StringBuilder rePlaceNext(StringBuilder replacee, String replacer) {
		StringBuilder ret = new StringBuilder(replacee.toString());
		int index = ret.indexOf(SQLInputValidation.delimiter);
		if(index > 0)
			ret.replace(index, 
					index + SQLInputValidation.delimiter.length(), 
					replacer);
		return ret;
	}
}


