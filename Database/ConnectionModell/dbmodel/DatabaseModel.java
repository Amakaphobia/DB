package dbmodel;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.joining;
import exceptions.ColumnAlreadyKnown;
import exceptions.ColumnNotKnown;
import exceptions.TableAlreadyKnown;
import exceptions.TableNotKnown;
import statement.Statement;

/**
 * Class that initiates and holds the models of the Database, its tables and their columns
 * @author Dave
 *
 */
public class DatabaseModel 
{
	/**Linked List containing the known TableIdentifiers*/
	private List<TableIdentifier> tables;
	/**Holds the reading Strategy that initiates the Table models*/
	private I_DBReader DbReader;
	
	/**
	 * Constructor
	 * @param DbReaderP reading Strategy
	 */
	public DatabaseModel(I_DBReader DbReaderP)
	{
		this.DbReader = DbReaderP;
		this.tables = this.readTableInformation();
	}
	
	/**
	 * Method used to find a TableIdentifier given a TableName
	 * @param tableNameP the TableName you want to search
	 * @return TableIdentifier of the found table
	 * @throws TableNotKnown if there is no such table
	 */
	public TableIdentifier findTable(String tableNameP) throws TableNotKnown
	{
		Optional<TableIdentifier> testTable = 
				this.tables.stream()
					.parallel()
					.filter(tab -> tab.getName().equalsIgnoreCase(tableNameP))
					.findAny();
					
					
		if(!testTable.isPresent())
			throw new TableNotKnown(tableNameP);
		
		return testTable.get();
	}
	
	/**
	 * Method used to find a TableIdentifier given a TableIdentifier
	 * @param tableP the tableIdentifier
	 * @return the found table
	 * @throws TableNotKnown if no such table exists
	 */
	public TableIdentifier findTable(TableIdentifier tableP) throws TableNotKnown
	{
		Optional<TableIdentifier> testForTable = 
				this.tables.stream()
				.parallel()
				.filter(tab -> tab.equals(tableP))
				.findAny();
		
		if(testForTable.isPresent())
			return testForTable.get();
		else
			throw new TableNotKnown(tableP.getName());
	}
	
	/**
	 * Method used to find a ColumnIdentifier given a TableName and a ColumnName
	 * @param tableNameP the TableName you want to search
	 * @param colNameP the ColumnName you want to search
	 * @return ColumnIdentifier containing the found column
	 * @throws ColumnNotKnown if there is no such column known
	 * @throws TableNotKnown if there is no such table
	 */
	public ColumnIdentifier findColumn(String tableNameP, String colNameP) throws ColumnNotKnown, TableNotKnown
	{
		TableIdentifier testTable = this.findTable(tableNameP);
		Optional<ColumnIdentifier>  testCol = 
				testTable.getColumns().stream()
					.parallel()
					.filter(col -> col.getColumnName().equalsIgnoreCase(colNameP))
					.findAny();
		
		if(!testCol.isPresent())		
			throw new ColumnNotKnown(String.format(" %s.%s ", tableNameP, colNameP));
		
		return testCol.get();
	}
	
	/**
	 * Method used to find a ColumnIdentifier given a ColumnIdentifier
	 * @param colP the ColumnIdentifier
	 * @return the found column
	 * @throws TableNotKnown if the used table is not known
	 * @throws ColumnNotKnown if the used column isnt part of the table
	 */
	public ColumnIdentifier findColumn(ColumnIdentifier colP) throws TableNotKnown, ColumnNotKnown
	{
		TableIdentifier testTable = this.findTable(colP.getTable());
		
		Optional<ColumnIdentifier> testCol = 
			testTable.getColumns()
					.stream()
					.parallel()
					.filter(col -> col.equals(colP))
					.findAny();
		if(!testCol.isPresent())
			throw new ColumnNotKnown(colP);
		
		return testCol.get();
	}
	
	/**
	 * Internal Adapter Method to activate the reading strategy
	 * @return List(tableIdentifier) containing the Information of all known tables
	 */
	private List<TableIdentifier> readTableInformation()
	{
		return this.DbReader.readTableInformation();
	}
			
	/**
	 * Method to check if a TableName is known
	 * @param tableNameP the name you want to check
	 * @return true if found false if not
	 */
	public boolean containsTable(String tableNameP)
	{
		return this.tables.stream()
							.parallel()
							.filter(tab -> tab.getName().equalsIgnoreCase(tableNameP))
							.findAny()
							.isPresent();
	}
	
