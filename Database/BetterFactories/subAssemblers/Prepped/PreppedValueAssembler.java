package subAssemblers.Prepped;

import static java.util.stream.Collectors.joining;
import java.util.function.Function;

import statement.SQLInputValidation;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble Prepared insert statements value clause
 * @author Dave
 *
 */
public class PreppedValueAssembler extends A_SubAssembler 
{
	/**Constructor*/
	public PreppedValueAssembler() {
		super(E_Assemblertypes.PREPPEDVALUES);
	}

	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			StringBuilder strb = new StringBuilder(" ( ");
			strb.append(
					input.getUpdateValues().stream()
						.map(value -> value.getColumn().getColumnName())
						.collect(joining(", ")))
				.append(" ) values ( ")
				.append(
					input.getUpdateValues().stream()
						.map(pair-> SQLInputValidation.delimiter)
						.collect(joining(", ")))
				.append(")");
			
			
			return strb.toString();
		};
	}
}
