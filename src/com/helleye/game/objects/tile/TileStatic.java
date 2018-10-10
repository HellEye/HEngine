package com.helleye.game.objects.tile;

import com.helleye.engine.gfx.Image;
import com.helleye.game.objects.ObjectBase;

public class TileStatic extends ObjectBase {
	
	public TileStatic(int xPos, int yPos, int width, int height, Image image) {
		super(xPos, yPos, width, height, image);
	}
	
	@Override
	public String toString() {
		return getHitbox().toString();
	}
}
