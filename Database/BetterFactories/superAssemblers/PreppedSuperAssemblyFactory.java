package superAssemblers;


import factories.lowlevel.E_SQLType;
import superAssemblers.Prepped.*;

/**
 * FactoryClass used to create preparedStatements
 * @author Dave
 *
 */
public class PreppedSuperAssemblyFactory extends A_SuperAssemblerFactory
{
	/**
	 * Constructor
	 * @param type the type of statement you want to create
	 */
	public PreppedSuperAssemblyFactory(E_SQLType type) {
		super(type);
	}

	@Override
	protected A_SuperAssembler buildAssembler(E_SQLType type) {
		A_SuperAssembler ret = null;
		switch(type){
			case SELECT:
				ret = new PreppedSelectAssembler();
				break;
			case INSERT:
				ret = new PreppedInsertAssembler();
				break;
			case DELETE:
				ret = new PreppedDeleteAssembler();
				break;
			case UPDATE:
				ret = new PreppedUpdateAssembler();
				break;
			default: throw new IllegalArgumentException("That is not implemented yet");			
		}
		return ret;
	}

}
