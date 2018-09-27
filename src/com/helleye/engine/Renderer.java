package com.helleye.engine;

import com.helleye.engine.gfx.IRenderable;
import com.helleye.engine.gfx.RenderFont;
import com.helleye.engine.gfx.RenderObject;

import java.awt.image.DataBufferInt;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Renderer {
	private int pWidth, pHeight;
	private int[] pixels;
	private List<RenderObject> renderList;
	private RenderFont font;
	
	public Renderer(GameContainer gc) {
		pWidth = GameContainer.P_WIDTH;
		pHeight = GameContainer.P_HEIGHT;
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		font = new RenderFont();
		font.getImage().setLayer(10);
		
		renderList = new LinkedList<>();
		//TODO implement queue adding and drawing
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setPixel(int x, int y, int value) {
		int alpha = ((value >> 24) & 0xff);
		if (x >= 0 && y >= 0 && x <= pWidth && y <= pHeight && alpha != 0) {
			if (alpha == 255) pixels[x + y * pWidth] = value;
			else {
				int p = pixels[x + y * pWidth];
				int r = ((p >> 16) & 0xff) - (int) (((p >> 16) & 0xff) - ((value >> 16) & 0xff) * (alpha / 255f));
				int g = ((p >> 8) & 0xff) - (int) (((p >> 8) & 0xff) - ((value >> 8) & 0xff) * (alpha / 255f));
				int b = (p & 0xff) - (int) ((p & 0xff) - (value & 0xff) * (alpha / 255f));
				pixels[x + y * pWidth] = (255 << 24 | r << 16 | g << 8 | b);
			}
		}
	}
	
	public void addToQueue(RenderObject image) {
		if (renderList.isEmpty()) renderList.add(image);
		else if(renderList.get(0).compareTo(image)<0)
			renderList.add(0, image);
		else {
			ListIterator<RenderObject> it = renderList.listIterator();
			while(it.hasNext())
				if (it.next().getImage().compareTo(image.getImage()) < 0) {
					it.previous();
					it.add(image);
					return;
				}
				it.add(image);
				
		}
		
	}
	
	public void draw() {
		clear();
		for (RenderObject next : renderList) {
			drawImage(next.getImage(), next.getOffX(), next.getOffY());
		}
	}
	
	public void drawImage(IRenderable image, int offX, int offY) {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
			}
		}
	}
	/*
	public void drawText(String text, int offX, int offY, int color) {
		text = text.toUpperCase();
		
		int offset = 0;
		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;
			for (int y = 1; y < font.getFontImage().getHeight(); y++)
				for (int x = 0; x < font.getWidths()[unicode]; x++)
					if (font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff) {
						setPixel(x + offX + offset, y + offY, color);
					}
			offset += font.getWidths()[unicode];
		}
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
		
		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, image.getPixels()[(x + tileX + image.getTileW()) + (y + tileY + image.getTileH()) * image.getWidth()]);
			}
		}
	}
	*/
	
	//TODO Move to queue based render
}
