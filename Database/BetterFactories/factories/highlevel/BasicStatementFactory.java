package factories.highlevel;

import dbmodel.DatabaseModel;
import factories.lowlevel.A_BasicStatementFactory;
import factories.lowlevel.E_SQLType;
import factories.lowlevel.I_StatementFactory;
import statement.Statement;
import superAssemblers.BasicSuperAssemblerFactory;

/**
 * Factory Class used to create Statements without SyntaxSanitzing
 * @author Dave
 *
 */
public class BasicStatementFactory extends A_BasicStatementFactory {

	/**
	 * Constructor
	 * @param Dbm DatabaseModel used for this statement
	 */
	public BasicStatementFactory(DatabaseModel Dbm) {
		super(Dbm);
	}

	@Override
	public I_StatementFactory prepareNewStatement(E_SQLType type) {
		BasicSuperAssemblerFactory fac = new BasicSuperAssemblerFactory(type); 
		this.toBuild = new Statement(fac.getAssembler());
		return this;
	}
}
