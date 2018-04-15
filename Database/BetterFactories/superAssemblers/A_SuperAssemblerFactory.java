package superAssemblers;

import factories.lowlevel.E_SQLType;

/**
 * Abstract BaseClass for SuperAssemblers
 * @author Dave
 *
 */
public abstract class A_SuperAssemblerFactory 
{
	/**the SQL-Type for that Assembler*/
	protected E_SQLType type;
	/**the SuperAssembler you want to build*/
	protected A_SuperAssembler Assembler;
	
	/**
	 * Constructor
	 * @param type the SQLType of the Statement you want an Assembler for
	 */
	public A_SuperAssemblerFactory(E_SQLType type) {
		this.type = type;
	}
	
	/**
	 * Method used to get the Assembler
	 * @return the build Assembler
	 */
	public A_SuperAssembler getAssembler() {
		return this.buildAssembler(this.type); 
	}
	
	/**
	 * internal method that is used to build the Assembler 
	 * @param type the type of Sql-Statement you want to assemble
	 * @return the build Super Assembler
	 */
	protected abstract A_SuperAssembler buildAssembler(E_SQLType type);
}
