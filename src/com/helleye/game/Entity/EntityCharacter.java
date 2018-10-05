package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.EntityController;

public class EntityCharacter extends EntityBase {
	private int health, damage;
	
	
	public EntityCharacter(int xPos, int yPos, int width, int height, Image image, int speed, int health, int damage) {
		super(xPos, yPos, width, height, image, speed);
		this.health = health;
		this.damage = damage;
	}
	
	public EntityProjectile shoot(){
		return EntityProjectile.createProjectile(this, 2, 10, 120);
	}
	
	@Override
	public Image getImage() {
		if(!(super.getImage() instanceof ImageTile))
			return super.getImage();
		return ((ImageTile) super.getImage()).getImage(getFacing().getColumn(), getFrame());
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public void update(EntityController controller) {
		super.update(controller);
		//TODO update stuffff
	}
}
