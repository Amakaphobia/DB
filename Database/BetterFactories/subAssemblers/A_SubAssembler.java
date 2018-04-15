package subAssemblers;
import java.util.function.Function;

import statement.Statement;
import subAssemblers.Basic.BasicSetAssembler;
import subAssemblers.Basic.BasicValueAssembler;
import subAssemblers.Basic.BasicWhereClauseAssembler;
import subAssemblers.Basic.GroupByAssembler;
import subAssemblers.Basic.OrderByAssembler;
import subAssemblers.Basic.SelectionColAssembler;
import subAssemblers.Basic.SelectionTabAssembler;
import subAssemblers.Basic.TableNameAssembler;
import subAssemblers.Prepped.PreppedSetAssembler;
import subAssemblers.Prepped.PreppedValueAssembler;
import subAssemblers.Prepped.PreppedWhereClauseAssembler;

/**
 * Abstract BaseClass for Subassemblers. SubAssemblers are used to create the distinct parts of a SqlStatement
 * @author Dave
 *
 */
public abstract class A_SubAssembler{

	/**A field holding a E_Assemblertypes Enum that uniquely defines the Assembler*/
	protected E_Assemblertypes name;
	
	/**
	 * Constructor
	 * it uses the WITHOUT replacement strategy
	 * @param name the type of Assembler this is
	 */
	public A_SubAssembler(E_Assemblertypes name)
	{
		this.name = name;
	}
		
	/**
	 * Method used to build the chosen SubAssembler
	 * @param type the type of SubAssembler you want to build
	 * @return the readied SubAssembler
	 */
	public static A_SubAssembler getAssembler(E_Assemblertypes type){
		A_SubAssembler ret;
		
		switch(type){
		case SELECTIONCOL: 
			ret = new SelectionColAssembler();
			break;
		case SELECTIONTAB:
			ret = new SelectionTabAssembler();
			break;
		case BASICWHERE:
			ret = new BasicWhereClauseAssembler();
			break;
		case PREPPEDWHERE:
			ret = new PreppedWhereClauseAssembler();
			break;
		case GROUPBY:
			 ret = new GroupByAssembler();
			break;
		case ORDERBY:
			ret = new OrderByAssembler();
			break;
		case TABLENAME:
			 ret = new TableNameAssembler();
			break;
		case BASICVALUES:
			ret = new BasicValueAssembler();
			break;
		case PREPPEDVALUES:
			ret = new PreppedValueAssembler();
			break;
		case BASICSET:
			ret = new BasicSetAssembler();
			break;
		case PREPPEDSET:
			ret = new PreppedSetAssembler();
			break;
		default:
			throw new RuntimeException("That is not implemented yet");
		}		
		return ret;
	}

	/**
	 * Method used to get the Enum defining the Assembler
	 * @return the name field
	 */
	public E_Assemblertypes getName() {
		return this.name;
	}
	
	/**
	 * Method used to assemble the the 
	 * @param input the Statement you want to Assemble
	 * @return the string of the assembled Statement
	 */
	public String assemble(Statement input){
		
		return this.getAssembler().apply(input);
	}

	/**
	 * Method used internally to call the assemble method
	 * @return a Function with input statement and output String. describing how to assemble the sqlstatement
	 */
	protected abstract Function<Statement, String> getAssembler();
	
}
