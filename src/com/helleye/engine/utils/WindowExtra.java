package com.helleye.engine.utils;

import com.helleye.engine.GameContainer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowExtra {
	public JFrame getFrame() {
		return frame;
	}
	private String text;
	private JFrame frame;
	private JLabel label;
	private BufferedImage image;
	
	public BufferedImage getImage() {
		return image;
	}
	
	private Graphics graphics;
	private BufferStrategy bufferStrategy;
	public WindowExtra(int width, int height, int x, int y, String title){
		image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Dimension dim = new Dimension(width, height);
		frame=new JFrame(title);
		frame.setLocation(x, y);
		frame.setSize(dim);
		frame.setMaximumSize(dim);
		frame.setMinimumSize(dim);
		frame.setFocusableWindowState(false);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //End program on pressing x
		frame.setLayout(new BorderLayout()); //Layout
		//frame.add(canvas, BorderLayout.CENTER); //put frame in center
		label=new JLabel(title);
		frame.add(label, BorderLayout.CENTER);
		
		frame.pack(); //match frame dimensions with canvas
		//frame.setLocationRelativeTo(null); //start in the middle of screen
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public void setText(String text) {
		this.text=text;
		label.setText("<html>" + text.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
	}
	public void addText(String text){
		setText(this.text+text);
	}
	public void clearText(){
		text="";
		label.setText("");
	}
	
	public void update(){
		//graphics.drawImage(image,0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}
}
