package br.com.inpe.worldwind.databaseTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.xmlRecord.XMLFile;

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
	 //AO RODAR ESSE TEST ELE ADICIONA NOVAMENTE OS DADOS NO BANCO.
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
	public void SearchDataMunicipalityNameTest(){
		String name = "Abreu e Lima";
		geometryRecords = data.SearchDataMunicipalityName(name);
		assertEquals(name, geometryRecords.get(0).getMunicipalityName());
	}
	@Test
	public void SearchDataMunicipalityAreaTest(){
		long area = 126;
		geometryRecords = data.SearchDataEqualsMunicipalityArea(area);
		assertEquals(area, geometryRecords.get(0).getMunicipalityArea());

	}
	@Test
	public void SearchDataBiggestMunicipalityAreaTest(){
		long area = 0;
		long size = 185;
		geometryRecords = data.SearchDataGreaterMunicipalityArea(area);
		assertEquals(size, geometryRecords.size());
	}
	
	@Test
	public void SearchDataSmallestMunicipalityAreaTest(){
		long area = 100;
		long size = 23;
		geometryRecords = data.SearchDataLessMunicipalityArea(area);
		assertEquals(size, geometryRecords.size());
		
	}
	

	@After
	public void end(){
		data = null;
		geometryRecords = null;
		filename = null;
		System.gc();
	}
}
