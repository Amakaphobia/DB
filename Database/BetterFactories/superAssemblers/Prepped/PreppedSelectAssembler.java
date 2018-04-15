package superAssemblers.Prepped;

import static subAssemblers.A_SubAssembler.getAssembler;

import statement.E_ReplacementStrategies;
import subAssemblers.E_Assemblertypes;
import superAssemblers.Basics.BasicSelectAssembler;

/**
 * Class used to Assemble prepared Select Statements
 * @author Dave
 *
 */
public class PreppedSelectAssembler extends BasicSelectAssembler{
	
	/**Constructor*/
	public PreppedSelectAssembler() {
		super(E_ReplacementStrategies.Standard);
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public PreppedSelectAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.SELECTIONCOL));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.SELECTIONTAB));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.PREPPEDWHERE));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.ORDERBY));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.GROUPBY));
	}
}
