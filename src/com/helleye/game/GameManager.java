package com.helleye.game;

import com.helleye.engine.GameContainer;
import com.helleye.engine.IGame;
import com.helleye.engine.Renderer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.sfx.SoundClip;

public class GameManager implements IGame {
	private SoundClip clip;
	private Image mouseImage, fighterImage;
	public GameManager (){
		mouseImage = new Image("/TestHeartTransparent.png");
		fighterImage=new Image("/Fighter.png");
		clip=new SoundClip("/Audio/pew.wav");
		clip.setVolume(-10F);
		
	}
	public static void main(String[] args) {
		
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
	
	@Override
	public void update(GameContainer gc, float deltaT) { //deltaT = 1/60 s
		//put controls here
		//for keys use KeyEvent
		/*
		if(gc.getInput().isKey(KeyEvent.VK_A))
			mouseImage.setOffX(mouseImage.getOffX()-1);
		if(gc.getInput().isKey(KeyEvent.VK_D))
			mouseImage.setOffX(mouseImage.getOffX()+1);
		if(gc.getInput().isKey(KeyEvent.VK_W))
			mouseImage.setOffY(mouseImage.getOffY()-1);
		if(gc.getInput().isKey(KeyEvent.VK_S))
			mouseImage.setOffY(mouseImage.getOffY()+1);
			*/
		
	}
	
	
	
	@Override
	public void render(GameContainer gc, Renderer renderer) {
		
		renderer.addRect(10, 10, 30, 30, 0xffaa00aa, true, 0);
		renderer.addImage(fighterImage, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1);
		
	}
	
	//Render animation: youtube about ImageTile
}
