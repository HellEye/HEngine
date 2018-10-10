package com.helleye.game.objects;

import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.controls.ObjectController;
import com.helleye.game.objects.entity.EntityProjectile;
import com.helleye.game.objects.entity.Facing;
import com.helleye.game.objects.tile.TileBreakable;

public abstract class ObjectBase {
	protected int xPos;
	protected int yPos;
	protected int animSpeed = 1;
	protected int animBuffer = 0;
	protected int frame;
	protected int frameAmount;
	boolean hit = false;
	private Hitbox hitbox;
	private Image image;
	private int width;
	private int iFrameDuration = 20;
	private int iFrameBuffer=0;
	private boolean invulnerable = false;
	private int height;
	private boolean visible = true;
	
	public ObjectBase(int xPos, int yPos, int width, int height, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		setImage(image);
		setHitbox(0, 0, 0, 0, Facing.UP);
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public boolean isColiding(ObjectBase entity) {
		return (getHitbox().isInHitbox(entity.getHitbox()) && !(entity instanceof EntityProjectile)) && ((EntityProjectile) this).getShooter() != entity; // obsolete? && (entity != this)
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void update(ObjectController ec) {
		frame = (frame + (animBuffer + 1) / animSpeed) % frameAmount;
		animBuffer = (animBuffer + 1) % animSpeed;
		if(hit) {
			setInvulnerable(true);
			setHit(false);
		}
		if(invulnerable) {
			setVisible(iFrameBuffer++ % 8 > 3);
		}
		if(iFrameBuffer>=iFrameDuration){
			setVisible(true);
			setInvulnerable(false);
			iFrameBuffer=0;
		}
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public int getFrameAmount() {
		return frameAmount;
	}
	
	public void setFrameAmount(int frameAmount) {
		this.frameAmount = frameAmount;
	}
	
	public int getAnimBuffer() {
		return animBuffer;
	}
	
	public void setAnimBuffer(int animBuffer) {
		this.animBuffer = animBuffer;
	}
	
	public int getAnimSpeed() {
		return animSpeed;
	}
	
	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}
	
	public void setHitbox(int fromTop, int fromBot, int fromLeft, int fromRight, Facing facing) {
		hitbox = new Hitbox(xPos, yPos, width, height, facing, fromTop, fromBot, fromLeft, fromRight);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		if (image instanceof ImageTile)
			frameAmount = image.getHeight() / ((ImageTile) image).getTileH();
		if (frameAmount < 1) frameAmount = 1;
	}
	
	public void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		getHitbox().setPos(xPos, yPos);
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
