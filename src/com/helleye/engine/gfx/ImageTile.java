package com.helleye.engine.gfx;

public class ImageTile extends Image {
	private int tileW;
	private int tileH;
	
	public ImageTile(String path, int tileW, int tileH) {
		super(path);
		this.tileH = tileH;
		this.tileW = tileW;
	}
	
	@Override
	public ImageTile setLayer(int layer) {
		super.setLayer(layer);
		return this;
	}
	
	public int getTileW() {
		return tileW;
	}
	
	public void setTileW(int tileW) {
		this.tileW = tileW;
	}
	
	public int getTileH() {
		return tileH;
	}
	
	public void setTileH(int tileH) {
		this.tileH = tileH;
	}
	
	public Image getImage(int tileX, int tileY) {
		return new Image(getPixels(tileX, tileY), tileW, tileH).setLayer(this.getLayer());
	}
	
	public int[] getPixels(int tileX, int tileY) {
		int[] pixels = new int[tileW * tileH];
		for (int y = 0; y < tileH; y++)
			for (int x = 0; x < tileW; x++)
				pixels[x + y * tileW] = super.getPixels()[(x + tileX * getTileW()) + (y + tileY * getTileH()) * getWidth()]; //FIXME
		return pixels;
	}
}
