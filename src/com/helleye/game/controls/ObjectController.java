package com.helleye.game.controls;

import com.helleye.engine.utils.WindowExtra;
import com.helleye.game.objects.entity.EntityBase;
import com.helleye.game.objects.entity.EntityCharacter;
import com.helleye.game.objects.entity.EntityPlayer;
import com.helleye.game.objects.entity.EntityProjectile;
import com.helleye.game.objects.ObjectBase;
import com.helleye.game.objects.entity.Facing;
import com.helleye.game.objects.tile.TileStatic;
import com.helleye.game.GameManager;
import com.helleye.game.state.MapState;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ObjectController {
	private List<TileStatic> objects;
	private List<EntityProjectile> projectiles;
	private List<EntityCharacter> characters;
	private List<ObjectBase> toRemove;
	private EntityPlayer player;
	private WindowExtra debugWindow;
	private GameManager gm;
	private MapState map;
	/*
	TODO !!!
	keep data in array of some base object type to simplify checking for colission and speed up access to a tile with given estimate coordinates ? Maybe??
	(notice collisions through instanceof, with projectiles dealing damage and other things stopping movement) ? probably not?
	implement a way to access game board state through the array.
	fix projectile appearing far away from the shooter.
	*/
	private final static String MAP_PATH="/map.png";
	public ObjectController(GameManager gm) {
		objects = new ArrayList<>();
		projectiles = new ArrayList<>();
		characters = new ArrayList<>();
		toRemove = new ArrayList<>();
		map=new MapState(MAP_PATH, this);
		this.gm = gm;
	}
	
	public void resetMap(){
		map=new MapState(MAP_PATH, this);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public void remove(ObjectBase entity) {
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
	
	public void createDebugWindow() {
		if (debugWindow != null) deleteDebugWindow();
		debugWindow = new WindowExtra(400, 300, 1400, 400, "Debug");
	}
	
	public void deleteDebugWindow() {
		debugWindow.getFrame().dispatchEvent(new WindowEvent(debugWindow.getFrame(), WindowEvent.WINDOW_CLOSING));
		debugWindow = null;
	}
	
	public void addTextInDebug(String text){
		debugWindow.addText(text);
	}
	
	public void setTextInDebug(String text) {
		debugWindow.setText(text);
	}
	
	public void clearTextInDebug() {
		debugWindow.clearText();
	}
	
	public void handlePlayerEvent(PlayerAction action) {
		if (action == PlayerAction.UP) {
			if (player.canMove(Facing.UP, this)) player.move(Facing.UP, this);
		}
		else if (action == PlayerAction.DOWN) {
			if (player.canMove(Facing.DOWN, this))
				player.move(Facing.DOWN, this);
		}
		else if (action == PlayerAction.RIGHT) {
			if (player.canMove(Facing.RIGHT, this))
				player.move(Facing.RIGHT, this);
		}
		else if (action == PlayerAction.LEFT) {
			if (player.canMove(Facing.LEFT, this))
				player.move(Facing.LEFT, this);
		}
		else if (action == PlayerAction.SHOOT) {
			addEntity(player.shoot());
		}
		else if (action == PlayerAction.PAUSE) {
			;//TODO handle pause
		}
		else if (action == PlayerAction.DEBUG) {
			if(debugWindow==null)
				createDebugWindow();
			else deleteDebugWindow();
		}
		else if(action==PlayerAction.SUP){
			if (player.canMove(Facing.UP, this))
				player.slide(Facing.UP, this);
		}
		else if(action==PlayerAction.SDOWN){
			if (player.canMove(Facing.DOWN, this))
				player.slide(Facing.DOWN, this);
		}
		else if(action==PlayerAction.SRIGHT){
			if (player.canMove(Facing.RIGHT, this))
				player.slide(Facing.RIGHT, this);
		}
		else if(action==PlayerAction.SLEFT){
			if (player.canMove(Facing.LEFT, this))
				player.slide(Facing.LEFT, this);
		}
		else if(action==PlayerAction.RESET){
			player.setPos(50, 50);
		}
		
	}
	public void updateWindows() {
		
		if (debugWindow != null) {
			debugWindow.setText(getDebugInfo());
			
		}
	}
	
	private String getDebugInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(player.toString()).append("\n").append(player.getHitbox());
//		for(TileStatic o: getTiles())
//			sb.append(o.toString());
		return sb.toString();
	}
	
	public List<EntityProjectile> getProjectiles() {
		return projectiles;
	}
	
	public List<TileStatic> getTiles() {
		return objects;
	}
	
	public List<EntityCharacter> getCharacters() {
		return characters;
	}
	
	public void addEntity(EntityBase entity) {
		if (entity instanceof EntityProjectile) projectiles.add((EntityProjectile) entity);
		else if (entity instanceof EntityCharacter) characters.add((EntityCharacter) entity);
		else throw new TypeMismatchException("No list for this type of entity");
	}
	
	public void addGameObject(ObjectBase object) {
		if (object instanceof TileStatic) objects.add((TileStatic) object);
		else throw new TypeMismatchException("No list for this type of object");
	}
	
	public void removeAll() {
		characters.removeAll(toRemove);
		objects.removeAll(toRemove);
		projectiles.removeAll(toRemove);
		toRemove.clear();
	}
	
	public enum PlayerAction {
		UP, DOWN, RIGHT, LEFT, SHOOT, PAUSE, DEBUG, SUP, SDOWN, SLEFT, SRIGHT, RESET;
	}
}
