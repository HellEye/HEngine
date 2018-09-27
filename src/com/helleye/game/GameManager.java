package com.helleye.game;

import com.helleye.engine.GameContainer;
import com.helleye.engine.IGame;
import com.helleye.engine.Renderer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageRectangle;
import com.helleye.engine.gfx.RenderObject;
import com.helleye.engine.sfx.SoundClip;

import java.awt.event.KeyEvent;

public class GameManager implements IGame {
	private SoundClip clip;
	private RenderObject mouseImage;
	public GameManager (){
		mouseImage = new RenderObject(new Image("/TestHeartTransparent.png")).setLayer(1);
		clip=new SoundClip("/Audio/pew.wav");
		clip.setVolume(-10F);
		mouseImage.setOff(GameContainer.P_WIDTH-32, GameContainer.P_HEIGHT-120);
	}
	public static void main(String[] args) {
		
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
	
	@Override
	public void update(GameContainer gc, float deltaT) { //deltaT = 1/60 s
		//put controls here
		//for keys use KeyEvent
		if(gc.getInput().isKey(KeyEvent.VK_A))
			mouseImage.setOffX(mouseImage.getOffX()-1);
		if(gc.getInput().isKey(KeyEvent.VK_D))
			mouseImage.setOffX(mouseImage.getOffX()+1);
		if(gc.getInput().isKey(KeyEvent.VK_W))
			mouseImage.setOffY(mouseImage.getOffY()-1);
		if(gc.getInput().isKey(KeyEvent.VK_S))
			mouseImage.setOffY(mouseImage.getOffY()+1);
		//TODO maybe add here?
	}
	
	public void initiateRender(GameContainer gc, Renderer renderer){
		renderer.addToQueue(mouseImage);
		renderer.addToQueue(new RenderObject(new ImageRectangle(25, 25, 0xffcc00cc, true), 5, 5).setLayer(0));
	}
	
	@Override
	public void render(GameContainer gc, Renderer renderer) {
		
		renderer.draw();
		//TODO figure out how to add new stuff to renderer
	}
}
