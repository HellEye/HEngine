package com.helleye.engine.gfx;

public class RenderFont extends RenderObject {
	private Font font;
	private char[] text;
	
	public RenderFont(Font font, String text, int offX, int offY) {
		super(font.getFontImage(), offX, offY);
		this.font = font;
		if (text == null) return;
		this.text = text.toCharArray();
	}
	
	public RenderFont(String text, int offX, int offY) {
		this(Font.standard, text, offX, offY);
	}
	
	public RenderFont(String text) {
		this(Font.standard, text, 0, 0);
	}
	
	public RenderFont(int offX, int offY) {
		this(null, offX, offY);
	}
	
	public RenderFont() {
		this(null);
	}
	
	public String getText() {
		return new String(text);
	}
	
	public void setText(String text) {
		this.text = text.toCharArray();
	}
	
	@Override
	public int[] getPixels() {
		int width = getWidth();
		int pixels[] = new int[getHeight() * width];
		for (int i = 0; i < getHeight(); i++)
			for (char aText : text) {
				for (int j = 0; j < font.getWidths()[aText - 32]; i++) {
					pixels[i * width + j] = font.getPixels()[font.getOffsets()[aText - 32]];
				}
			}
		return pixels;
	}
	
	@Override
	public int getHeight() {
		return font.getHeight();
	}
	
	@Override
	public int getWidth() {
		int width = 0;
		for (char aText : text) width += font.getWidths()[aText - 32];
		return width;
	}
}
