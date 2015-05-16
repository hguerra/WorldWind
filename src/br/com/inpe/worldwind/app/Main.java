package br.com.inpe.worldwind.app;
import java.io.FileNotFoundException;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;

public class Main {
	public static void main(String[]args) throws FileNotFoundException{
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
		WorldWindView app = new WorldWindView();
		WorldWindModel w = new WorldWindModel();
		w.registerObserver(app);
		w.createShapeGreaterMunicipalityArea(0);
		app.setVisible(true);
		
	}

}
