package dbmodel;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import boxes.Pair;
import exceptions.ColumnAlreadyKnown;
import statement.Statement;

/**
 * Class to be used to hold the targeted Table names 
 * and their short names.
 * @author Dave
 *
 */
public class TableIdentifier 
{
	/**holds the full name of the table*/
	private String name;
	/**holds the given short name*/
	private String shname;
	/**holds all Columns this table contains*/
	private List<ColumnIdentifier> Columns;
	/**holds the used subSelect*/
	private Statement subSelect;
	
	/**
	 * Constructor
	 * @param nameP the name of the table
	 * @param shnameP the desired short name
	 */
	public TableIdentifier(String nameP,String shnameP)
	{
		this.name = nameP.toLowerCase();
		this.shname = shnameP.toLowerCase();
		this.Columns = new ArrayList<>();
		this.subSelect = null;
	}
	
	/**
	 * Constructor
	 * @param subSelectP the subselect thats defining the table
	 * @param tableNameP the given tablename
	 * @param shnameP the given shortname for the table
	 * @throws ColumnAlreadyKnown if such a name is already in use
	 */
	public TableIdentifier(Statement subSelectP,String tableNameP, String shnameP) throws ColumnAlreadyKnown
	{
		this.subSelect = subSelectP;
		this.shname = shnameP;
		this.name = tableNameP;
		this.Columns = new ArrayList<>();
		for( ColumnIdentifier e :subSelectP.getUsedCols())
			this.addColumn(e.getColumnName());
	}
	
	/**
	 * Method Used to add another Column to the TableModel
	 * @param s String containing the name of the column you want to add
	 * @throws ColumnAlreadyKnown if the Column is already known
	 */
	public void addColumn(String s) throws ColumnAlreadyKnown
	{
		Optional<ColumnIdentifier> test = 
				this.Columns.stream()
							 .filter(col -> col.getColumnName().equals(s.toLowerCase()))
							 .findFirst();
		if(test.isPresent())
			throw new ColumnAlreadyKnown(test.get());
		else
			this.Columns.add(new ColumnIdentifier(this,s));
	}
	
	/**
	 * Method Used to add another Column to the TableModel
	 * @param ci the ColumnIdentfier you want to add
	 * @throws ColumnAlreadyKnown if the Column is already known
	 */
	public void addColumn(ColumnIdentifier ci) throws ColumnAlreadyKnown
	{
		if(this.Columns.stream()
						 .filter(col -> col.equals(ci))
						 .findAny()
						 .isPresent())
			throw new ColumnAlreadyKnown(ci);
		else
			this.Columns.add(ci);
	}
	
	/**
	 * Method used to get all columns incl their tablesht separated by a Comma
	 * @return String Col1, Col2, Col3....
	 */
	public String getAllColumns()
	{
		return this.Columns.stream()
					.map(col -> String.format("%s.%s (%s)", col.getTable().getshName(), col.getColumnName(), col.getTable().getName()))
					.collect(joining(", "));
	}
	
	/**
	 * Method used to check if a column is known given a Name
	 * @param ColName String with the name of the column
	 * @return true if found false if not
	 */
	public boolean containsColumn(String ColName)
	{
		return this.Columns.stream()
					.parallel()
					.filter(col-> col.getColumnName().equals(ColName))
					.findAny()
					.isPresent();					
	}
	
	/**
	 * Method used to check if a column is known given a Name
	 * @param Col Column to be searched
	 * @return true if found false if not
	 */
	public boolean containsColumn(ColumnIdentifier Col)
	{
		return this.Columns.stream()
					.parallel()
					.filter(col-> col.equals(Col))
					.findAny()
					.isPresent();					
	}
	
	/**
	 * Gets the name of the table as a string
	 * @return String
	 */
	public String getName()		{ return this.name; }
	
	/**
	 * gets the short name of the table as a String
	 * @return String
	 */
	public String getshName()	{ return this.shname; }
	
	/**
	 * gets a List containing the ColumnIdentifiers
	 * @return LinkedList(ColumnIdentifier)
	 */
	public List<ColumnIdentifier> getColumns() { return this.Columns; }
	
	/**
	 * gets the name and short name in a pair of strings
	 * @return Pair(String,String)
	 */
	public Pair<String, String> getBoth()
	{
		return new Pair<String, String>(this.name, this.shname);
	}
	
	/**
	 * used to check if this table is a existing one or a subselect table
	 * @return true if this table uses a Subselect
	 */
	public boolean isSubSelect()
	{
		return this.subSelect != null;
	}
	
	@Override
	public boolean equals(Object other)
	{
		return this.name.equalsIgnoreCase(( (TableIdentifier) other ).getName());
	}
	
	@Override
	public String toString()
	{
		return this.subSelect == null
				? this.toStringNorm()
				: this.toStringSub();
	}	
	
	/**
	 * Internal method used by the toStringMethod
	 * @return the tabledefiner in SQL syntax with (Statement) as shname
	 */
	private String toStringSub()
	{
		return String.format("( %s ) as %s", this.subSelect.toString(),this.shname);
	}
	
	/**
	 * Internal method used by the toStringMethod
	 * @return the tablename as is used in sqlstatements with name [Space] shname
	 */
	private String toStringNorm()
	{
		return  this.shname.isEmpty() ?
				this.name :
				String.format("%s %s",this.name, this.shname);
	}
}
