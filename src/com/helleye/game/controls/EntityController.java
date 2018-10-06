package com.helleye.game.controls;

import com.helleye.game.Entity.EntityBase;
import com.helleye.game.Entity.EntityPlayer;
import com.helleye.game.GameManager;

import java.util.ArrayList;
import java.util.List;

public class EntityController {
	private List<EntityBase> list;
	private List<EntityBase> toRemove;
	private EntityPlayer player;
	private GameManager gm;
	/*
	TODO !!!
	keep data in array of some base object type to simplify checking for colission and speed up access to a tile with given estimate coordinates
	(notice collisions through instanceof, with projectiles dealing damage and other things stopping movement)
	implement a way to access game board state through the array.
	fix projectile appearing far away from the shooter.
	*/
	
	public EntityController(GameManager gm) {
		list = new ArrayList<>();
		toRemove = new ArrayList<>();
		this.gm = gm;
	}
	
	public void remove(EntityBase entity) {
		toRemove.add(entity);
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(EntityPlayer player) {
		if (player != null) list.remove(player);
		list.add(player);
		this.player = player;
	}
	
	public void handlePlayerEvent(PlayerAction action) {
		if (action == PlayerAction.UP) {
			if (player.canMove(EntityBase.Facing.UP)) player.move(EntityBase.Facing.UP);
		}
		else if (action == PlayerAction.DOWN) {
			if (player.canMove(EntityBase.Facing.DOWN)) player.move(EntityBase.Facing.DOWN);
		}
		else if (action == PlayerAction.RIGHT) {
			if (player.canMove(EntityBase.Facing.RIGHT)) player.move(EntityBase.Facing.RIGHT);
		}
		else if (action == PlayerAction.LEFT) {
			if (player.canMove(EntityBase.Facing.LEFT)) player.move(EntityBase.Facing.LEFT);
		}
		else if (action == PlayerAction.SHOOT) {
			addEntity(player.shoot());
		}
		else if (action == PlayerAction.PAUSE) {
			;//TODO handle pause
		}
	}
	
	public void addEntity(EntityBase entity) {
		list.add(entity);
	}
	
	public List<EntityBase> getList() {
		return list;
	}
	
	public void removeAll() {
		list.removeAll(toRemove);
		toRemove.clear();
	}
	
	public enum PlayerAction {
		UP, DOWN, RIGHT, LEFT, SHOOT, PAUSE;
	}
}
