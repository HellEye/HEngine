package com.helleye.game;

import com.helleye.engine.GameContainer;
import com.helleye.engine.IGame;
import com.helleye.engine.Renderer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.engine.sfx.SoundClip;
import com.helleye.game.Entity.EntityBase;
import com.helleye.game.Entity.EntityCharacter;
import com.helleye.game.Entity.EntityPlayer;
import com.helleye.game.controls.EntityController;

import java.awt.event.KeyEvent;

public class GameManager implements IGame {
	EntityController ec;
	float temp;
	private SoundClip clip;
	private ImageTile fighterImage;
	private Image mouseImage;
	private ImageTile laserShot;
	
	public GameManager() {
		mouseImage = new Image("/TestHeartTransparent.png");
		fighterImage = new ImageTile("/FighterTile.png", 16, 16).setLayer(5);
		clip = new SoundClip("/Audio/pew.wav");
		clip.setVolume(-10F);
		ec = new EntityController(this);
		//SPEED: speed/60 pixels per frame
		ec.setPlayer(new EntityPlayer(10, 10, fighterImage.getTileW(), fighterImage.getTileH(), fighterImage, 60, 20, 1));
		ec.getPlayer().setAnimSpeed(10);
		Image red = new Image("/SquareRed.png").setLayer(5);
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)
				ec.addEntity(new EntityCharacter(10*i, 10*j, 5, 5, new Image(red.getPixels(), 5, 5).setLayer(red.getLayer()), 0, 10, 0));
		
		
	}
	
	public static void main(String[] args) {
		
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
	
	@Override
	public void update(GameContainer gc, float deltaT) { //deltaT = 1/60 s
		//put controls here
		//for keys use KeyEvent
		if (gc.getInput().isKey(KeyEvent.VK_A))
			ec.handlePlayerEvent(EntityController.PlayerAction.LEFT);
		if (gc.getInput().isKey(KeyEvent.VK_D))
			ec.handlePlayerEvent(EntityController.PlayerAction.RIGHT);
		if (gc.getInput().isKey(KeyEvent.VK_W))
			ec.handlePlayerEvent(EntityController.PlayerAction.UP);
		if (gc.getInput().isKey(KeyEvent.VK_S))
			ec.handlePlayerEvent(EntityController.PlayerAction.DOWN);
		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE))
			ec.handlePlayerEvent(EntityController.PlayerAction.SHOOT);
		
		for (EntityBase entity : ec.getList())
			entity.update(ec);
		
		ec.removeAll();
	}
	
	@Override
	public void render(GameContainer gc, Renderer renderer) {
		for (EntityBase entity : ec.getList())
			renderer.addImage(entity, entity.getImage().getLayer());
		
		for (int i = 0; i < 13; i++)
			for (int j = 0; j < 12; j++) {
				renderer.addRect(20 * i, 20 * j + 10, 10, 10, 0xff222222, true, 0);
				renderer.addRect(20 * i + 10, 20 * j, 10, 10, 0xff222222, true, 0);
			}
		//renderer.addRect(10, 10, 30, 30, 0xffaa00aa, true, 0);
		//renderer.addImage(fighterImage, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1);
		
	}
	
	//Render animation: youtube about ImageTile
}
