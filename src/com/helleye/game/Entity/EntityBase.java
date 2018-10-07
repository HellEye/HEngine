package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.game.controls.EntityController;

public abstract class EntityBase extends ObjectBase {
	private final static int SPEED_PPF = 60;
	boolean hit = false;
	
	private Facing facing = Facing.UP;
	private int speed;
	private Image image;
	private int speedBufferX, speedBufferY;
	
	protected EntityBase(int xPos, int yPos, int width, int height, Image image, int speed) {
		super(xPos, yPos, width, height, image);
		this.speed = speed;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	public boolean move(Facing direction, EntityController ec) {
		facing=direction;
		
		
		if (direction == Facing.DOWN) {
			speedBufferY += speed;
			while (speedBufferY > SPEED_PPF) {
				if(!canMove(direction, ec)&& !(this instanceof EntityProjectile)) {
					speedBufferY=0;
					return false;
				}
				speedBufferY -= SPEED_PPF;
				yPos++;
				getHitbox().move(facing);
			}
		}
		else if (direction == Facing.UP) {
			speedBufferY -= speed;
			while (speedBufferY < -SPEED_PPF) {
				if(!canMove(direction, ec)&& !(this instanceof EntityProjectile)) {
					speedBufferY=0;
					return false;
				}
				speedBufferY += SPEED_PPF;
				yPos--;
				getHitbox().move(facing);
			}
		}
		else if (direction == Facing.RIGHT) {
			speedBufferX += speed;
			while (speedBufferX > SPEED_PPF) {
				if(!canMove(direction, ec)&& !(this instanceof EntityProjectile)) {
					speedBufferX=0;
					return false;
				}
				speedBufferX -= SPEED_PPF;
				xPos++;
				getHitbox().move(facing);
			}
		}
		else if (direction == Facing.LEFT) {
			speedBufferX -= speed;
			while (speedBufferX < -SPEED_PPF) {
				if(!canMove(direction, ec)&& !(this instanceof EntityProjectile)) {
					speedBufferX=0;
					return false;
				}
				speedBufferX += SPEED_PPF;
				xPos--;
				getHitbox().move(facing);
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
	
	public boolean isColiding(EntityBase entity) {
		return (getHitbox().isInHitbox(entity.getHitbox()) && entity != this && entity instanceof EntityProjectile) && ((EntityProjectile) entity).getShooter() != this && !(this instanceof EntityProjectile);
	}
	
	public boolean canMove(Facing direction, EntityController ec) {
		return true;
	}
	
	public void update(EntityController controller) {
		frame = (frame + (animBuffer + 1) / animSpeed) % frameAmount;
		animBuffer = (animBuffer + 1) % animSpeed;
		for (EntityBase entity : controller.getProjectiles())
			if (isColiding(entity)) {
				System.out.println("HIT by " + entity.toString() + " on\n   " + this.toString());
				hit = true;
				controller.remove(entity);
			}
	}
	
	@Override
	public String toString() {
		return "EntityBase, this should not appear";
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
