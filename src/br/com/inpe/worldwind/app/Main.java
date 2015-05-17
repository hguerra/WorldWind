package br.com.inpe.worldwind.app;
import java.io.FileNotFoundException;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import br.com.inpe.worldwind.controller.Comment;
import br.com.inpe.worldwind.controller.DrawPolygon;
import br.com.inpe.worldwind.controller.IAnnotation;
import br.com.inpe.worldwind.controller.IDraw;
import br.com.inpe.worldwind.controller.ScreenAnnotationLayer;
import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class Main {
	public static void main(String[]args) throws FileNotFoundException{
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
		
		WorldWindView view = new WorldWindView();
		WorldWindModel model = new WorldWindModel();
		
		model.registerObserver(view);
		//Draw Polygon
		/*
		IDraw polygonBorderPositions = new DrawPolygon(model, view);
		polygonBorderPositions.drawShapeGreaterMunicipalityArea(0);
		
		view.setIDraw(polygonBorderPositions);
		 */
		
		//Screen Annotations
		/*
		IAnnotation annotation = new ScreenAnnotationLayer(view, model);
		annotation.addAnnotation();
		view.setIAnnotation(annotation);
		*/
		
		//Comment
		IAnnotation annotation = new Comment(view, model);
		annotation.addAnnotation();
		view.setIAnnotation(annotation);
		view.setVisible(true);
		
	}

}
