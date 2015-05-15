package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.layers.RenderableLayer;

public interface Observer {
	public void updateMaterial(RenderableLayer layer);
	public void updateData();
	public void updateTime();
	public void updateBorderPosition();

}
