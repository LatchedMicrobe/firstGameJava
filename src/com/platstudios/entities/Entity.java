package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.platstudios.world.Camera;

import firstGameJava.Game;

public class Entity {
	
	
	protected static final BufferedImage LIFEPACK_EN = Game.spritesheet.getSpriteSheet(0,16,16,16);
	protected static final BufferedImage WEAPON_EN = Game.spritesheet.getSpriteSheet(16,16,16,16);
	protected static final BufferedImage BULLET_EN = Game.spritesheet.getSpriteSheet(32,16,16,16);
	protected static final BufferedImage ENEMY_EN = Game.spritesheet.getSpriteSheet(48,16,16,16);
	
	
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	public void render(Graphics surface) {
		surface.drawImage(sprite, this.x -Camera.x, this.y-Camera.y, null);
	}
}
