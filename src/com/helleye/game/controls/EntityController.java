package com.helleye.game.controls;

import com.helleye.game.Entity.EntityBase;
import com.helleye.game.Entity.EntityCharacter;
import com.helleye.game.Entity.EntityPlayer;
import com.helleye.game.Entity.EntityProjectile;
import com.helleye.game.Entity.ObjectBase;
import com.helleye.game.Entity.ObjectStatic;
import com.helleye.game.GameManager;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.util.ArrayList;
import java.util.List;

public class EntityController {
	private List<ObjectStatic> objects;
	private List<EntityProjectile> projectiles;
	private List<EntityCharacter> characters;
	private List<ObjectBase> toRemove;
	private EntityPlayer player;
	private GameManager gm;
	/*
	TODO !!!
	keep data in array of some base object type to simplify checking for colission and speed up access to a tile with given estimate coordinates ? Maybe??
	(notice collisions through instanceof, with projectiles dealing damage and other things stopping movement) ? probably not?
	implement a way to access game board state through the array.
	fix projectile appearing far away from the shooter.
	*/
	
	public EntityController(GameManager gm) {
		objects = new ArrayList<>();
		projectiles = new ArrayList<>();
		characters = new ArrayList<>();
		toRemove = new ArrayList<>();
		this.gm = gm;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public void remove(EntityBase entity) {
		toRemove.add(entity);
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(EntityPlayer player) {
		if (player != null) characters.remove(player);
		characters.add(player);
		this.player = player;
	}
	
	public void handlePlayerEvent(PlayerAction action) {
		if (action == PlayerAction.UP) {
			if (player.canMove(EntityBase.Facing.UP, this)) player.move(EntityBase.Facing.UP, this);
		}
		else if (action == PlayerAction.DOWN) {
			if (player.canMove(EntityBase.Facing.DOWN, this)) player.move(EntityBase.Facing.DOWN, this);
		}
		else if (action == PlayerAction.RIGHT) {
			if (player.canMove(EntityBase.Facing.RIGHT, this)) player.move(EntityBase.Facing.RIGHT, this);
		}
		else if (action == PlayerAction.LEFT) {
			if (player.canMove(EntityBase.Facing.LEFT, this)) player.move(EntityBase.Facing.LEFT, this);
		}
		else if (action == PlayerAction.SHOOT) {
			addEntity(player.shoot());
		}
		else if (action == PlayerAction.PAUSE) {
			;//TODO handle pause
		}else if(action==PlayerAction.DEBUG){
			System.out.println(player.toString()+"\n"+player.getHitbox());
		}
	}
	
	public List<EntityProjectile> getProjectiles() {
		return projectiles;
	}
	
	public List<ObjectStatic> getObjects() {
		return objects;
	}
	
	public List<EntityCharacter> getCharacters() {
		return characters;
	}
	
	public void addEntity(EntityBase entity) {
		if(entity instanceof EntityProjectile)
			projectiles.add((EntityProjectile) entity);
		else if(entity instanceof EntityCharacter)
			characters.add((EntityCharacter) entity);
		else throw new TypeMismatchException("No list for this type of entity");
	}
	public void addGameObject(ObjectBase object){
		if(object instanceof ObjectStatic)
			objects.add((ObjectStatic) object);
		else throw new TypeMismatchException("No list for this type of object");
	}
	
	public void removeAll() {
		characters.removeAll(toRemove);
		objects.removeAll(toRemove);
		projectiles.removeAll(toRemove);
		toRemove.clear();
	}
	
	public enum PlayerAction {
		UP, DOWN, RIGHT, LEFT, SHOOT, PAUSE, DEBUG;
	}
}
