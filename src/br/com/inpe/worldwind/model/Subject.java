package br.com.inpe.worldwind.model;

import gov.nasa.worldwind.layers.RenderableLayer;
import br.com.inpe.worldwind.view.Observer;


public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserverLayer(RenderableLayer layer);
	public void notifyObserverData();
	public void notifyObserverTime();
}
