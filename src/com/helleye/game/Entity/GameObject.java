package com.helleye.game.Entity;

import com.helleye.engine.gfx.Image;

public interface GameObject {
	Hitbox getHitbox();
	
	Image getImage();
	
	void setPos(int xPos, int yPos);
	
	int getxPos();
	
	void setxPos(int xPos);
	
	int getyPos();
	
	void setyPos(int yPos);
	
	int getWidth();
	
	int getHeight();
}
