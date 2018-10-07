package com.helleye.engine;

import com.helleye.engine.gfx.Font;
import com.helleye.engine.gfx.Image;
import com.helleye.engine.gfx.ImageRequest;
import com.helleye.engine.gfx.ImageTile;
import com.helleye.game.Entity.ObjectBase;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
	private int pWidth, pHeight;
	private int[] pixels;
	private Font font;
	private int[] zBuffer;
	private int zDepth = 0;
	private List<ImageRequest> imageRequests = new ArrayList<>();
	public Renderer(GameContainer gc) {
		pWidth = GameContainer.P_WIDTH;
		pHeight = GameContainer.P_HEIGHT;
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		font = new Font("/font5.png");
		zBuffer = new int[pixels.length];
	}
	
	
	public void addImage(ObjectBase entity, int layer) {
		addImage(entity.getImage(), entity.getxPos(), entity.getyPos(), layer);
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff444444;
			zBuffer[i] = 0;
		}
	}
	
	private void setPixel(int x, int y, int value) {
		int alpha = ((value >> 24) & 0xff);
		if (x >= 0 && y >= 0 && x <= pWidth && y <= pHeight && alpha != 0 && zBuffer[x + y * pWidth] <= zDepth) {
			if (alpha == 255) pixels[x + y * pWidth] = value;
			else {
				//Magic
				int p = pixels[x + y * pWidth];
				int r = (int) (((p >> 16) & 0xff) * (1 - (alpha / 255f)) + (value >> 16 & 0xff) * (alpha / 255f));
				int g = (int) (((p >> 8) & 0xff) * (1 - (alpha / 255f)) + (value >> 8 & 0xff) * (alpha / 255f));
				int b = (int) ((p & 0xff) * (1 - (alpha / 255f)) + (value & 0xff) * (alpha / 255f));
				
				pixels[x + y * pWidth] = (255 << 24 | r << 16 | g << 8 | b);
			}
		}
	}
	
	/*
	private void setArrayRegion(RenderObject image, int offX, int offY) {
		if (image != null) for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++)
				setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
	}
	*/
	public void addImage(Image image, int offX, int offY, int depth) {
		imageRequests.add(new ImageRequest(image, depth, offX, offY));
	}
	
	private void drawImage(Image image, int offX, int offY) {
		if (offX < -image.getWidth() || offY < -image.getHeight() || offX >= GameContainer.P_WIDTH || offY >= GameContainer.P_HEIGHT)
			return; //don't render at all
		//TODO fix crash when cutting entire image to the right
		int newWidth = image.getWidth();
		int newHeight = image.getHeight();
		int newX = 0;
		int newY = 0;
		//cut stuff that's outside of render area
		
		//cut right side
		if (newWidth + offX > GameContainer.P_WIDTH)
			newWidth -= newWidth + offX - GameContainer.P_WIDTH;
		//cut bottom
		if (newHeight + offY > GameContainer.P_HEIGHT)
			newHeight -= ((newHeight + offY) - GameContainer.P_HEIGHT);
		//cut left side
		if (offX < 0) newX -= offX;
		//cut top
		if (offY < 0) newY -= offY;
		
		for (int y = newY; y < newHeight; y++)
			for (int x = newX; x < newWidth; x++)
				setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
	}
	
	public void drawImageList() {
		imageRequests.sort(null);
		for (ImageRequest ir : imageRequests) drawImage(ir.image, ir.offX, ir.offY);
		imageRequests.clear();
	}
	
	public void addText(String text, int offX, int offY, int color, int depth) {
		text = text.toUpperCase();
		int offset = 0;
		int width = font.getTextLength(text);
		int[] pixels = new int[width * font.getHeight()];
		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i);
			for (int y = 1; y < font.getFontImage().getHeight(); y++)
				for (int x = 0; x < font.getWidths()[unicode]; x++)
					if (font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff) {
						pixels[x + offset + y * width] = color;
					}
			offset += font.getWidths()[unicode];
		}
		addImage(new Image(pixels, width, font.getHeight()), offX, offY, depth);
	}
	
	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		if (offX < -image.getTileW() || offY < -image.getTileH() || offX > pWidth || offY > pHeight)
			return; //don't render at all
		int newX = 0;
		int newY = 0;
		int newWidth = image.getTileW();
		int newHeight = image.getTileH();
		
		//cut stuff that's outside of render area
		
		if (offX < 0) newX -= offX; //cut left side
		if (offY < 0) newY -= offY; //cut top
		if (newWidth + offX > pWidth) newWidth -= newWidth + offX - pWidth; //cut right side
		if (newHeight + offY > pHeight) newHeight -= newHeight + offY - pHeight; //cut bottom
		
		for (int y = newY; y < newHeight; y++)
			for (int x = newX; x < newWidth; x++)
				setPixel(x + offX, y + offY, image.getPixels()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getWidth()]);
	}
	
	public void addRect(int offX, int offY, int width, int height, int color, boolean filled, int depth) {
		
		if (filled) {
			int[] pixels = new int[height * width];
			for (int y = 0; y < height; y++)
				for (int x = 0; x < width; x++)
					pixels[x + y * width] = color;
			//setPixel(x + offX, y + offY, color); //CHECKME
			addImage(new Image(pixels, width, height), offX, offY, depth);
		}
		else {
			int[] pixels = new int[height * width];
			
			for (int y = 0; y < height; y++)
				for (int x = 0; x < height; x++)
					if (x == 0 || y == 0 || x == width - 1 || y == height - 1)
						pixels[x + y * width] = color;
			
			addImage(new Image(pixels, width, height), offX, offY, depth);
		}
	}
	
}
