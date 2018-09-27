package com.helleye.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private final int NUM_KEYS = 256;
	private final int NUM_MBUTTONS = 5;
	private GameContainer gc;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	private boolean[] buttons = new boolean[NUM_KEYS];
	private boolean[] buttonsLast = new boolean[NUM_KEYS];
	private int mouseX = 0, mouseY = 0;
	private int scroll = 0;
	public Input(GameContainer gc) {
		this.gc = gc;
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public int getScroll() {
		return scroll;
	}
	public boolean isKey(int keyCode){
		return keys[keyCode];
	}
	public boolean isKeyUp(int keyCode){
		return !keys[keyCode]&&keysLast[keyCode];
	}
	public boolean isKeyDown(int keyCode){
		return keys[keyCode]&&!keysLast[keyCode];
	}
	public boolean isButton(int button){
		return buttons[button];
	}
	public boolean isButtonUp(int button){
		return !buttons[button]&&buttonsLast[button];
	}
	public boolean isButtonDown(int button){
		return buttons[button]&&!buttonsLast[button];
	}
	
	public void update() {
		scroll=0;
		for (int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
			buttonsLast[i] = buttons[i];
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//check if bounds necessary
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}
}