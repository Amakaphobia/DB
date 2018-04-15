package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble selected Tables
 * @author Dave
 *
 */
public class SelectionTabAssembler extends A_SubAssembler {

	/**Constructor*/
	public SelectionTabAssembler() {
		super(E_Assemblertypes.SELECTIONTAB);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			return				
				input.getTableNameList().stream()
				.map(tableName -> tableName.toString())
				.collect(joining(", "));
		};
	}

}
