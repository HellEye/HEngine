package com.helleye.game.objects.tile;

import com.helleye.engine.gfx.Image;

public class TileBrick extends TileBreakable {
	public TileBrick(int xPos, int yPos, int width, int height, Image image) {
		super(xPos, yPos, width, height, image, 100);
	}
}
