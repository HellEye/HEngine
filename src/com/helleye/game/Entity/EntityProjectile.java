package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;

public class EntityProjectile extends EntityBase {
	private int damage;
	
	public EntityProjectile(int xPos, int yPos, int width, int height, Image image, int speed, int damage, Facing facing) {
		super(xPos, yPos, width, height, image, speed);
		this.damage = damage;
		setFacing(facing);
	}
	
	@Override
	public void update() {
		if(getFacing()==Facing.UP){
		//TODO
		}
	}
}
