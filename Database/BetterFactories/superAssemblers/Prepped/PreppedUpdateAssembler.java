package superAssemblers.Prepped;

import static subAssemblers.A_SubAssembler.getAssembler;

import statement.E_ReplacementStrategies;
import subAssemblers.E_Assemblertypes;
import superAssemblers.Basics.BasicUpdateAssembler;

/**
 * Class used to assemble prepared UpdateStatements
 * @author Dave
 *
 */
public class PreppedUpdateAssembler extends BasicUpdateAssembler{
	
	/**Constructor*/
	public PreppedUpdateAssembler() {
		super(E_ReplacementStrategies.Standard);
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public PreppedUpdateAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.TABLENAME));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.PREPPEDSET));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.PREPPEDWHERE));
	}
}
