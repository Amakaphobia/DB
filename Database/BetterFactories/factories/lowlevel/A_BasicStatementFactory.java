package factories.lowlevel;

import boxes.GenericContainer;
import dbmodel.ColumnIdentifier;
import dbmodel.DatabaseModel;
import dbmodel.TableIdentifier;
import exceptions.ClauseNotSupported;
import exceptions.ColumnAlreadyKnown;
import exceptions.ColumnNotKnown;
import exceptions.IllegalClauseValue;
import exceptions.TableAlreadyKnown;
import exceptions.TableNotKnown;
import statement.E_WhereType;
import statement.Statement;
import statement.BasicSetValueClause;
import statement.BasicWhereClause;

/**
 * Abstract BaseClass for unprepared Statement Factories
 * @author Dave
 *
 */
public abstract class A_BasicStatementFactory implements I_StatementFactory{
	
	/**the DatabaseModel of your connection*/
	protected DatabaseModel Dbm;
	/**the statement that is being build*/
	protected Statement toBuild;
	
	/**
	 * Constructor
	 * @param Dbm the used database model
	 */
	public A_BasicStatementFactory(DatabaseModel Dbm)
	{		
		this.Dbm = Dbm;
	}	
	
	@Override
	public Statement getStatement(){
		return this.toBuild;
	}
	
	@Override
	public DatabaseModel getDbm(){
		return this.Dbm;
	}

	@Override
	public I_StatementFactory addOrderBy(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported {
		this.toBuild.addOrderBy(this.Dbm.findColumn(ciP));
		return this;
	}
	
	@Override
	public I_StatementFactory setOrderByDescending() throws ClauseNotSupported {
		this.toBuild.setOrderByDescending();
		return this;
	}

	@Override
	public I_StatementFactory addGroupBy(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported {
		this.toBuild.addGroupBy(this.Dbm.findColumn(ciP));
		return this;
	}

	@Override
	public I_StatementFactory addSelectionColumn(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported {
		this.toBuild.addSelection(this.Dbm.findColumn(ciP));
		return this;
	}

	@Override
	public I_StatementFactory addSelectionTable(TableIdentifier tiP) throws TableNotKnown, ClauseNotSupported {
		this.toBuild.addTableName(this.Dbm.findTable(tiP));
		return this;
	}

	@Override
	public I_StatementFactory addSelectionTable(Statement subquerryP, String tableName, String shnameP)
			throws ColumnAlreadyKnown, ClauseNotSupported {
		try{
			this.Dbm.addTable(subquerryP, tableName, shnameP);
		}catch(TableAlreadyKnown e) {}
		
		this.toBuild.addTableName(subquerryP, tableName, shnameP);
		return this;
	}

	@Override
	public I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier colP, E_WhereType typeP, Statement valueP)
			throws TableNotKnown, ColumnNotKnown, IllegalClauseValue, ClauseNotSupported {
		this.toBuild.addWhereClause(new BasicWhereClause(andP, colP, typeP, valueP));		
		return this;
	}

	@Override
	public <T> I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier ciP, E_WhereType typeP, T valueP)
			throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported {
		this.toBuild.addWhereClause(new BasicWhereClause(ciP, typeP, new GenericContainer<T>(valueP)));
		return this;
	}
	
	@Override
	public I_StatementFactory addValue(ColumnIdentifier ci, String value) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported {
		this.toBuild.addUpdateValue(new BasicSetValueClause(ci, new GenericContainer<String>(value)));
		return this;
	}
}
