package com.helleye.game.Entity;

public class Hitbox {
	private int x, y, width, height;
	private int fromTop, fromBot, fromRight, fromLeft;
	private EntityBase.Facing facing;
	
	public Hitbox(int x, int y, int width, int height, EntityBase.Facing facing, int fromTop, int fromBot, int fromLeft, int fromRight) {
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
	
	public EntityBase.Facing getFacing() {
		return facing;
	}
	
	public void setFacing(EntityBase.Facing facing) {
		this.facing = facing;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isInHitbox(Hitbox hitbox) {
		return isInHitbox(hitbox.x, hitbox.y, hitbox.width, hitbox.height, hitbox.facing, hitbox.fromTop, hitbox.fromBot, hitbox.fromLeft, hitbox.fromRight);
	}
	
	public boolean isInHitbox(int x, int y, int width, int height, EntityBase.Facing facing, int fromTop, int fromBot, int fromLeft, int fromRight) {
		if (facing == EntityBase.Facing.UP) {
			return isInHitbox(x + fromRight, y + fromTop) || isInHitbox(x + width - fromLeft, y + fromTop) || isInHitbox(x + fromRight, y + height - fromBot) || isInHitbox(x + width - fromLeft, y + height - fromBot) || isInHitbox(x + width / 2, y + fromTop) || isInHitbox(x + width / 2, y + height - fromBot) || isInHitbox(x + width - fromLeft, y + height / 2) || isInHitbox(x + fromRight, y + height / 2);
		}
		else if (facing == EntityBase.Facing.DOWN) {
			return isInHitbox(x + fromLeft, y + fromBot) || isInHitbox(x + width - fromRight, y + fromBot) || isInHitbox(x + fromLeft, y + height - fromTop) || isInHitbox(x + width - fromRight, y + height - fromTop) || isInHitbox(x + width / 2, y + fromBot) || isInHitbox(x + width / 2, y + height - fromTop) || isInHitbox(x + width - fromRight, y + height / 2) || isInHitbox(x + fromLeft, y + height / 2);
		}
		else if (facing == EntityBase.Facing.RIGHT) {
			return isInHitbox(x + fromBot, y + fromLeft) || isInHitbox(x + height - fromTop, y + fromLeft) || isInHitbox(x + fromBot, y + width - fromRight) || isInHitbox(x + height - fromTop, y + width - fromRight) || isInHitbox(x + height / 2, y + fromLeft) || isInHitbox(x + height / 2, y + width - fromRight) || isInHitbox(x + height - fromTop, y + width / 2) || isInHitbox(x + fromBot, y + width / 2);
		}
		else if (facing == EntityBase.Facing.LEFT) {
			return isInHitbox(x + fromTop, y + fromRight) || isInHitbox(x + height - fromBot, y + fromRight) || isInHitbox(x + fromTop, y + width - fromLeft) || isInHitbox(x + height - fromBot, y + width - fromLeft) || isInHitbox(x + height / 2, y + fromRight) || isInHitbox(x + height / 2, y + width - fromLeft) || isInHitbox(x + height - fromBot, y + width / 2) || isInHitbox(x + fromTop, y + width / 2);
		}
		return false;
	}
	
	public boolean isInHitbox(int x, int y) {
		return (x > getXLeft() && x < getXRight()) && (y > getYTop()&& y < getYBot());
	}
	
	public void move(EntityBase.Facing direction) {
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
	
	public Hitbox getOffsetHitbox(EntityBase.Facing direction) {
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
