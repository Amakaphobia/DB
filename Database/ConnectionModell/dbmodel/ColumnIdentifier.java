package dbmodel;

/**
 * Class Used to hold column names and their table short names in SQL statements
 * @author Dave
 *
 */
public class ColumnIdentifier 
{
	/**holds the short name of the table*/
	private TableIdentifier Table;
	/**holds the column name*/
	private String columnName;
	
	/**
	 * Constructor
	 * @param tableP the table of the column
	 * @param columnNameP the column name of the table
	 */
	public ColumnIdentifier(TableIdentifier tableP, String columnNameP)
	{
		this.Table = tableP;
		this.columnName = columnNameP.toLowerCase();
	}	
	
	/**
	 * gets the table
	 * @return TableIdentifier
	 */
	public TableIdentifier getTable() { return this.Table; }
	
	/**
	 * gets the columns name
	 * @return String
	 */
	public String getColumnName() { return this.columnName; }
	
	@Override
	public String toString()
	{
		return String.format("%s.%s", this.Table.getshName(), this.columnName);
	}

	@Override
	public boolean equals(Object other)
	{
		return this.Table.equals(((ColumnIdentifier)other).getTable()) 
				&&
				this.columnName.equalsIgnoreCase(((ColumnIdentifier) other).getColumnName());
	}
}
