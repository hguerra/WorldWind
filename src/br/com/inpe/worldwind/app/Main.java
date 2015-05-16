package br.com.inpe.worldwind.app;
import java.io.FileNotFoundException;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;

import javax.swing.JFrame;

import br.com.inpe.worldwind.model.WorldWind;
import br.com.inpe.worldwind.view.AppFrame;
import br.com.inpe.worldwind.view.View;

public class Main {
	public static void main(String[]args) throws FileNotFoundException{
		/*
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
		
		View v = new View();
		WorldWind w = new WorldWind();
		w.registerObserver(v);
		w.createShapeGreaterMunicipalityArea(0);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.pack();
		v.setVisible(true);
		*/
		AppFrame app = new AppFrame();
		app.setVisible(true);
		
	}

}
