package subAssemblers.Basic;

import java.util.function.Function;

import statement.Statement;
import subAssemblers.A_SubAssembler;
import subAssemblers.E_Assemblertypes;

/**
 * Class used to Assemble Tablenames
 * @author Dave
 *
 */
public class TableNameAssembler extends A_SubAssembler 
{
	/**Constructor*/
	public TableNameAssembler() {
		super(E_Assemblertypes.TABLENAME);
	}
	
	@Override
	protected Function<Statement, String> getAssembler() {
		return input ->{
			return input.getTableNameList().get(0).getName();
		};
	}

}
