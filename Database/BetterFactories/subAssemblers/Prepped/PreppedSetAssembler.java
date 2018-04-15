package subAssemblers.Prepped;

import static java.util.stream.Collectors.joining;

import java.util.function.Function;

import statement.SQLInputValidation;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble prepared Update Value clauses
 * @author Dave
 *
 */
public class PreppedSetAssembler  extends A_SubAssembler 
{
	/**Constructor*/
	public PreppedSetAssembler() {
		super(E_Assemblertypes.PREPPEDSET);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			StringBuilder strb = new StringBuilder("SET ");
			strb.append(
				input.getUpdateValues().stream()
								.map(val -> String.format("%s = %s", 
															val.getColumn().getColumnName(), 
															SQLInputValidation.delimiter))
								.collect(joining(", ")));			
			
			return strb.toString();
		};
	}

}
