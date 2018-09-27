package com.helleye.engine.gfx;

public abstract class IRenderable implements Comparable<IRenderable> {
	private int layer;
	private int[] pixels;
	private int height, width;
	
	public abstract int getHeight();
	
	public abstract int getWidth();
	
	public int[] getPixels() {
		return pixels;
	}
	
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public int compareTo(IRenderable e) {
		return layer - e.layer; //CHECKME
	}
}
