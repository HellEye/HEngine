package com.helleye.engine.gfx;

public class Font {
	public static final Font standard = new Font("/font5.png");
	private Image fontImage;
	private int[] offsets;
	private int[] widths;
	
	public Font(String path) {
		fontImage = new Image(path);
		offsets = new int[256];
		widths = new int[256];
		int unicode = 0;
		for (int i = 0; i < fontImage.getWidth(); i++) {
			if (fontImage.getPixels()[i] == 0xff0000ff) offsets[unicode] = i;
			else if (fontImage.getPixels()[i] == 0xffffff00)
				widths[unicode] = i - offsets[unicode++];
		}
	}
	public int getTextLength(String text){
		int sum=0;
		for(char c:text.toCharArray())
			sum+=widths[c];
		return sum;
	}
	
	public int[] getPixels() {
		return fontImage.getPixels();
	}
	
	public Image getFontImage() {
		return fontImage;
	}
	
	public int[] getWidths() {
		return widths;
	}
	
	public int[] getOffsets() {
		return offsets;
	}
	
	public int getHeight() {return fontImage.getHeight();}
}
