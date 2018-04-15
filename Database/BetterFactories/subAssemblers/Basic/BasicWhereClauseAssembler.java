package subAssemblers.Basic;

import java.util.function.Function;

import statement.A_WhereClause;
import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to assemble Whereclauses
 * @author Dave
 *
 */
public class BasicWhereClauseAssembler extends A_SubAssembler {

	/**Constructor*/
	public BasicWhereClauseAssembler() {
		super(E_Assemblertypes.BASICWHERE);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input -> {
			if(input.getWhereList().size() == 0)
				return "";
				
			StringBuilder strb = new StringBuilder(" WHERE ");
			boolean first = true;
			
			for(A_WhereClause e : input.getWhereList())
			{
				if(!first)
				{
					if(e.getAnd())
						strb.append(" and ");
					else
						strb.append(" or ");		
				}
				if(input.getTableNameList().size() > 1)
					strb.append(e.toString());
				else
					strb.append(e.toStringSingle());
				first = false;
			}		
			return strb.toString();
		};
	}

}
