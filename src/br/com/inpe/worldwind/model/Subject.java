package br.com.inpe.worldwind.model;

import java.util.List;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.view.Observer;


public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserverGeometryRecord(List<GeometryRecord> geometryRecord);
}
