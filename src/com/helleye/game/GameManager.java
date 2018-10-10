package com.helleye.game;

import com.helleye.engine.GameContainer;
import com.helleye.engine.IGame;
import com.helleye.engine.Renderer;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.engine.sfx.SoundClip;
import com.helleye.game.controls.ObjectController;
import com.helleye.game.objects.ObjectBase;
import com.helleye.game.objects.entity.EntityBase;
import com.helleye.game.objects.entity.EntityCharacter;
import com.helleye.game.objects.entity.EntityPlayer;
import com.helleye.game.objects.entity.EntityProjectile;
import com.helleye.game.objects.tile.TileStatic;

import java.awt.event.KeyEvent;

public class GameManager implements IGame {
	ObjectController ec;
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
		ec = new ObjectController(this);
		//SPEED: speed/60 pixels per frame
		ec.setPlayer(new EntityPlayer(10, 10, fighterImage.getTileW(), fighterImage.getTileH(), fighterImage, 60, 20, 20));
		ec.getPlayer().setAnimSpeed(10);
		Image red = new Image("/SquareRed.png").setLayer(5);
		/*for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				ec.addEntity(new EntityCharacter(10*i, 10*j, 5, 5, new Image(red.getPixels(), 5, 5).setLayer(red.getLayer()), 0, 10, 0));
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				ec.addGameObject(new TileStatic(100+40*i, 100+40*j, 10, 10, new Image(red.getPixels(), 10, 10).setLayer(red.getLayer())));*/
		
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
			ec.handlePlayerEvent(ObjectController.PlayerAction.LEFT);
		if (gc.getInput().isKey(KeyEvent.VK_D))
			ec.handlePlayerEvent(ObjectController.PlayerAction.RIGHT);
		if (gc.getInput().isKey(KeyEvent.VK_W))
			ec.handlePlayerEvent(ObjectController.PlayerAction.UP);
		if (gc.getInput().isKey(KeyEvent.VK_S))
			ec.handlePlayerEvent(ObjectController.PlayerAction.DOWN);
		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE))
			ec.handlePlayerEvent(ObjectController.PlayerAction.SHOOT);
		if (gc.getInput().isKeyDown(KeyEvent.VK_P))
			ec.handlePlayerEvent(ObjectController.PlayerAction.DEBUG);
		if (gc.getInput().isKey(KeyEvent.VK_DOWN))
			ec.handlePlayerEvent(ObjectController.PlayerAction.SDOWN);
		if (gc.getInput().isKey(KeyEvent.VK_UP))
			ec.handlePlayerEvent(ObjectController.PlayerAction.SUP);
		if (gc.getInput().isKey(KeyEvent.VK_LEFT))
			ec.handlePlayerEvent(ObjectController.PlayerAction.SLEFT);
		if (gc.getInput().isKey(KeyEvent.VK_RIGHT))
			ec.handlePlayerEvent(ObjectController.PlayerAction.SRIGHT);
		if (gc.getInput().isKeyDown(KeyEvent.VK_O))
			ec.handlePlayerEvent(ObjectController.PlayerAction.RESET);
		for (EntityProjectile p : ec.getProjectiles())
			p.update(ec);
		for (EntityCharacter entity : ec.getCharacters())
			entity.update(ec);
		for(ObjectBase object : ec.getTiles())
			object.update(ec);
		ec.removeAll();
		ec.updateWindows();
	}
	
	@Override
	public void render(GameContainer gc, Renderer renderer) {
		for (EntityBase entity : ec.getCharacters())
			if (entity.isVisible())
				renderer.addImage(entity, entity.getImage().getLayer(), GameContainer.SCREEN_GAME);
		for (EntityBase entity : ec.getProjectiles())
			if (entity.isVisible())
				renderer.addImage(entity, entity.getImage().getLayer(), GameContainer.SCREEN_GAME);
		for (TileStatic object : ec.getTiles())
			if (object.isVisible())
				renderer.addImage(object, object.getImage().getLayer(), GameContainer.SCREEN_GAME);
		
		//fancy checker grid
		for (int x = 0; x < (GameContainer.TILE_WIDTH + 1) / 2; x++)
			for (int y = 0; y < (GameContainer.TILE_HEIGHT + 1) / 2; y++) {
				renderer.addRect(16 * x, 16 * y + 8, 8, 8, 0xff222222, true, 0, GameContainer.SCREEN_GAME);
				renderer.addRect(16 * x + 8, 16 * y, 8, 8, 0xff222222, true, 0, GameContainer.SCREEN_GAME);
			}
		//renderer.addRect(10, 10, 30, 30, 0xffaa00aa, true, 0);
		//renderer.addImage(fighterImage, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1);
		
	}
	
	//Render animation: youtube about ImageTile
}
