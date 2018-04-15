package dbmodel;

import java.util.List;

/**
 * This Interface is used by Classes that will provide the known tables and columns
 * @author Dave
 *
 */
public interface I_DBReader 
{
	/**
	 * This method is used to get a collection of the known Tables
	 * @return LinkedList(TableIdentifier)
	 */
	public List<TableIdentifier> readTableInformation();
}
