package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to assemble the set clause in update statements
 * @author Dave
 *
 */
public class BasicSetAssembler extends A_SubAssembler{

	/**Constructor*/
	public BasicSetAssembler() {
		super(E_Assemblertypes.BASICSET);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			StringBuilder strb = new StringBuilder("SET ");
			strb.append(
				input.getUpdateValues().stream()
								.map(val -> String.format("%s = %s", 
															val.getColumn().getColumnName(), 
															val.getValue().getValue().toString()))
								.collect(joining(", ")));			
			
			return strb.toString();
		};
	}
	
}
