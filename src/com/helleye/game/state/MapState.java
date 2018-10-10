package com.helleye.game.state;

import com.helleye.engine.GameContainer;
import com.helleye.engine.gfx.Image;
import com.helleye.game.objects.ObjectBase;
import com.helleye.game.objects.tile.TileBrick;
import com.helleye.game.objects.tile.TileStatic;
import com.helleye.game.controls.ObjectController;

public class MapState {
	private ObjectBase[] map;
	private int mapWidth;
	private int mapHeight;
	ObjectController ec;
	public MapState(String mapPath, ObjectController ec) {
		this.ec=ec;
		Image mapImage = new Image(mapPath);
		mapWidth = Math.min(mapImage.getWidth(), GameContainer.TILE_WIDTH);
		mapHeight = Math.min(mapImage.getHeight(), GameContainer.TILE_HEIGHT);
		map = new ObjectBase[mapWidth*mapHeight];
		Image brickImage = new Image("/Brick.png").setLayer(4);
		brickImage.setWidth(GameContainer.TILE_SIZE);
		brickImage.setHeight(GameContainer.TILE_SIZE);
		for (int x = 0; x < mapWidth; x++)
			for (int y = 0; y < mapHeight; y++) {
				if (mapImage.getPixels()[x + y * mapImage.getWidth()] == MapBlock.BRICK.getColor()) {
					ObjectBase o = new TileBrick(GameContainer.TILE_SIZE*x, GameContainer.TILE_SIZE*y, GameContainer.TILE_SIZE, GameContainer.TILE_SIZE, new Image(brickImage));
					map[x+y*mapWidth]=o;
					ec.addGameObject(o);
				}
				else if (mapImage.getPixels()[x + y * mapWidth] == MapBlock.AIR.getColor()) {
				}
				else if (mapImage.getPixels()[x + y * mapWidth] == MapBlock.METAL.getColor()) {
				}
				else if (mapImage.getPixels()[x + y * mapWidth] == MapBlock.SPAWNPOINT.getColor()) {
				}
				else if (mapImage.getPixels()[x + y * mapWidth] == MapBlock.OBJECTIVE.getColor()) {
				}
			}
	}
	
	private enum MapBlock {
		BRICK(0xff000000), AIR(0xffFFFFFF), METAL(0xff00FF00), SPAWNPOINT(0xffFF0000), OBJECTIVE(0xff0000FF);
		private final int color;
		
		MapBlock(int color) {this.color = color;}
		
		final int getColor() {return color;}
	}
	
}
