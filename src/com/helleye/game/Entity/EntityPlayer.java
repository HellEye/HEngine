package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;

public class EntityPlayer extends EntityCharacter {
	public EntityPlayer(int xPos, int yPos, int width, int height, Image image, int speed, int health, int damage) {
		super(xPos, yPos, width, height, image, speed, health, damage);
	}
	
}
