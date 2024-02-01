package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import com.platstudios.world.Camera;

import firstGameJava.Game;

public class Entity {
	
	
	protected static final BufferedImage LIFEPACK_EN = Game.spritesheet.getSpriteSheet(0,16,16,16);
	protected static final BufferedImage WEAPON_EN = Game.spritesheet.getSpriteSheet(16,16,16,16);
	protected static final BufferedImage BULLET_EN = Game.spritesheet.getSpriteSheet(32,16,16,16);
	protected static final BufferedImage WEAPON_RIGHT_EN = Game.spritesheet.getSpriteSheet(0,32,16,16);
	protected static final BufferedImage WEAPON_LEFT_EN = Game.spritesheet.getSpriteSheet(16,32,16,16);
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public int maskX;
	public int maskY;
	public int maskW;
	public int maskH;
	
	
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;
		
	}
	
	public void setMask(int posX, int posY, int width, int height) {
		this.maskX = posX;
		this.maskY = posY;
		this.maskW = width;
		this.maskH = height;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.x + e1.maskX, e1.y + e1.maskY,e1.maskW,e1.maskH);
		Rectangle e2Mask = new Rectangle(e2.x + e2.maskX, e2.y + e2.maskY,e2.maskW,e2.maskH);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics surface) {
		surface.drawImage(sprite, this.x -Camera.x, this.y-Camera.y, null);
	}
}
