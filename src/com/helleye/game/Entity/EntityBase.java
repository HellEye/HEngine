package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.EntityController;

public abstract class EntityBase implements GameObject{
	private final static int SPEED_PPF = 60;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Facing facing = Facing.UP;
	private int speed;
	private Image image;
	private Hitbox hitbox;
	private int animSpeed = 1, animBuffer = 0;
	private int frame;
	private int frameAmount;
	private int speedBufferX, speedBufferY;
	boolean hit=false;
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	protected EntityBase(int xPos, int yPos, int width, int height, Image image, int speed) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.speed = speed;
		setImage(image);
		setHitbox(0, 0, 0, 0);
	}
	
	public void setHitbox(int fromTop, int fromBot, int fromLeft, int fromRight) {
		hitbox = new Hitbox(xPos + fromLeft, yPos + fromTop, width - fromLeft - fromRight, height - fromTop - fromBot);
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
				hitbox.move(facing);
			}
		}
		else if (direction == Facing.UP) {
			facing = Facing.UP;
			speedBufferY -= speed;
			while (speedBufferY < -SPEED_PPF) {
				speedBufferY += SPEED_PPF;
				yPos--;
				hitbox.move(facing);
			}
		}
		else if (direction == Facing.RIGHT) {
			facing = Facing.RIGHT;
			speedBufferX += speed;
			while (speedBufferX > SPEED_PPF) {
				speedBufferX -= SPEED_PPF;
				xPos++;
				hitbox.move(facing);
			}
		}
		else if (direction == Facing.LEFT) {
			facing = Facing.LEFT;
			speedBufferX -= speed;
			while (speedBufferX < -SPEED_PPF) {
				speedBufferX += SPEED_PPF;
				xPos--;
				hitbox.move(facing);
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
		if (frameAmount < 1) frameAmount = 1;
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	public boolean isColiding(EntityBase entity) {
		return (hitbox.isInHitbox(entity.hitbox) && entity != this && entity instanceof EntityProjectile) && ((EntityProjectile) entity).getShooter() != this && !(this instanceof EntityProjectile);
	}
	
	public boolean canMove(Facing direction){
		return true;
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
	
	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}
	
	public void update(EntityController controller) {
		frame = (frame + (animBuffer + 1) / animSpeed) % frameAmount;
		animBuffer = (animBuffer + 1) % animSpeed;
		for (EntityBase entity : controller.getList())
			if (isColiding(entity)) {
				System.out.println("HIT by " + entity.toString() + " on\n   " + this.toString());
				hit=true;
				if (entity instanceof EntityProjectile) controller.remove(entity);
			}
	}
	
	public enum Facing {
		UP(0), LEFT(1), DOWN(2), RIGHT(3);
		private int column;
		
		Facing(int column) {this.column = column;}
		
		public int getColumn() {
			return column;
		}
	}
	
	@Override
	public String toString() {
		return "EntityBase, this should not appear";
	}
}
