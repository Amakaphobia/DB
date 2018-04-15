package test;

import java.util.LinkedList;

import dbmodel.I_DBReader;
import dbmodel.TableIdentifier;

@SuppressWarnings("javadoc")
public class PseudoReader implements I_DBReader {
	@Override
	public LinkedList<TableIdentifier> readTableInformation()
	{
		LinkedList<TableIdentifier> testlist = new LinkedList<>();
		try{
			TableIdentifier t1 = new TableIdentifier("Schüler", "s");
			t1.addColumn("Name");
			t1.addColumn("Alter");
			t1.addColumn("Klasse");
			testlist.add(t1);
			
			TableIdentifier t2 = new TableIdentifier("Klassen", "k");
			t2.addColumn("Lehrer");
			t2.addColumn("ID");
			t2.addColumn("Klassensprecher");
			testlist.add(t2);
			
			TableIdentifier t3 = new TableIdentifier("FsDataB", "FsDb");
			t3.addColumn("asd");
			testlist.add(t3);
		}catch(Exception e){e.printStackTrace();}
		
		return testlist;
	}
}
