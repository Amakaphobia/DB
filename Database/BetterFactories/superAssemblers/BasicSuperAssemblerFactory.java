package superAssemblers;

import factories.lowlevel.E_SQLType;
import superAssemblers.Basics.*;

/**
 * FactoryClass for creating SuperAssemblers (Unprepared)
 * @author Dave
 *
 */
public class BasicSuperAssemblerFactory extends A_SuperAssemblerFactory 
{

	/**
	 * Constructor
	 * @param type of the SQL-Statement you want a SuperAssembler for
	 */
	public BasicSuperAssemblerFactory(E_SQLType type) {
		super(type);
	}

	@Override
	protected A_SuperAssembler buildAssembler(E_SQLType type) {
		A_SuperAssembler ret = null;
		switch(type){
			case SELECT:
				ret = new BasicSelectAssembler();
				break;
			case INSERT:
				ret = new BasicInsertAssembler();
				break;
			case DELETE:
				ret = new BasicDeleteAssembler();
				break;
			case UPDATE:
				ret = new BasicUpdateAssembler();
				break;
			default: throw new IllegalArgumentException("That is not implemented yet");			
		}
		return ret;
	}
}
