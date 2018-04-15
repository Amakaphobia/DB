package test;
import connection.DatabaseConnection;
import dbmodel.I_DBReader;
import factories.lowlevel.E_SQLType;
import statement.E_WhereType;
import statement.Statement;

@SuppressWarnings("javadoc")
public class Test
{

	public static void main(String[] args) throws Exception{
		I_DBReader dbr = new PseudoReader();		
		DatabaseConnection dbcon = new DatabaseConnection(dbr);
		
		Statement inner = dbcon.getStatementFactory(true)
				.prepareNewStatement(E_SQLType.SELECT)
				.addSelectionColumn("Klassen","ID")
				.addSelectionTable("Klassen")
				.addWhereClause("ID",  "Klassen", E_WhereType.GREATER, 0)
				.getStatement();
		
		System.out.println(inner);
		
		Statement inner2 =
				dbcon.getStatementFactory(true)
					.prepareNewStatement(E_SQLType.SELECT)
					.addSelectionTable("FsDataB")
					.getStatement();
		
		System.out.println(inner2);
		
		System.out.println(
				dbcon.getStatementFactory(true)
						.prepareNewStatement(E_SQLType.SELECT)
						.addSelectionColumn("Sch�ler", "Alter")
						.addSelectionTable(inner, "hallo","h")
						.addSelectionColumn("hallo", "ID")
						.addSelectionTable("Sch�ler")
						.addWhereClause("ID", "hallo", E_WhereType.IN, inner2)
						.addSelectionTable("Klassen")
						.addWhereClause(dbr.readTableInformation().get(0).getColumns().get(0), E_WhereType.EQUALS, 15)
						.addWhereClause(false, "ID", "Klassen", E_WhereType.GREATER_EQUAL, "asd")
						.addGroupBy("Sch�ler", "Name")
						.addOrderBy("Klassen", "ID")
						.build());
	
		System.out.println(
				dbcon.getStatementFactory(true)
					.prepareNewStatement(E_SQLType.INSERT)
					.addSelectionTable("Sch�ler")
					.addValue("Sch�ler", "Name", "Hans")
					.addValue(dbr.readTableInformation().get(0).getColumns().get(1), "15")
					.build()
		);
		
		System.out.println(
				dbcon.getStatementFactory(true)
						.prepareNewStatement(E_SQLType.DELETE)
						.addSelectionTable("Sch�ler")
						.addWhereClause("Alter", "Sch�ler", E_WhereType.EQUALS, 15)
						.build()
		);
		
		System.out.println(
				dbcon.getStatementFactory(true)
						.prepareNewStatement(E_SQLType.UPDATE)
						.addSelectionTable("Sch�ler")
						.addValue("Sch�ler", "Name", "Karl")
						.addValue("Sch�ler", "Alter", "20")
						.addWhereClause("Alter", "Sch�ler", E_WhereType.EQUALS, 15)
						.build()
		);		
		
	}
}