package subAssemblers.Basic;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble Order By Clauses
 * @author Dave
 *
 */
public class OrderByAssembler extends A_SubAssembler{
	
	
	/**Constructor*/
	public OrderByAssembler() {
		super(E_Assemblertypes.ORDERBY);
	}

	@Override
	protected Function<Statement, String> getAssembler() {
		return input->{
			if(input.getOrderBy().getOrderByList().size() == 0)
				return "";
			
			StringBuilder strb = new StringBuilder(" ORDER BY ");
			strb.append( 
					input.getOrderBy().getOrderByList().stream()
						.map(col -> String.format("%s.%s", col.getTable().getshName(), col.getColumnName()))
						.collect(joining(", ")))
			
				.append(
					 input.getOrderBy().getIsAsc()
					 	? " ASC " 
					 	: " DESC ");
			
			return strb.toString();
		};

	}
	
	
}
