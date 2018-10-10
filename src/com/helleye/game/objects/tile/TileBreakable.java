package com.helleye.game.objects.tile;

import com.helleye.engine.gfx.Image;
import com.helleye.game.controls.ObjectController;

public abstract class TileBreakable extends TileStatic {
	private int hp;
	private int maxHp;
	public int getHp() {
		return hp;
	}
	public void repair(){ //TODO finish adding different tiles, draw graphics
		hp=maxHp;
	}
	
	public void getHit(int damage, ObjectController ec){
		if(!isInvulnerable()) hp -= damage;
		if(hp<=0) ec.remove(this);
	}
	
	@Override
	public void update(ObjectController ec) {
		
		super.update(ec);
	}
	
	public TileBreakable(int xPos, int yPos, int width, int height, Image image, int hp) {
		super(xPos, yPos, width, height, image);
		this.hp = hp;
		maxHp=hp;
	}
	
	@Override
	public String toString() {
		return super.toString()+" HP: "+getHp();
	}
}
