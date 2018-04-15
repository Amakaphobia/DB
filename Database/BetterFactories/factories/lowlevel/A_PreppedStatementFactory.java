package factories.lowlevel;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;
import dbmodel.DatabaseModel;
import exceptions.ClauseNotSupported;
import exceptions.ColumnNotKnown;
import exceptions.IllegalClauseValue;
import exceptions.TableNotKnown;
import statement.E_WhereType;
import statement.PreppedSetValueClause;
import statement.PreppedWhereClause;
import statement.Statement;

/**
 * Abstract BaseClass for Factories used to create prepared Statements
 * @author Dave
 *
 */
public abstract class A_PreppedStatementFactory extends A_BasicStatementFactory {

	/**
	 * Constructor
	 * @param Dbm the database model to be used
	 */
	public A_PreppedStatementFactory(DatabaseModel Dbm) {
		super(Dbm);
	}
	
	@Override
	public <T> I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier ciP, E_WhereType typeP, T valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported {
		this.toBuild.addWhereClause(new PreppedWhereClause(andP, ciP, typeP, new GenericContainer<String>(valueP.toString())));
		return this;
	}
	
	@Override
	public I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier colP, E_WhereType typeP, Statement valueP)
			throws TableNotKnown, ColumnNotKnown, IllegalClauseValue, ClauseNotSupported {
		this.toBuild.addWhereClause(new PreppedWhereClause(andP, colP, typeP, valueP));		
		return this;
	}
	
	@Override
	public I_StatementFactory addValue(ColumnIdentifier ci, String value)
			throws ColumnNotKnown, TableNotKnown, ClauseNotSupported {
		this.toBuild.addUpdateValue(new PreppedSetValueClause(ci, new GenericContainer<String>(value)));
		return this;
	}

}
