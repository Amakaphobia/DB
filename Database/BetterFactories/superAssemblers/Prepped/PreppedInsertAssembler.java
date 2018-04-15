package superAssemblers.Prepped;

import static subAssemblers.A_SubAssembler.getAssembler;

import statement.E_ReplacementStrategies;
import subAssemblers.E_Assemblertypes;
import superAssemblers.Basics.BasicInsertAssembler;

/**
 * Class used to Assemble prepared Insert Statements
 * @author Dave
 *
 */
public class PreppedInsertAssembler extends BasicInsertAssembler 
{
	/**Constructor*/
	public PreppedInsertAssembler() {
		super(E_ReplacementStrategies.Standard);
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public PreppedInsertAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.TABLENAME));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.PREPPEDVALUES));
	}
}
