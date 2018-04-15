package replacers;

import java.util.function.BiFunction;

import statement.Statement;

/**
 * Class used if there is no replacement to be done
 * @author Dave
 *
 */
public class WithoutReplacer extends A_ReplacementStrategy {
	@Override
	public BiFunction<Statement, String, String> getReplacer() {
		return (statement,string)-> {
			return string;
		};
	}

}
