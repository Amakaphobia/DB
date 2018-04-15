package connection;

import dbmodel.DatabaseModel;
import dbmodel.I_DBReader;
import factories.highlevel.BasicStatementFactory;
import factories.highlevel.PreppedStatementFactory;
import factories.lowlevel.I_StatementFactory;
/**
 * Class that holds the database connection
 * @author Dave
 *
 */
public class DatabaseConnection 
{
	/**the Model for the database*/
	private DatabaseModel DbModel;

	/**
	 * Constructor
	 * @param DbReaderP the reader that reads the database
	 */
	public DatabaseConnection(I_DBReader DbReaderP)
	{
		this.DbModel = new DatabaseModel(DbReaderP);
	}

	/**
	 * used to access the database model
	 * @return the database model used by the connection
	 */
	public DatabaseModel getDbModel() { return this.DbModel; }
	
	/**
	 * Method used to get a StatementFactory
	 * @param prepared describing the preparedness of the generated Factory
	 * @return the StatementFactory
	 */
	public I_StatementFactory getStatementFactory(boolean prepared) 
	{
		return prepared 
			? new PreppedStatementFactory(this.DbModel)
			: new BasicStatementFactory(this.DbModel);		
	}
}
