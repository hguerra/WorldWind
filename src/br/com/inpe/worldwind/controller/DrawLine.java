package br.com.inpe.worldwind.controller;

import gov.nasa.worldwind.geom.Position;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class DrawLine implements ILine{
	private WorldWindView view;
	private WorldWindModel model;
	private String displayName;
	private List<Position> positions;

	public DrawLine(WorldWindView view, WorldWindModel model, String displayName) {
		super();
		this.view = view;
		this.model = model;
		this.displayName = displayName;
		positions = new LinkedList<Position>();
	}
	
	public void setPositionsExample(){
		 positions.add(Position.fromDegrees(52, 8));
         positions.add(Position.fromDegrees(52, 13));
         positions.add(Position.fromDegrees(50, 19));
         positions.add(Position.fromDegrees(49, 19));
	}
	
	public void drawLine(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				view.drawLine(displayName, positions);;
			}
		}).start();
	}
	
	
	
}
