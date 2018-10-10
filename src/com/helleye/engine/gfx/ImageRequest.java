package com.helleye.engine.gfx;

public class ImageRequest implements Comparable<ImageRequest>{
	public Image image;
	
	public int screen;
	public int zDepth, offX, offY;
	public ImageRequest(Image image, int zDepth, int offX, int offY, int screen){
		this.image=image;
		this.zDepth=zDepth;
		this.offX=offX;
		this.offY=offY;
		this.screen=screen;
	}
	
	@Override
	public int compareTo(ImageRequest o) {
		return zDepth-o.zDepth;
	}
}
