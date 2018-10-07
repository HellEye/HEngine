package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;

public abstract class ObjectBase {
	protected int xPos;
	protected int yPos;
	protected int animSpeed = 1;
	protected int animBuffer = 0;
	protected int frame;
	protected int frameAmount;
	private Hitbox hitbox;
	private Image image;
	private int width;
	private int height;
	
	public ObjectBase(int xPos, int yPos, int width, int height, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		setImage(image);
		setHitbox(0, 0, 0, 0);
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public int getFrameAmount() {
		return frameAmount;
	}
	
	public void setFrameAmount(int frameAmount) {
		this.frameAmount = frameAmount;
	}
	
	public int getAnimBuffer() {
		return animBuffer;
	}
	
	public void setAnimBuffer(int animBuffer) {
		this.animBuffer = animBuffer;
	}
	
	public int getAnimSpeed() {
		return animSpeed;
	}
	
	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}
	
	public void setHitbox(int fromTop, int fromBot, int fromLeft, int fromRight) {
		hitbox = new Hitbox(xPos + fromLeft, yPos + fromTop, width - fromLeft - fromRight, height - fromTop - fromBot);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		if (image instanceof ImageTile)
			frameAmount = image.getHeight() / ((ImageTile) image).getTileH();
		if (frameAmount < 1) frameAmount = 1;
	}
	
	public void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
