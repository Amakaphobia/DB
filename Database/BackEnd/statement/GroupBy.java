package statement;

import java.util.ArrayList;
import java.util.List;

import dbmodel.ColumnIdentifier;

/**
 * Class used to hold the GroupBy Syntax
 * @author Dave
 *
 */
public class GroupBy 
{
	/**holds all the columns to group by*/
	private List<ColumnIdentifier> columns;
	
	/**
	 * Constructor
	 */
	public GroupBy()
	{
		this.columns = new ArrayList<>();
	}
	
	/**
	 * used to access the ColumnList
	 * @return List(ColumnIdentifier)
	 */
	public List<ColumnIdentifier> getColumns()
	{
		return this.columns;
	}
	
}
