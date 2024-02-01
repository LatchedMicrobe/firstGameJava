package com.platstudios.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.platstudios.world.Camera;

import firstGameJava.Game;

public class BulletShoot extends Entity{
	
	private double dirX;
	private double dirY;
	private double speed = 4;
	private int life = 0,maxLife = 30;
	
	public BulletShoot(int x, int y, int width, int height, double dirX, double dirY) {
		
		super(x, y, width, height, Entity.BULLET_EN);
		this.dirX = dirX;
		this.dirY = dirY;
		
	}
	
	@Override
	public void tick() {
		this.setX((int)(this.getX() + dirX * speed));
		this.setY((int)(this.getY() + dirY * speed));
		life++;
		if(life > maxLife) {
			life = 0;
			Game.bullets.remove(this);
			return;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, this.getWidth(),this.getHeight());
	}
}
