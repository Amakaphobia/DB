package statement;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

/**
 * Class used for inputvalidation
 * @author Dave
 *
 */
public class SQLInputValidation {
	/**Contains the forbidden charsequences*/
	public static final List<String> traps = Arrays.asList(";","--",SQLInputValidation.delimiter);
	/**the delimiter used to hold the values place*/
	public static final String delimiter = "%i";
	
	/**
	 * Checks a given String for possible input violations
	 * @param input the string you want to check
	 * @return a List of type String containing all violations
	 */
	public static List<String> check(String input){
		return SQLInputValidation.traps.stream()
						.filter(input::contains)
						.collect(toList());
	}
}
