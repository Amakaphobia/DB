package superAssemblers.Prepped;

import static subAssemblers.A_SubAssembler.getAssembler;

import statement.E_ReplacementStrategies;
import subAssemblers.E_Assemblertypes;
import superAssemblers.Basics.BasicDeleteAssembler;

/**
 * Class used Assemble prepared Delete statements
 * @author Dave
 *
 */
public class PreppedDeleteAssembler extends BasicDeleteAssembler{
	
	/**Constructor*/
	public PreppedDeleteAssembler() {
		super(E_ReplacementStrategies.Standard);
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public PreppedDeleteAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.TABLENAME));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.PREPPEDWHERE));
	}
}
