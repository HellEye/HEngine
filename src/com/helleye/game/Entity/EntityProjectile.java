package com.helleye.game.Entity;

import com.helleye.engine.GameContainer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.EntityController;

public class EntityProjectile extends EntityBase {
	private int damage;
	private EntityBase shooter;
	private static final ImageTile LASER_RED = new ImageTile("/LaserShotTileBig.png", 16, 16).setLayer(4);
	public EntityProjectile(int xPos, int yPos, int width, int height, Image image, int speed, int damage, Facing facing, EntityBase shooter) {
		super(xPos, yPos, width, height, image, speed);
		this.damage = damage;
		setFacing(facing);
		if (facing.getColumn() % 2 == 0) {
			setHitbox(3, 3, 7, 7);
		}
		else {
			setHitbox(7, 7, 3, 3);
		}
		this.shooter=shooter;
	}
	
	public EntityBase getShooter() {
		return shooter;
	}
	
	public static EntityProjectile createProjectile(EntityCharacter shooter, int width, int height, int speed){
		if(shooter.getFacing()==Facing.UP)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()-shooter.getHeight(), width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if(shooter.getFacing()==Facing.DOWN)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()+shooter.getHeight(), width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if(shooter.getFacing()==Facing.RIGHT)
			return new EntityProjectile(shooter.getxPos()+shooter.getWidth(), shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if(shooter.getFacing()==Facing.LEFT)
			return new EntityProjectile(shooter.getxPos()-shooter.getWidth(), shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else return null;
	}
	
	@Override
	public void update(EntityController controller) {
		super.update(controller);
		move(getFacing());
		if(getxPos()<-16||getyPos()<-16||getxPos()> GameContainer.P_WIDTH+16||getyPos()>GameContainer.P_HEIGHT+16)
			controller.remove(this);
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return "Projectile shot by \n  " + shooter +" \n   with hitbox of "+getHitbox();
	}
}
