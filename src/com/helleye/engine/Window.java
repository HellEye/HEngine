package com.helleye.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	
	public JFrame getFrame() {
		return frame;
	}
	
	private JFrame frame;
	private BufferedImage image;
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	private Canvas canvas;
	private Graphics graphics;
	private BufferStrategy bufferStrategy;
	
	public Window(GameContainer gc){
		image=new BufferedImage(GameContainer.P_WIDTH, GameContainer.P_HEIGHT, BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension dim = new Dimension((int) (GameContainer.P_WIDTH*gc.getScale()), (int) (GameContainer.P_HEIGHT*gc.getScale()));
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		
		frame=new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //End program on pressing x
		frame.setLayout(new BorderLayout()); //Layout
		frame.add(canvas, BorderLayout.CENTER); //put frame in center
		frame.pack(); //match frame dimensions with canvas
		frame.setLocationRelativeTo(null); //start in the middle of screen
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy=canvas.getBufferStrategy();
		graphics=bufferStrategy.getDrawGraphics();
		
	}
	
	public void update(){
		graphics.drawImage(image,0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}
}
