package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble Group By Clauses
 * @author Dave
 *
 */
public class GroupByAssembler extends A_SubAssembler 
{
	/**Constructor*/
	public GroupByAssembler() {
		super(E_Assemblertypes.GROUPBY);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			if(input.getGroupBy().getColumns().size() == 0)
				return "";		
			
			StringBuilder strb = new StringBuilder(" GROUP BY ");
			
			strb.append(
					input.getGroupBy().getColumns().stream()
					.map(col -> String.format("%s.%s", col.getTable().getshName(), col.getColumnName()))
					.collect(joining(", ")));
			return strb.toString();
		};
	}

}
