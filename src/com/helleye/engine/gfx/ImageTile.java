package com.helleye.engine.gfx;

public class ImageTile extends Image {
	private int tileW;
	private int tileH;
	
	public ImageTile(String path, int tileW, int tileH) {
		super(path);
		this.tileH = tileH;
		this.tileW = tileW;
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
	
	public int[] getPixels(int tileX, int tileY) {
		int[] pixels = new int[tileW * tileH];
		for (int y = 0; y < tileH; y++)
			for (int x = 0; x < tileW; x++)
				pixels[x + y * tileW] = super.getPixels()[x + tileX * tileW + y * tileY * super.getWidth()]; //CHECKME
		return pixels;
	}
}
