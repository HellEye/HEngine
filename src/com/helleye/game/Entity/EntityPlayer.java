package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;

public class EntityPlayer extends EntityCharacter {
	public EntityPlayer(int xPos, int yPos, int width, int height, Image image, int speed, int health, int damage) {
		super(xPos, yPos, width, height, image, speed, health, damage);
		setHitbox(1, 1, 1, 1);
	}
	
	@Override
	public String toString() {
		return "Player with HP: "+getHealth()+" and damage "+getDamage();
	}
}
