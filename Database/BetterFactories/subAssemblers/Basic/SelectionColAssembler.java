package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble Selected Columns
 * @author Dave
 *
 */
public class SelectionColAssembler extends A_SubAssembler {

	/**Constructor*/
	public SelectionColAssembler() {
		super(E_Assemblertypes.SELECTIONCOL);
	}
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			return	input.getSelectList().size() > 0
					? input.getSelectList().stream()
							.map(select -> select.toString() )
							.collect(joining(", "))
					: " * ";
		};
	}

}
