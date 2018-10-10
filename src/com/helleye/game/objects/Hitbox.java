package com.helleye.game.objects;

import com.helleye.game.objects.entity.Facing;

public class Hitbox {
	private int x, y, width, height;
	private int fromTop, fromBot, fromRight, fromLeft;
	private Facing facing;
	
	public Hitbox(int x, int y, int width, int height, Facing facing, int fromTop, int fromBot, int fromLeft, int fromRight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.facing = facing;
		this.fromBot = fromBot;
		this.fromTop = fromTop;
		this.fromLeft = fromLeft;
		this.fromRight = fromRight;
	}
	
	public Hitbox(Hitbox hitbox) {
		x = hitbox.x;
		y = hitbox.y;
		width = hitbox.width;
		height = hitbox.height;
		facing = hitbox.facing;
		fromTop = hitbox.fromTop;
		fromBot = hitbox.fromBot;
		fromRight = hitbox.fromRight;
		fromLeft = hitbox.fromLeft;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isInHitbox(Hitbox hitbox) {
		return isInHitbox(hitbox.x, hitbox.y, hitbox.width, hitbox.height, hitbox.facing, hitbox.fromTop, hitbox.fromBot, hitbox.fromLeft, hitbox.fromRight);
	}
	
	
	public boolean isInHitbox(int x, int y, int width, int height, Facing facing, int fromTop, int fromBot, int fromLeft, int fromRight) {
		if (facing == Facing.UP) {
			for(int i=fromLeft;i<width-fromRight;i++)
				if (isInHitbox(x + i, y) || isInHitbox(x + i, y + height)) return true;
			for(int i=fromTop;i<height-fromBot;i++)
				if(isInHitbox(x, y+i )||isInHitbox(x+width, y+i)) return true;
		}
		else if (facing == Facing.DOWN) {
			for(int i=fromRight;i<width-fromLeft;i++)
				if (isInHitbox(x + i, y) || isInHitbox(x + i, y + height)) return true;
			for(int i=fromBot;i<height-fromTop;i++)
				if(isInHitbox(x, y+i )||isInHitbox(x+width, y+i)) return true;
		}
		else if (facing == Facing.RIGHT) {
			for(int i=fromBot;i<width-fromTop;i++)
				if (isInHitbox(x + i, y) || isInHitbox(x + i, y + height)) return true;
			for(int i=fromLeft;i<height-fromRight;i++)
				if(isInHitbox(x, y+i )||isInHitbox(x+width, y+i)) return true;
		}
		else if (facing == Facing.LEFT) {
			for(int i=fromTop;i<width-fromBot;i++)
				if (isInHitbox(x + i, y) || isInHitbox(x + i, y + height)) return true;
			for(int i=fromRight;i<height-fromLeft;i++)
				if(isInHitbox(x, y+i )||isInHitbox(x+width, y+i)) return true;
		}
		return false;
	}
	
	public boolean isInHitbox(int x, int y) {
		return (x > getXLeft() && x < getXRight()) && (y > getYTop() && y < getYBot());
	}
	
	public void move(Facing direction) {
		switch (direction) {
			case UP:
				y--;
				break;
			case DOWN:
				y++;
				break;
			case LEFT:
				x--;
				break;
			case RIGHT:
				x++;
		}
	}
	
	public Hitbox getOffsetHitbox(Facing direction) {
		Hitbox h = new Hitbox(this);
		h.move(direction);
		return h;
	}
	
	private int getXLeft() {
		switch (facing) {
			case UP:
				return x + fromLeft;
			case DOWN:
				return x + fromRight;
			case LEFT:
				return x + fromTop;
			case RIGHT:
				return x + fromBot;
		}
		return 0;
	}
	
	private int getXRight() {
		switch (facing) {
			case UP:
				return x + width - fromRight;
			case DOWN:
				return x + width - fromLeft;
			case LEFT:
				return x + height - fromBot;
			case RIGHT:
				return x + height - fromTop;
		}
		return 0;
	}
	
	private int getYTop() {
		switch (facing) {
			case UP:
				return y + fromTop;
			case DOWN:
				return y + fromBot;
			case LEFT:
				return y + fromRight;
			case RIGHT:
				return y + fromLeft;
		}
		return 0;
	}
	
	private int getYBot() {
		switch (facing) {
			case UP:
				return y + height - fromTop;
			case DOWN:
				return y + height - fromBot;
			case LEFT:
				return y + width - fromRight;
			case RIGHT:
				return y + width - fromLeft;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		
		return facing.getColumn() % 2 == 0 ? " x: " + getXLeft() + " - " + getXRight() + " y: " + getYTop() + " - " + getYBot() + "\n" : " x: " + x + " - " + (x + height) + " y: " + y + " - " + (y + width) + "\n";
	}
	
}
