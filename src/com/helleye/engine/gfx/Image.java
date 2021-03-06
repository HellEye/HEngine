package com.helleye.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private int width;
	//private boolean alpha=false;
	private int layer;
	private int height;
	private int[] pixels;
	
	public Image(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}
	
	public Image(String path) {
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		image.flush();
	}
	
	public Image(Image image) {
		pixels = new int[image.getPixels().length];
		System.arraycopy(image.getPixels(), 0, pixels, 0, image.getPixels().length);
		width = image.width;
		height = image.height;
		layer = image.layer;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public Image setLayer(int layer) {
		this.layer = layer;
		return this;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	
}
