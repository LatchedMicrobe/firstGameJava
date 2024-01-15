package com.platstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import firstGameJava.Game;

public class Tile {
	
	protected static final BufferedImage TILE_FLOOR = Game.spritesheet.getSpriteSheet(0, 0, 16, 16);
	protected static final BufferedImage TILE_WALL = Game.spritesheet.getSpriteSheet(16, 0, 16, 16);
	
	private BufferedImage sprite;
	private int posX, posY;
	
	public Tile(int posX, int posY, BufferedImage sprite) {
		this.posX = posX;
		this.posY = posY;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.posX - Camera.x, this.posY - Camera.y, null);
	}
}
