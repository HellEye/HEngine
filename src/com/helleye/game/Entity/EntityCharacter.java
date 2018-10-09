package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.EntityController;

public class EntityCharacter extends EntityBase {
	int temp;
	private int health, damage;
	
	public EntityCharacter(int xPos, int yPos, int width, int height, Image image, int speed, int health, int damage) {
		super(xPos, yPos, width, height, image, speed);
		this.health = health;
		this.damage = damage;
		//setHitbox(1, 1, 1, 1);
	}
	
	public EntityProjectile shoot() {
		return EntityProjectile.createProjectile(this, 16, 16, 120);
	}
	
	@Override
	public Image getImage() {
		if (!(super.getImage() instanceof ImageTile)) return super.getImage();
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
	public boolean move(Facing direction, EntityController ec) {
		return super.move(direction, ec);
	}
	
	@Override
	public boolean canMove(Facing direction, EntityController ec) {
		for (ObjectBase object : ec.getObjects())
			if(getHitbox().getOffsetHitbox(direction).isInHitbox(object.getHitbox())) return false;
		return true;
	}
	
	@Override
	public void update(EntityController controller) {
		super.update(controller);
		if (hit) {
			getImage().setLayer(-1);
			setHit(false);
		}
		else if (getImage().getLayer() == -1 && temp < 20) temp++;
		else if (getImage().getLayer() == -1) {
			getImage().setLayer(5);
			temp = 0;
		}
		//TODO update stuffff
	}
	
	@Override
	public String toString() {
		return "Character with HP: " + getHealth() + " and damage " + getDamage() + "\n  " + getHitbox();
	}
}