	/**
	 * Method to check if a TableIdentifier is known
	 * @param tiP the tableIdentifier you want to check
	 * @return true if found false if not
	 */
	public boolean containsTable(TableIdentifier tiP)
	{
		return this.tables.stream()
							.parallel()
							.filter(tab -> tab.equals(tiP))
							.findAny()
							.isPresent();
	}
	
	/**
	 * Method to check if a ColumnIdentifier is known
	 * @param ColNameP the name of the searched column
	 * @param TableNameP the table you want to search
	 * @return true if found false if not
	 * @throws TableNotKnown if the searched table is unknown
	 */
	public boolean containsColumn(String ColNameP, String TableNameP) throws TableNotKnown
	{
		TableIdentifier ti = this.findTable(TableNameP);
		return ti.getColumns().stream()
					.filter(col -> col.getColumnName().equals(ColNameP))
					.findAny()
					.isPresent();
	}
	
	/**
	 * Method to check if a ColumnIdentifier is known
	 * @param ciP the columnIdentifier you want to check
	 * @return true if found false if not
	 * @throws TableNotKnown if the table used by the column is not known to the Databasemodel
	 */
	public boolean containsColumn(ColumnIdentifier ciP) throws TableNotKnown
	{
		return this.containsColumn(ciP.getColumnName(), ciP.getTable().getName());
	}
	
	/**
	 * Method used to add a new Table to the known table list
	 * @param ti the new table you want to add
	 * @throws TableAlreadyKnown if this table is already known
	 */
	private void addTable(TableIdentifier ti) throws TableAlreadyKnown
	{
		if(this.containsTable(ti))
			throw new TableAlreadyKnown(ti);
		else
			this.tables.add(ti);
	}
	
	/**
	 * Method used to add a new Table to the known table list
	 * @param TableNameP The name of the table
	 * @param TableshtP the short name of the table
	 * @throws TableAlreadyKnown if that table is already known
	 */
	public void addTable(String TableNameP, String TableshtP) throws TableAlreadyKnown
	{
		this.addTable(new TableIdentifier(TableNameP, TableshtP));
	}
	
	/**
	 * Method used to add a new DynamicTable to the known table list 
	 * @param subquerryP the statement used to generate the table
	 * @param tableNameP the given table name
	 * @param shnameP the given shortname
	 * @throws TableAlreadyKnown if such a table is already known
	 * @throws ColumnAlreadyKnown if the table has a duplicate column
	 */
	public void addTable(Statement subquerryP, String tableNameP, String shnameP) throws TableAlreadyKnown, ColumnAlreadyKnown
	{
		this.addTable(new TableIdentifier(subquerryP, tableNameP, shnameP));
	}
	
	/**
	 * Method used to add a column to a known Table
	 * @param tiP the Table you want to add to
	 * @param colNameP the name of the new Column
	 * @throws TableNotKnown If that table is not known
	 * @throws ColumnAlreadyKnown if that column is already known
	 */
	public void addColumnToTable(TableIdentifier tiP, String colNameP) throws TableNotKnown, ColumnAlreadyKnown
	{
		TableIdentifier ti = this.findTable(tiP);
		
		if(ti.containsColumn(colNameP))
			throw new ColumnAlreadyKnown(String.format(" %s.%s ", tiP.getName(),colNameP));

		tiP.addColumn(colNameP);				
	}
	
	/**
	 * Method used to add a column to a known Table
	 * @param tableNameP the TableName you want to add to
	 * @param colNameP the name of the new Column
	 * @throws TableNotKnown If that table is not known
	 * @throws ColumnAlreadyKnown if that column is already known
	 */
	public void addColumnToTable(String tableNameP, String colNameP) throws TableNotKnown, ColumnAlreadyKnown
	{
		TableIdentifier ti = this.findTable(tableNameP);
		
		if(ti.containsColumn(colNameP))
			throw new ColumnAlreadyKnown(String.format("%s.%s", tableNameP,colNameP));

		ti.addColumn(colNameP);
	}
	
	/**
	 * Method used to get the List of known tables
	 * @return LinkedList(TableIdentifier)
	 */
	public List<TableIdentifier> getTables() { return this.tables; }
	
	@Override
	public String toString()
	{
		return this.tables.stream()
						.map(table -> table.getAllColumns())
						.collect(joining(" - "));
	}

	
}
