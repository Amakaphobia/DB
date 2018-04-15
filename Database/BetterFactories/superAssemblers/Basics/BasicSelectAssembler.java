package superAssemblers.Basics;

import statement.E_ReplacementStrategies;
import statement.Statement;
import subAssemblers.E_Assemblertypes;
import superAssemblers.A_SuperAssembler;

import static java.util.stream.Collectors.joining;
import static subAssemblers.A_SubAssembler.getAssembler;

/**
 * SelectStatement SuperAssembler (Unprepared)
 * @author Dave
 *
 */
public class BasicSelectAssembler extends A_SuperAssembler 
{	
	/**Constructor*/
	public BasicSelectAssembler() {
		super();
	}
	
	/**
	 * Constructor
	 * @param strat Replacementstrategy to use
	 */
	public BasicSelectAssembler(E_ReplacementStrategies strat) {
		super(strat);
	}
	@Override
	protected void gatherTeam() {
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.SELECTIONCOL));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.SELECTIONTAB));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.BASICWHERE));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.ORDERBY));
		this.Assemblyteam.add(getAssembler(E_Assemblertypes.GROUPBY));
	}
	
	@Override
	public String assemble(Statement statement) {
		StringBuilder strb = new StringBuilder("SELECT ");
		strb.append(this.Assemblyteam.get(0).assemble(statement))
			.append(" FROM ")		
			.append(
			this.Assemblyteam.stream()
				.skip(1)
				.map(ass -> ass.assemble(statement))
				.collect(joining(" "))
		);
		return strb.toString();
	}

}
