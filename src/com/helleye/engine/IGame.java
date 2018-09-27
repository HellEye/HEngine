package com.helleye.engine;

public interface IGame {
	void update(GameContainer gc, float deltaT);
	void render(GameContainer gc, Renderer renderer);
	void initiateRender(GameContainer gc, Renderer renderer);
	
}
