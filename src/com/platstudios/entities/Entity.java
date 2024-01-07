package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	
	private int x;
	private int y;
	private int width;
	private int height;
	protected BufferedImage sprite;
	
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
		surface.drawImage(sprite, this.x, this.y, null);
	}
}
