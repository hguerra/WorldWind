package br.com.inpe.worldwind.modeltest;


import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.model.WorldWind;


public class WorldWindTest {
	private WorldWind world;
	private List<GeometryRecord> geometryRecord;
	static DataLoaderScience data;

	@Before
	public void start() {
		world = new WorldWind();
		data = DataLoaderScience.getInstance();
		geometryRecord = new LinkedList<GeometryRecord>();
	}

	@Test
	public void createShapeGreaterMunicipalityAreaTest() {
		long area = 0;
		int municipalities = 185;
		geometryRecord = data.SearchDataBiggestMunicipalityArea(area);
		RenderableLayer actual = world.getLayer(geometryRecord);
		Assert.assertEquals(municipalities, actual.getNumRenderables());
	}



	@After
	public void end() {
		world = null;
		data = null;
		geometryRecord = null;
	}
}
