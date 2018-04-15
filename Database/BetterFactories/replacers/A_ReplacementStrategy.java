package replacers;
import java.util.function.BiFunction;
import statement.E_ReplacementStrategies;
import statement.Statement;

/**
 * Baseclass for Replacementstrategies
 * @author Dave
 *
 */
public abstract class A_ReplacementStrategy {

	/**The ReplacementStrategy Enum*/
	protected E_ReplacementStrategies strategy;
	
	/**
	 * This Method is used to access the reolace strategy
	 * @return the replacement bifunction[Statement,String,String]
	 */
	public abstract BiFunction<Statement, String, String> getReplacer();
	
	/**
	 * Used to get the StrategyEnum
	 * @return the enum
	 */
	public E_ReplacementStrategies getStrategy() { return this.strategy; }
}
