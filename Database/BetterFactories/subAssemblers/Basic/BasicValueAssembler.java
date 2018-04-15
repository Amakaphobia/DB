package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to assemble insert values 
 * @author Dave
 *
 */
public class BasicValueAssembler extends A_SubAssembler 
{
	/**Constructor*/
	public BasicValueAssembler() {
		super(E_Assemblertypes.BASICVALUES);
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
						.map(value-> value.getValue().getValue().toString())
						.collect(joining(", ")))
				.append(")");
			
			
			return strb.toString();
		};
	}

}
