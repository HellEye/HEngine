package com.helleye.game.Entity;

public class Hitbox {
	private int x, y, width, height;
	
	public Hitbox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public boolean isInHitbox(Hitbox hitbox){
		return isInHitbox(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	public boolean isInHitbox(int x, int y, int width, int height){
		return isInHitbox(x, y) || isInHitbox(x+width, y) || isInHitbox(x, y+height) || isInHitbox(x+width, y+height);
	}
	public boolean isInHitbox(int x, int y){
		return (x>=this.x&&x<=this.x+this.width)&&(y>=this.y&&y<=this.y+this.width);
	}
	public void move(EntityBase.Facing direction){
		switch (direction){
			case UP: y--; break;
			case DOWN: y++; break;
			case LEFT: x--; break;
			case RIGHT: x++;
		}
	}
	
	@Override
	public String toString() {
		return " x:"+x+" y:"+y+" width "+width+" height "+height;
	}
}
