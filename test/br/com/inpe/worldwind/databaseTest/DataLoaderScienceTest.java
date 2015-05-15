package br.com.inpe.xmlgeometryrecordTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inpe.xmlRecord.XMLFile;
import br.com.inpe.xmlgeometryrecord.DataLoaderScience;
import br.com.inpe.xmlgeometryrecord.GeometryRecord;
import com.db4o.ObjectSet;
	
public class DataLoaderScienceTest {
	static DataLoaderScience data;
	private List<GeometryRecord> geometryRecords;
	private String filename;
	@Before
	public void start(){
		data = DataLoaderScience.getInstance();
		filename = "../WorldWind/xmldata/data.xml";
		geometryRecords = new LinkedList<GeometryRecord>();
	}
	/*
	@Test
	public void addDataTest(){
		try {
			geometryRecords = XMLFile.readList(filename);
			for(GeometryRecord g: geometryRecords){
				data.addData(g);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	*/
	@Test
	public void SearchDataTest(){
		//Terminar
		ObjectSet atual = data.SearchData("Abreu e Lima");
		
		
	}
	@Test
	public void numberCheckTest(){
		String parameter = "10";
		assertEquals(true, data.numberCheck(parameter));
	}
	@After
	public void end(){
		data = null;
		geometryRecords = null;
		filename = null;
		System.gc();
	}
}
