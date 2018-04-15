package statement;


import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;

import dbmodel.ColumnIdentifier;
import dbmodel.TableIdentifier;
import exceptions.ColumnAlreadyKnown;
import superAssemblers.A_SuperAssembler;

/**
 * Class used to Hold the Statement and all its Attributes
 * @author Dave
 *
 */
public class Statement
{	

	/**holds the whereclauses*/
	private List<A_WhereClause> whereList;
	/**holds the selected Columnnames*/
	private List<ColumnIdentifier> selectList;
	/**holds the tables*/
	private List<TableIdentifier> tableNameList;
	/**holds the values for update or insert into statements*/
	private List<A_SetValueClause> UpdateValues;
	/**holds the order by clause*/
	private OrderBy OrderBy;
	/**holds the group by clause*/
	private GroupBy GroupBy;
	/**Assembly Strategy*/
	private A_SuperAssembler strategy;
	
	
	/**
	 * Constructor automatically set Replacement to Without
	 * @param strategy the Strategy thats used to assemble the statement
	 */
	public Statement(A_SuperAssembler strategy) 
	{
		this.whereList = new ArrayList<>();
		this.selectList = new ArrayList<>();
		this.tableNameList = new ArrayList<>();
		this.UpdateValues = new ArrayList<>();
		this.OrderBy = new OrderBy();
		this.GroupBy = new GroupBy();
		this.strategy = strategy;
	}
	
	/**
	 * Method used to add a where clause for the statement
	 * @param wc the Where clause you want to add
	 */
	public void addWhereClause(A_WhereClause wc)		
	{
		this.whereList.add(wc);
	}	
	
	/**
	 * Method used to add a column thats gonna get selected
	 * @param ci the ColumnIdentifier you want to select
	 */
	public void addSelection(ColumnIdentifier ci)	
	{ 
		this.selectList.add(ci);
	}
	
	/**
	 * Method used to add a Table thats gonna get selected
	 * @param ti the TableIdentifier you want to select
	 */
	public void addTableName(TableIdentifier ti) 	
	{ 
		this.tableNameList.add(ti);
	}
	
	/**
	 * Method used to add a Table thats gonna get selected if that table is a subselect
	 * @param subquerryP the subselected Statement
	 * @param tableName the table name
	 * @param shnameP the tableshname
	 * @throws ColumnAlreadyKnown if that name is already used
	 */
	public void addTableName(Statement subquerryP,String tableName, String shnameP) throws ColumnAlreadyKnown
	{
		this.tableNameList.add(new TableIdentifier(subquerryP, tableName, shnameP));
	}

	/**
	 * Method used to hold a column thats gonna get used in ordered by
	 * @param ci the ColumnIdentifier that you want to add
	 */
	public void addOrderBy(ColumnIdentifier ci)		
	{
		this.OrderBy.getOrderByList().add(ci);
	}
	
	/**
	 * Method used to hold a column thats gonna get used in ordered by
	 * @param ci the ColumnIdentifier that you want to add
	 */
	public void addGroupBy(ColumnIdentifier ci)		
	{ 
		this.GroupBy.getColumns().add(ci); 
	}
	
	/**
	 * Method used to add a value to a insert into Statement
	 * @param in the value clause you want to add
	 */
	public void addUpdateValue(A_SetValueClause in){
		this.UpdateValues.add(in);
	}
	
	/**Method used to set the OrderBy Order to Descending*/
	public void setOrderByDescending()				
	{
		this.OrderBy.setDesc();
	}
	
	/**
	 * Gets all used column in a collection
	 * @return the collection full of columnidentifiers used in this statement
	 */
	public List<ColumnIdentifier> getUsedCols()
	{
		List<ColumnIdentifier> sel = this.selectList;
		List<A_WhereClause>    whe = this.whereList;
		sel.addAll(
				whe.stream()
				   .map(w -> w.getCol())
				   .collect(toList())
				);
		return sel.stream()
					.distinct()
					.collect(toList());
	}
	
	/**
	 * Method used to get the Wherelist of the statement
	 * @return a List of A_WhereClauses
	 */
	public List<A_WhereClause> getWhereList() { return this.whereList; }

	/**
	 * Method used to get the ColumnList of the statement
	 * @return a List of ColumnIdentifiers
	 */
	public List<ColumnIdentifier> getSelectList() { return this.selectList; }

	/**
	 * Method used to get the TableNameList of the statement
	 * @return a List of TableIdentifiers
	 */
	public List<TableIdentifier> getTableNameList() { return this.tableNameList;	}

	/**
	 * Gets a List Containg Pairs of Columnidentifiers and strings containing the values to be adde to a table
	 * @return List(Pair(ClomumnIdentifier,String))
	 */
	public List<A_SetValueClause> getUpdateValues() { return this.UpdateValues;	}

	/**
	 * Gets the order by object
	 * @return the order by object
	 */
	public OrderBy getOrderBy() { return this.OrderBy;}

	/**
	 * Gets the group by object
	 * @return the group by object
	 */
	public GroupBy getGroupBy() { return this.GroupBy;}
	
	@Override
	public String toString(){
		return this.strategy.getAssembledAndReplaced(this);
	}
	
}
