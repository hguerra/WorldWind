package br.com.inpe.worldwind.model;

import br.com.inpe.worldwind.view.Observer;


public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserverLayer();
	public void notifyObserverData();
	public void notifyObserverTime();
}
