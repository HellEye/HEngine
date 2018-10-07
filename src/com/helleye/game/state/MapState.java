package com.helleye.game.state;

import com.helleye.engine.GameContainer;
import com.helleye.game.Entity.ObjectBase;

import java.util.ArrayList;
import java.util.List;

public class MapState {
	private List<ObjectBase> mapState = new ArrayList<>(GameContainer.TILE_WIDTH*GameContainer.TILE_HEIGHT);
	
}
