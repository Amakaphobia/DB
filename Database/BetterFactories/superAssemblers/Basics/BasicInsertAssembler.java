package superAssemblers.Basics;

import static java.util.stream.Collectors.joining;
import static subAssemblers.A_SubAssembler.getAssembler;

import statement.E_ReplacementStrategies;
import statement.Statement;
import subAssemblers.E_Assemblertypes;
import superAssemblers.A_SuperAssembler;

/**
 * Class used to assemble insert into statements
 * @author Dave
 *
 */
public class BasicInsertAssembler extends A_SuperAssembler
{
	/**Constructor*/
	public BasicInsertAssembler() {
		super();
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public BasicInsertAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.TABLENAME));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.BASICVALUES));
	}

	@Override
	public String assemble(Statement statement) {
		StringBuilder strb = new StringBuilder("INSERT INTO ");
		strb.append(this.Assemblyteam.stream()
									  .map(ass -> ass.assemble(statement))
									  .collect(joining(" ")));
		
		return strb.toString();
	}

}
