package superAssemblers;

import java.util.ArrayList;
import java.util.List;

import replacers.A_ReplacementStrategy;
import replacers.StandartReplacer;
import replacers.WithoutReplacer;
import statement.E_ReplacementStrategies;
import statement.Statement;
import subAssemblers.A_SubAssembler;

/**
 * Abstract BaseClass for SuperAssemblers
 * @author Dave
 *
 */
public abstract class A_SuperAssembler 
{
	/**The list of SubAssemblers in Order*/
	protected List<A_SubAssembler> Assemblyteam;
	/**The replacement Strategy that is used by prepared Statements*/
	protected A_ReplacementStrategy strat;
	
	/**
	 * Constructor without replacment
	 */
	public A_SuperAssembler() {
		this.onConstructor(E_ReplacementStrategies.Without);
	}
	
	/**
	 * Constructor
	 * @param strat replacement strategy you want to use 
	 */
	public A_SuperAssembler(E_ReplacementStrategies strat) {
		this.onConstructor(strat);
	}
	
	/**
	 * Method used by all Constructors
	 * @param strat replacement strategy you want to use 
	 */
	protected void onConstructor(E_ReplacementStrategies strat) {
		this.Assemblyteam = new ArrayList<>();
		this.strat = this.chooseReplacer(strat);
		this.gatherTeam();
	}
	
	/**Internal Method that adds the different SubAssemblers to the AssemblyTeam List*/
	protected abstract void gatherTeam();
	
	/**
	 * Method used to assemble the string
	 * @param statement the statement you want to assemble
	 * @return the SQL-Statement as a String
	 */
	protected abstract String assemble(Statement statement);
	
	/**
	 * MEthod used to choose the replacementstrategy
	 * @param strat the enum defining the chosen strat
	 * @return the chosen strategy
	 */
	protected A_ReplacementStrategy chooseReplacer(E_ReplacementStrategies strat){
		A_ReplacementStrategy arep;
		switch(strat){
			case Standard:
				arep = new StandartReplacer();
				break;
			case Without:
				arep = new WithoutReplacer();
				break;
			default:
				throw new RuntimeException("That is not yet implemented");
		}
		return arep;
	}
	
	/**
	 * Method used to get the Assembled statement
	 * @param statement you want to assemble
	 * @return the string with all info
	 */
	public String getAssembledAndReplaced(Statement statement) {
		return this.strat.getReplacer()
					.apply(statement, 
						   this.assemble(statement));
	}
}
