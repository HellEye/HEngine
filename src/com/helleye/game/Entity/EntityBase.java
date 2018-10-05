package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;

public abstract class EntityBase {
	private final static int SPEED_PPF = 60;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Facing facing = Facing.UP;
	private int speed;
	private Image image;
	private int frame;
	private int frameAmount;
	private int speedBufferX, speedBufferY;
	
	protected EntityBase(int xPos, int yPos, int width, int height, Image image, int speed) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.speed = speed;
		setImage(image);
	}
	
	public int getFrameAmount() {
		return frameAmount;
	}
	
	public void setFrameAmount(int frameAmount) {
		this.frameAmount = frameAmount;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public boolean move(Facing direction) {
		if (direction == Facing.DOWN) {
			facing = Facing.DOWN;
			speedBufferY += speed;
			while (speedBufferY > SPEED_PPF) {
				speedBufferY -= SPEED_PPF;
				yPos++;
			}
		}
		else if (direction == Facing.UP) {
			facing = Facing.UP;
			speedBufferY -= speed;
			while (speedBufferY < -SPEED_PPF) {
				speedBufferY += SPEED_PPF;
				yPos--;
			}
		}
		else if (direction == Facing.RIGHT) {
			facing = Facing.RIGHT;
			speedBufferX += speed;
			while (speedBufferX > SPEED_PPF) {
				speedBufferX -= SPEED_PPF;
				xPos++;
			}
		}
		else if (direction == Facing.LEFT) {
			facing = Facing.LEFT;
			speedBufferX -= speed;
			while (speedBufferX < -SPEED_PPF) {
				speedBufferX += SPEED_PPF;
				xPos--;
			}
		}
		
		//TODO check collision somehow? return false if colliding;
		return true;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Image getImage() {
		//TODO change to imagetile and add turning for characters
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		if (image instanceof ImageTile)
			frameAmount = image.getHeight() / ((ImageTile) image).getTileH();
	}
	
	public boolean isColiding(EntityBase entity) {
		return (xPos <= entity.xPos + entity.width || yPos < entity.yPos + entity.height || xPos + width >= entity.xPos || yPos + height >= entity.yPos) && entity != this;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
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
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void update() {
		frame = (frame + 1) % frameAmount;
	}
	
	public enum Facing {
		UP(0), LEFT(1), DOWN(2), RIGHT(3);
		private int column;
		
		Facing(int column) {this.column = column;}
		
		public int getColumn() {
			return column;
		}
	}
}
