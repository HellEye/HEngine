package com.helleye.game.objects.entity;

import com.helleye.engine.gfx.Image;
import com.helleye.game.controls.ObjectController;
import com.helleye.game.objects.ObjectBase;

public abstract class EntityBase extends ObjectBase {
	private final static int SPEED_PPF = 60;
	
	
	private Facing facing = Facing.UP;
	private int speed;
	private int speedBufferX, speedBufferY;
	
	protected EntityBase(int xPos, int yPos, int width, int height, Image image, int speed) {
		super(xPos, yPos, width, height, image);
		this.speed = speed;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	public boolean slide(Facing direction, ObjectController ec) {
		if (direction == Facing.DOWN) {
			speedBufferY += speed;
			while (speedBufferY > SPEED_PPF) {
				if (!canMove(direction, ec) && !(this instanceof EntityProjectile)) {
					speedBufferY = 0;
					return false;
				}
				speedBufferY -= SPEED_PPF;
				yPos++;
				getHitbox().move(Facing.DOWN);
			}
		}
		else if (direction == Facing.UP) {
			speedBufferY -= speed;
			while (speedBufferY < -SPEED_PPF) {
				if (!canMove(direction, ec) && !(this instanceof EntityProjectile)) {
					speedBufferY = 0;
					return false;
				}
				speedBufferY += SPEED_PPF;
				yPos--;
				getHitbox().move(Facing.UP);
			}
		}
		else if (direction == Facing.RIGHT) {
			speedBufferX += speed;
			while (speedBufferX > SPEED_PPF) {
				if (!canMove(direction, ec) && !(this instanceof EntityProjectile)) {
					speedBufferX = 0;
					return false;
				}
				speedBufferX -= SPEED_PPF;
				xPos++;
				getHitbox().move(Facing.RIGHT);
			}
		}
		else if (direction == Facing.LEFT) {
			speedBufferX -= speed;
			while (speedBufferX < -SPEED_PPF) {
				if (!canMove(direction, ec) && !(this instanceof EntityProjectile)) {
					speedBufferX = 0;
					return false;
				}
				speedBufferX += SPEED_PPF;
				xPos--;
				getHitbox().move(Facing.LEFT);
			}
		}
		return true;
	}
	
	public boolean move(Facing direction, ObjectController ec) {
		facing = direction;
		getHitbox().setFacing(direction);
		return slide(direction, ec);
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
	
	public boolean canMove(Facing direction, ObjectController ec) {
		return true;
	}
	
	
	@Override
	public String toString() {
		return "EntityBase, this should not appear";
	}
	
}
