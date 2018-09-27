package com.helleye.engine.gfx;

public class ImageRectangle extends IRenderable {
	
	int width;
	int height;
	private int[] pixels;
	
	public ImageRectangle(int width, int height, int color, boolean filled) {
		pixels = new int[width * height];
		if (filled) {
			for (int y = 0; y < height; y++)
				for (int x = 0; x < width; x++)
					pixels[x + width * y] = color; //CHECKME
		}
		else {
			for (int y = 0; y < height; y++) {
				pixels[y * width] = color;
				pixels[y * width + width - 1] = color;
			}
			for (int x = 0; x < width; x++) {
				pixels[x * height] = color;
				pixels[x * height + height - 1] = color;
			}
		}
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
}
