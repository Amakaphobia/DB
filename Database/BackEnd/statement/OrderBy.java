package statement;

import java.util.ArrayList;
import java.util.List;

import dbmodel.ColumnIdentifier;

/**
 * Class used to represent the OrderBy part of the SQL Syntax
 * @author Dave
 *
 */
public class OrderBy 
{
	/**List of Columnnames to Order by*/
	private List<ColumnIdentifier> columnNames;
	/**true for ascending false for descending*/
	private boolean isAsc;
	
	/**
	 * Constructor default is Ascending
	 */
	public OrderBy()
	{
		this.columnNames = new ArrayList<>();
		this.isAsc = true;
	}
	
	/**
	 * Method Used to set the Order to descending
	 */
	public void setDesc()
	{
		this.isAsc = false;
	}
	
	/**
	 * Accesses the LinkedList that Contains the Columns
	 * @return List(ColumnIdentifier) used by sql
	 */
	public List<ColumnIdentifier> getOrderByList()
	{
		return this.columnNames;
	}
	
	/**
	 * accesses the IsAsc value
	 * @return true if ascending false for descending
	 */
	public boolean getIsAsc()
	{
		return this.isAsc;
	}
}
