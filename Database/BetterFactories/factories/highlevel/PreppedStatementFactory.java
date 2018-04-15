package factories.highlevel;

import dbmodel.DatabaseModel;
import factories.lowlevel.A_PreppedStatementFactory;
import factories.lowlevel.E_SQLType;
import factories.lowlevel.I_StatementFactory;
import statement.Statement;
import superAssemblers.PreppedSuperAssemblyFactory;

/**
 * Factory used to create prepared Statements
 * @author Dave
 *
 */
public class PreppedStatementFactory extends A_PreppedStatementFactory {
	/**
	 * Constructor
	 * @param dbm the used databse model
	 */
	public PreppedStatementFactory(DatabaseModel dbm) {
		super(dbm);
	}

	@Override
	public I_StatementFactory prepareNewStatement(E_SQLType type) {
		PreppedSuperAssemblyFactory fac = new PreppedSuperAssemblyFactory(type);
		this.toBuild = new Statement(fac.getAssembler());
		return this;
	}
}
