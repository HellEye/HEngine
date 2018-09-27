package com.helleye.engine.gfx;

import com.helleye.engine.GameContainer;

public class RenderObject implements Comparable<RenderObject>{
	private IRenderable image;
	private int offX, offY;
	private int newX, newY, newWidth, newHeight;
	
	public RenderObject(IRenderable image, int offX, int offY) {
		this.image = image;
		this.offX = offX;
		this.offY = offY;
		newWidth=image.getWidth();
		newHeight=image.getHeight();
	}
	
	public RenderObject(IRenderable image) {
		this(image, 0, 0);
	}
	
	public IRenderable getImage() {
		return image;
	}
	
	public void setImage(IRenderable image) {
		this.image = image;
	}
	
	public int getOffX() {
		return offX;
	}
	
	public RenderObject setOffX(int offX) {
		this.offX = offX;
		return this;
	}
	
	public int getOffY() {
		return offY;
	}
	
	public RenderObject setLayer(int layer){
		image.setLayer(layer);
		return this;
	}
	public RenderObject setOffY(int offY) {
		this.offY = offY;
		return this;
	}
	
	public RenderObject setOff(int offX, int offY) {
		this.offX = offX;
		this.offY = offY;
		return this;
	}
	
	public int[] getPixels() {
		if (offX < -image.getWidth() || offY < -image.getHeight() || offX > GameContainer.P_WIDTH || offY > GameContainer.P_HEIGHT)
			return null; //don't render at all
		
		newWidth=image.getWidth();
		newHeight=image.getHeight();
		newX=0;
		newY=0;
		//cut stuff that's outside of render area
		
		if (newWidth + offX > GameContainer.P_WIDTH) { //cut right side
			newWidth -= newWidth + offX - GameContainer.P_WIDTH+1;
		}
		if (newHeight + offY > GameContainer.P_HEIGHT) { //cut bottom
			newHeight -= newHeight + offY - GameContainer.P_HEIGHT+1;
		}
		if (offX < 0) { //cut left side
			newX -= offX;
			//offX = 0;
		}
		if (offY < 0) { //cut top
			newY -= offY;
			//offY = 0;
		}
		
		int[] pixels = new int[newWidth * newHeight];
		
		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				pixels[x + y * newWidth] = image.getPixels()[x + y * image.getWidth()];
			}
		}
		return pixels;
	}
	
	public int getHeight() {
		return newHeight;
	}
	
	public int getWidth() {
		return newWidth;
	}
	
	@Override
	public int compareTo(RenderObject o) {
		return image.compareTo(o.getImage());
	}
}
