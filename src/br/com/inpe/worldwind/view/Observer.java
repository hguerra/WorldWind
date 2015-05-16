package br.com.inpe.worldwind.view;

import java.util.List;

import gov.nasa.worldwind.layers.RenderableLayer;
import br.com.inpe.worldwind.database.GeometryRecord;
public interface Observer {
	public void updateGeometryRecordLayer(List<GeometryRecord> geometryRecord);
}
