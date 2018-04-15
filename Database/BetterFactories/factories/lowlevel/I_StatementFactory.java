package factories.lowlevel;
import java.util.List;

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

/**
 * Factory Interface to build Statements
 * @author Dave
 *
 */
public interface I_StatementFactory 
{
	/**
	 * Method used to add a new OrderBy parameter to the Statement
	 * @param tableNameP the TableName of the column
	 * @param colNameP the name of the Column
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addOrderBy(String tableNameP, String colNameP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported	{
		return this.addOrderBy(this.getDbm().findColumn(tableNameP, colNameP));
	}
	
	/**
	 * Method used to add a new OrderBy parameter to the Statement
	 * @param ciP the ColumnIdentifier you want to add
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addOrderBy(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported;
	
	/**
	 * Method used to add a new GroupBy parameter to the Statement
	 * @param tableNameP the TableName of the column
	 * @param colNameP the name of the Column
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addGroupBy(String tableNameP,String colNameP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported{
		return this.addGroupBy(this.getDbm().findColumn(tableNameP, colNameP));
	}
	
	/**
	 * Method used to add a new GroupBy parameter to the Statement
	 * @param ciP the Column you want to add
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if the Table is unknown
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addGroupBy(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported;
	
	/**
	 * Method used to add a new SelectionColumn to the Statement
	 * @param tableNameP the TableName of the column
	 * @param colNameP the name of the Column
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it 
	 */
	public default I_StatementFactory addSelectionColumn(String tableNameP, String colNameP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported{
		return this.addSelectionColumn(this.getDbm().findColumn(tableNameP, colNameP));
	}
	
	/**
	 * Method used to add a new SelectionColumn to the Statement
	 * @param ciP the ColumnIdentifier you want to add
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if the table is unknown
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addSelectionColumn(ColumnIdentifier ciP) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported;
	
	/**
	 * Method used to add a new SelectionTable to the Statement
	 * @param tableNameP the Name of the Table
	 * @return this for chain building
	 * @throws TableNotKnown if there is now such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addSelectionTable(String tableNameP) throws TableNotKnown, ClauseNotSupported{
		return this.addSelectionTable(this.getDbm().findTable(tableNameP));
	}
	
	/**
	 * Method used to add a new SelectionTable to the Statement
	 * @param tiP the TableIdentifier of the Table 
	 * @return this for chain building
	 * @throws TableNotKnown if there is now such table known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addSelectionTable(TableIdentifier tiP) throws TableNotKnown, ClauseNotSupported;
	
	/**
	 * Method used to add a new Selection Table to the Statement using a suquerry
	 * @param subquerryP the subquerry youre using 
	 * @param tableName the tablename you want to give the dynamic table
	 * @param shnameP short name of the table
	 * @return this for chainbuilding
	 * @throws ColumnAlreadyKnown if there alreade such a Column
	 * @throws TableAlreadyKnown if this table is already known
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addSelectionTable(Statement subquerryP, String tableName, String shnameP) throws ColumnAlreadyKnown, TableAlreadyKnown, ClauseNotSupported;
	
	/**
	 * Method used to add a new WhereClause to the Statement (and conditional)
	 * @param colNameP the name of the Column
	 * @param tableNameP the name of the table
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the clause
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addWhereClause(String colNameP, String tableNameP, E_WhereType typeP, Statement valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported{
		return this.addWhereClause(true, colNameP, tableNameP, typeP, valueP);
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement
	 * @param andP true for and false for or
	 * @param colNameP the name of the Column
	 * @param tableNameP the name of the table
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the clause
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addWhereClause(boolean andP, String colNameP, String tableNameP, E_WhereType typeP, Statement valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported{		
		ColumnIdentifier ci = this.getDbm().findColumn(tableNameP, colNameP);	
		return this.addWhereClause(andP, ci, typeP, valueP);		
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement (and conditional)
	 * @param colP Column Identifier of the column
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the statement
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default I_StatementFactory addWhereClause(ColumnIdentifier colP, E_WhereType typeP, Statement valueP) throws TableNotKnown, ColumnNotKnown, IllegalClauseValue, ClauseNotSupported{
		return this.addWhereClause(true, colP, typeP, valueP);
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement
	 * @param andP true for and false for or
	 * @param colP Column Identifier of the column
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the statement
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier colP, E_WhereType typeP, Statement valueP) throws TableNotKnown, ColumnNotKnown, IllegalClauseValue, ClauseNotSupported;
	
	
	
	/**
	 * Method used to add a new WhereClause to the Statement (and conditional)
	 * @param colNameP the name of the Column
	 * @param tableNameP the name of the table
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @param <T> the type of the value you want to compare against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the clause
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default <T> I_StatementFactory addWhereClause(String colNameP, String tableNameP, E_WhereType typeP, T valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported{
		return this.addWhereClause(true, colNameP, tableNameP, typeP, valueP);
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement
	 * @param andP true for and false for or
	 * @param colNameP the name of the Column
	 * @param tableNameP the name of the table
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @param <T> the type of the value you want to compare against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the clause
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default <T> I_StatementFactory addWhereClause(boolean andP, String colNameP, String tableNameP, E_WhereType typeP, T valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported{	
		ColumnIdentifier ci = this.getDbm().findColumn(tableNameP,colNameP);
		return this.addWhereClause(andP, ci, typeP, valueP);		
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement (and conditional)
	 * @param ciP the ColumnIdentifier you want to check
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @param <T> the type of the value you want to compare against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table known
	 * @throws IllegalClauseValue if you attack the statement
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public default <T> I_StatementFactory addWhereClause(ColumnIdentifier ciP, E_WhereType typeP, T valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported{
		return this.addWhereClause(true, ciP, typeP, valueP);
	}
	
	/**
	 * Method used to add a new WhereClause to the Statement
	 * @param andP true for and false for or
	 * @param ciP the ColumnIdentifier you want to check
	 * @param typeP the type of comparison (static integers in where clause class)
	 * @param valueP the value you want to check against
	 * @param <T> the type of the value you want to compare against
	 * @return this for chain building
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown  if there is no such table known
	 * @throws IllegalClauseValue if you attack the statement
	 * @throws ClauseNotSupported if you try to use that clause on a statements that cant handle it
	 */
	public <T> I_StatementFactory addWhereClause(boolean andP, ColumnIdentifier ciP, E_WhereType typeP, T valueP) throws ColumnNotKnown, TableNotKnown, IllegalClauseValue, ClauseNotSupported;

	/**
	 * Method used to add a value for update
	 * @param tablename the tablename
	 * @param Columnname the columnname
	 * @param value the value to insert
	 * @return this for chainbuilding
	 * @throws ColumnNotKnown if the column isnt known
	 * @throws TableNotKnown if the table is unknown
	 * @throws ClauseNotSupported if that operation is not supported
	 */
	public default I_StatementFactory addValue(String tablename, String Columnname, String value) throws ColumnNotKnown, TableNotKnown, ClauseNotSupported{
		ColumnIdentifier ci = this.getDbm().findColumn(tablename, Columnname);
		return this.addValue(ci, value);
	}
	
	/**
	 * Method used to add a value for insertion
	 * @param ci the column you want to add into
	 * @param value the value to insert
	 * @return this for chainbuilding
	 * @throws ColumnNotKnown if the column isnt known
	 * @throws TableNotKnown if the table is unknown
	 * @throws ClauseNotSupported if that operation is not supported
	 */
	public I_StatementFactory addValue(ColumnIdentifier ci, String value)throws ColumnNotKnown, TableNotKnown, ClauseNotSupported;
	
	/**
	 * Used to instanciate a new Statement
	 * @param type the statementtype you want to create
	 * @return this for chainbuilding
	 */
	public I_StatementFactory prepareNewStatement(E_SQLType type);

	/**
	 * Method used to setOrderBy order to Descending
	 * @return this for chain building
	 * @throws ClauseNotSupported if that operation is not supported
	 */
	public I_StatementFactory setOrderByDescending() throws ClauseNotSupported;
	
	/**
	 * Used to build the StatementObject
	 * @return the Statement Object
	 */
	public Statement getStatement();
	
	/**
	 * used to access the databasemodell
	 * @return the dbm
	 */
	abstract DatabaseModel getDbm();
	
	/**
	 * Method used to get all used Collumns of the Statement
	 * @return LinkedList of Type ColumnIdentifier
	 */
	public default List<ColumnIdentifier> getUsedColls(){
		return this.getStatement().getUsedCols();
	}
	
	/**
	 * Method used to get the Statement String
	 * @return String Containing the build SQL Statement
	 */
	public default String build() {
		return this.getStatement().toString();
	}
}
