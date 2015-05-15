package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.terrain.ZeroElevationModel;
import gov.nasa.worldwindx.examples.LayerPanel;

import javax.swing.*;

import br.com.inpe.worldwind.controller.IDraw;

public class View extends JFrame implements Observer{
	private WorldWindowGLCanvas wwd;
	private IDraw drawPolygon;
	private RenderableLayer layer;
	private LayerPanel panel;

	public View() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());
		this.wwd.getModel().getGlobe()
		.setElevationModel(new ZeroElevationModel());
		
		panel = new LayerPanel(this.wwd);
		
		insertBeforeCompass(this.wwd, getLayer());
		panel.update(this.wwd);
	}

	@Override
	public void updateMaterial(RenderableLayer layer) {
		setLayer(layer);
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBorderPosition() {
		// TODO Auto-generated method stub
		
	}
	
	
	public RenderableLayer getLayer() {
		return layer;
	}

	public void setLayer(RenderableLayer layer) {
		this.layer = layer;
	}

	public static void removeCompass(WorldWindow wwd) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.remove(compassPosition);
	}

	public static void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public static void insertBeforeBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition - 1, layer);
	}
}