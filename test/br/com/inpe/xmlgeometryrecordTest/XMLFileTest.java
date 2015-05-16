package br.com.inpe.xmlgeometryrecordTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.xmlRecord.XMLFile;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class XMLFileTest {
	private int geometryRecordsSize;
	private String filename;
	private List<GeometryRecord> geometryRecords;
	@Before
	public void start(){
		geometryRecordsSize = 185;
		filename = "../WorldWind/xmldata/data.xml";
		geometryRecords = new LinkedList<GeometryRecord>();
	}
	@Test
	public void readListTest() throws Exception{
		geometryRecords = XMLFile.readList(filename);
		assertEquals(geometryRecordsSize, geometryRecords.size());
	}
	@After
	public void end(){
		geometryRecords = null;
		filename = null;
		System.gc();
	}

}
