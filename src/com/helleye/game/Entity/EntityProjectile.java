package com.helleye.game.Entity;

import com.helleye.engine.GameContainer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.GameManager;
import com.helleye.game.controls.EntityController;

import static com.helleye.game.Entity.EntityBase.Facing.*;

public class EntityProjectile extends EntityBase {
	private int damage;
	private static final ImageTile LASER_RED = new ImageTile("/LaserShotTile.png", 16, 16).setLayer(4);
	public EntityProjectile(int xPos, int yPos, int width, int height, Image image, int speed, int damage, Facing facing) {
		super(xPos, yPos, width, height, image, speed);
		this.damage = damage;
		setFacing(facing);
	}
	
	public static EntityProjectile createProjectile(EntityCharacter shooter, int width, int height, int speed){
		if(shooter.getFacing()==Facing.UP)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()-shooter.getHeight(), width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing());
		else if(shooter.getFacing()==Facing.DOWN)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()+shooter.getHeight(), width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing());
		else if(shooter.getFacing()==Facing.RIGHT)
			return new EntityProjectile(shooter.getxPos()+shooter.getWidth(), shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing());
		else if(shooter.getFacing()==Facing.LEFT)
			return new EntityProjectile(shooter.getxPos()-shooter.getWidth(), shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing());
		else return null;
	}
	
	@Override
	public void update(EntityController controller) {
		super.update(controller);
		move(getFacing());
		if(getxPos()<-16||getyPos()<-16||getxPos()> GameContainer.P_WIDTH+16||getyPos()>GameContainer.P_HEIGHT+16)
			controller.getToRemove().add(this);
	}
}
