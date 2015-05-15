package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.terrain.ZeroElevationModel;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;

public class PlotGeometries extends ApplicationTemplate {
	public static class AppFrame extends ApplicationTemplate.AppFrame {
		public AppFrame() throws FileNotFoundException {
			super(true, true, false);

			this.getWwd().getModel().getGlobe()
					.setElevationModel(new ZeroElevationModel());

			RenderableLayer layer = new RenderableLayer();
			DataLoaderScience data;
			data = DataLoaderScience.getInstance();

			List<GeometryRecord> geometryRecord = data
					.SearchDataBiggestMunicipalityArea(0);

			for (int i = 0; i < geometryRecord.size(); i++) {

				ArrayList<Position> borderPositions = new ArrayList<Position>();

				String latlong[] = geometryRecord.get(i).getGeometry()
						.split(";");

				long area = geometryRecord.get(i).getMunicipalityArea();

				for (String str : latlong) {
					String latlong2[] = str.split(",");
					borderPositions.add(Position.fromDegrees(
							Double.parseDouble(latlong2[1]),
							Double.parseDouble(latlong2[0]), 1e4));
				}
				Polygon polygon = new Polygon(borderPositions);
				ShapeAttributes sideAttributes = new BasicShapeAttributes();

				if (area < 300) {
					sideAttributes.setInteriorMaterial(Material.RED);
				} else if (area >= 300 && area < 500) {
					sideAttributes.setInteriorMaterial(Material.WHITE);
				} else if (area >= 500 && area < 800) {
					sideAttributes.setInteriorMaterial(Material.YELLOW);
				} else if (area >= 800 && area < 1000) {
					sideAttributes.setInteriorMaterial(Material.GREEN);
				} else if (area >= 1000) {
					sideAttributes.setInteriorMaterial(Material.BLUE);
				}

				sideAttributes.setOutlineOpacity(0.5);
				sideAttributes.setInteriorOpacity(0.5);
				sideAttributes.setOutlineWidth(2);
				sideAttributes.setDrawOutline(true);
				sideAttributes.setDrawInterior(true);
				polygon.setAttributes(sideAttributes);
				

				polygon.setValue(AVKey.DISPLAY_NAME, geometryRecord.get(i)
						.getMunicipalityName()
						+ " - "
						+ geometryRecord.get(i).getMunicipalityArea() + "km");

				layer.addRenderable(polygon);
			}
			insertBeforeCompass(getWwd(), layer);
			this.getLayerPanel().update(this.getWwd());

		}
	}

	public static void main(String[] args) {
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);

		ApplicationTemplate.start("NASA World Wind", AppFrame.class);
	}

}