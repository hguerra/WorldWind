package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.Earth.CountryBoundariesLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ScreenAnnotation;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.model.InteriorMaterial;
import br.com.inpe.worldwind.model.WorldWind;

public class AppFrame extends JFrame {
	private WorldWindowGLCanvas wwd;
	private int height = 600;
	private int width = 800;
	public static final double INITIAL_ZOOM = 2.3e7;
	public static final Position PARA_POS = Position.fromDegrees(-4.72826,
			-52.302247, 7000000);

	private JLayeredPane layeredPane;
	//
	private List<GeometryRecord> geometryRecord;
	static DataLoaderScience data;

	public AppFrame() {
		//
		data = DataLoaderScience.getInstance();
		geometryRecord = new LinkedList<GeometryRecord>();
		//
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(width, height));
		wwd.setModel(new BasicModel());
		wwd.setBounds(0, 0, width + 1, height + 1); // +1 because without it
													// the camera image isnt
													// shown

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new java.awt.Dimension(width, height));

		layeredPane.add(wwd, java.awt.BorderLayout.CENTER);

		layeredPane.setBounds(0, 0, width, height);

		layeredPane.doLayout();

		this.printLogo();
		// this.layers();
		this.layerThread(this.wwd, 0);
		// Teste remocao com timer
		// this.removeLayersTimer(5);
		frameEvents();
	}

	public void removeLayersTimer(int seconds) {
		final int t = seconds * 1000;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(t);
					removeCompass(getWwd());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public RenderableLayer getLayer(List<GeometryRecord> geometryRecord) {
		RenderableLayer layer = new RenderableLayer();
		for (int i = 0; i < geometryRecord.size(); i++) {
			List<Position> borderPositions = new LinkedList<Position>();
			String latlong[] = geometryRecord.get(i).getGeometry().split(";");

			long area = geometryRecord.get(i).getMunicipalityArea();

			for (String str : latlong) {
				String latlong2[] = str.split(",");
				borderPositions.add(Position.fromDegrees(
						Double.parseDouble(latlong2[1]),
						Double.parseDouble(latlong2[0]), 1e4));
			}

			Polygon polygon = new Polygon(borderPositions);

			ShapeAttributes sideAttributes = new BasicShapeAttributes();

			InteriorMaterial.setColorMaterial(area, sideAttributes);

			polygon.setAttributes(sideAttributes);
			polygon.setValue(AVKey.DISPLAY_NAME, geometryRecord.get(i)
					.getMunicipalityName()
					+ " - "
					+ geometryRecord.get(i).getMunicipalityArea() + "km");

			layer.addRenderable(polygon);
		}
		return layer;
	}

	public void layers() {
		geometryRecord = data.SearchDataBiggestMunicipalityArea(0);
		RenderableLayer layer = getLayer(geometryRecord);
		insertBeforeCompass(this.wwd, layer);
		// insertBeforeBeforeCompass(this.wwd, layer);
	}

	public void layerThread(WorldWindow worldWindCanvas, long elements) {
		final long area = elements;
		final WorldWindowGLCanvas wwd = (WorldWindowGLCanvas) worldWindCanvas;
		new Thread(new Runnable() {
			@Override
			public void run() {
				geometryRecord = data.SearchDataBiggestMunicipalityArea(area);
				RenderableLayer layer = getLayer(geometryRecord);
				insertBeforeCompass(wwd, layer);
			}
		}).start();
	}

	private void printLogo() {

		PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage
				.fromPath("database/logo.png");
		AnnotationLayer annLayer = new AnnotationLayer();

		ScreenAnnotation logoDWIH = new ScreenAnnotation("", new Point(0, 0));

		logoDWIH.getAttributes().setImageSource(pic.getPowerOfTwoImage());
		logoDWIH.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
		logoDWIH.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
		logoDWIH.getAttributes().setDrawOffset(new Point(100, 0));
		logoDWIH.getAttributes().setHighlightScale(1);

		logoDWIH.getAttributes().setInsets(new Insets(0, 40, 0, 0));
		logoDWIH.getAttributes().setSize(new Dimension(120, 100));

		logoDWIH.getAttributes().setImageScale(0.22);
		logoDWIH.getAttributes().setImageOffset(new Point(10, 10));

		annLayer.addAnnotation(logoDWIH);
		insertBeforeBeforeCompass(this.wwd, annLayer);

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

	public void frameEvents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				event.getWindow().setVisible(false);
				event.getWindow().dispose();
				System.exit(0);
			}
		});
		// this.setUndecorated(true);
		this.getContentPane().add(layeredPane, java.awt.BorderLayout.CENTER);
		this.pack();
		this.setBounds(0, 0, width, height);
		removeCompass(this.getWwd());
	}

	// Gets and sets

	public WorldWindowGLCanvas getWwd() {
		return wwd;
	}

	public void setWwd(WorldWindowGLCanvas wwd) {
		this.wwd = wwd;
	}
}
