package com.helleye.game.objects.entity;

import com.helleye.engine.GameContainer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.ObjectController;
import com.helleye.game.objects.tile.TileBreakable;
import com.helleye.game.objects.tile.TileStatic;

public class EntityProjectile extends EntityBase {
	private static final ImageTile LASER_RED = new ImageTile("/LaserShotTileBig.png", 16, 16).setLayer(4);
	private int damage;
	private EntityBase shooter;
	
	public EntityProjectile(int xPos, int yPos, int width, int height, Image image, int speed, int damage, Facing facing, EntityBase shooter) {
		super(xPos, yPos, width, height, image, speed);
		this.damage = damage;
		setFacing(facing);
		
		setHitbox(3, 3, 7, 7, getFacing());
		
		this.shooter = shooter;
	}
	
	public static EntityProjectile createProjectile(EntityCharacter shooter, int width, int height, int speed) {
		if (shooter.getFacing() == Facing.UP)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()-3, width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if (shooter.getFacing() == Facing.DOWN)
			return new EntityProjectile(shooter.getxPos(), shooter.getyPos()+3, width, height, LASER_RED.getImage(0, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if (shooter.getFacing() == Facing.RIGHT)
			return new EntityProjectile(shooter.getxPos()+3, shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else if (shooter.getFacing() == Facing.LEFT)
			return new EntityProjectile(shooter.getxPos() -3, shooter.getyPos(), height, width, LASER_RED.getImage(1, 0), speed, shooter.getDamage(), shooter.getFacing(), shooter);
		else return null;
	}
	
	public EntityBase getShooter() {
		return shooter;
	}
	
	@Override
	public void update(ObjectController controller) {
		super.update(controller);
		slide(getFacing(), controller);
		if (getxPos() < -16 || getyPos() < -16 || getxPos() > GameContainer.P_WIDTH + 16 || getyPos() > GameContainer.P_HEIGHT + 16)
			controller.remove(this);
		for (EntityBase entity : controller.getCharacters())
			if (isColiding(entity)) {
				if (!entity.isInvulnerable()) {
					System.out.println("HIT by " + this.toString() + "   on\n   " + entity.toString());
					entity.setHit(true);
				}
				controller.remove(this);
			}
		for (TileStatic o : controller.getTiles())
			if (isColiding(o)) {
				if (!o.isInvulnerable()) {
					o.setHit(true);
					if(o instanceof TileBreakable) ((TileBreakable)o).getHit(getDamage(), controller);
					System.out.println("HIT by " + this.toString() + "    on\n   " + o.toString());
				}
				controller.remove(this);
			}
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return "Projectile shot by \n  " + shooter + " \n   with hitbox of " + getHitbox();
	}
}
