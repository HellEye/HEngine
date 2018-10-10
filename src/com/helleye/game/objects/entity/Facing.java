package com.helleye.game.objects.entity;

public enum Facing {
	UP(0), LEFT(1), DOWN(2), RIGHT(3);
	private int column;
	
	Facing(int column) {this.column = column;}
	
	public int getColumn() {
		return column;
	}
}
