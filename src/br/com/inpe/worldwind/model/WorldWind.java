package br.com.inpe.worldwind.model;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.view.Observer;

public class WorldWind implements Subject{
	private List<GeometryRecord> geometryRecords;
	private List<String> borderPositions;
	private List<Observer> observers;
	static DataLoaderScience data;
	
	public WorldWind() {
		data = DataLoaderScience.getInstance();
		geometryRecords = new LinkedList<GeometryRecord>();
		borderPositions = new LinkedList<String>();
		observers = new LinkedList<Observer>();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserverLayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserverData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserverTime() {
		// TODO Auto-generated method stub
		
	}
	

}
