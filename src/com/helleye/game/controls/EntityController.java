package com.helleye.game.controls;

import com.helleye.game.Entity.EntityBase;
import com.helleye.game.Entity.EntityPlayer;
import com.helleye.game.GameManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class EntityController {
	public enum PlayerAction{
		UP, DOWN, RIGHT, LEFT, SHOOT, PAUSE;
	}
	private List<EntityBase> list;
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(EntityPlayer player) {
		if(player!=null)
			list.remove(player);
		list.add(player);
		this.player = player;
	}
	
	private EntityPlayer player;
	private GameManager gm;
	public EntityController(GameManager gm) {
		list = new ArrayList<>();
		this.gm = gm;
	}
	
	public void handlePlayerEvent(PlayerAction action) {
		if(action==PlayerAction.UP){
			player.move(EntityBase.Facing.UP);
		}
		else if(action==PlayerAction.DOWN){
			player.move(EntityBase.Facing.DOWN);
		}
		else if (action==PlayerAction.RIGHT){
			player.move(EntityBase.Facing.RIGHT);
		}
		else if (action==PlayerAction.LEFT){
			player.move(EntityBase.Facing.LEFT);
		}
		else if(action==PlayerAction.SHOOT){
			;//TODO handle shooting
		}
		else if(action==PlayerAction.PAUSE){
			;//TODO handle pause
		}
	}
	
	public void addEntity(EntityBase entity) {
		list.add(entity);
	}
	
	public List<EntityBase> getList() {
		return list;
	}
}
