package br.com.inpe.worldwind.model;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.view.Observer;

public class WorldWind implements Subject{
	private List<GeometryRecord> geometryRecord;
	private List<Observer> observers;
	static DataLoaderScience data;

	public WorldWind() {
		data = DataLoaderScience.getInstance();
		geometryRecord = new LinkedList<GeometryRecord>();
		observers = new LinkedList<Observer>();
	}

	
	public void createShapeGreaterMunicipalityArea(long area) {
		//Teste
		final long a = area;
		new Thread(new Runnable() {
			@Override
			public void run() {
				geometryRecord = data.SearchDataBiggestMunicipalityArea(a);
				RenderableLayer layer = getLayer(geometryRecord);
				notifyObserverLayer(layer);
			}
		}).start();
		//
		//geometryRecord = data.SearchDataBiggestMunicipalityArea(area);
		//notifyObserverLayer(getLayer(geometryRecord));
	}

	public void createShapeEqualsMunicipalityArea(long area) {
		geometryRecord = data.SearchDataMunicipalityArea(area);
		notifyObserverLayer(getLayer(geometryRecord));
	}

	public void createShapeLessMunicipalityArea(long area) {
		geometryRecord = data.SearchDataSmallestMunicipalityArea(area);
		notifyObserverLayer(getLayer(geometryRecord));
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserverLayer(RenderableLayer layer) {
		for (Observer o : observers) {
			o.updateMaterial(layer);
		}
	}

	public RenderableLayer getLayer(List<GeometryRecord> geometryRecord) {
		RenderableLayer layer = new RenderableLayer();
		for (int i = 0; i < geometryRecord.size(); i++) {
			List<Position> borderPositions = new LinkedList<Position>();
			String latlong[] = geometryRecord.get(i).getGeometry().split(";");

			long area = geometryRecord.get(i).getMunicipalityArea();

			for (String str : latlong) {
				String latlong2[] = str.split(",");
				borderPositions.add(Position.fromDegrees(
						Double.parseDouble(latlong2[1]),
						Double.parseDouble(latlong2[0]), 1e4));
			}

			Polygon polygon = new Polygon(borderPositions);

			ShapeAttributes sideAttributes = new BasicShapeAttributes();

			InteriorMaterial.setColorMaterial(area, sideAttributes);

			polygon.setAttributes(sideAttributes);
			polygon.setValue(AVKey.DISPLAY_NAME, geometryRecord.get(i)
					.getMunicipalityName()
					+ " - "
					+ geometryRecord.get(i).getMunicipalityArea() + "km");

			layer.addRenderable(polygon);
		}
		return layer;
	}

	@Override
	public void notifyObserverData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObserverTime() {
		// TODO Auto-generated method stub

	}
	/**
	 * Teste
	 */

}
