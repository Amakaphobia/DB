package superAssemblers.Basics;

import statement.E_ReplacementStrategies;
import statement.Statement;
import subAssemblers.E_Assemblertypes;
import superAssemblers.A_SuperAssembler;

import static java.util.stream.Collectors.joining;
import static subAssemblers.A_SubAssembler.getAssembler;

/**
 * Class used to build unprepared Delete Statements
 * @author Dave
 *
 */
public class BasicUpdateAssembler extends A_SuperAssembler 
{	
	/**Constructor*/
	public BasicUpdateAssembler() {
		super();
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public BasicUpdateAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.TABLENAME));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.BASICSET));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.BASICWHERE));
	}

	@Override
	public String assemble(Statement statement) {
			StringBuilder strb = new StringBuilder("UPDATE ");
			strb.append(this.Assemblyteam.stream()
							.map(ass -> ass.assemble(statement))
							.collect(joining(" ")));			
			return strb.toString();		
	}

}
