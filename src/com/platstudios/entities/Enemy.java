package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.platstudios.world.Camera;
import com.platstudios.world.World;

import firstGameJava.Game;

public class Enemy extends Entity{
	
	private int speed = 1;
	private int maskX = 8, maskY = 8, maskH = 10, maskW = 10;
	private BufferedImage sprites[];
	
	protected int frames, maxFrames, currAnimation, maxAnimation;
	private int life;

	public Enemy(int x, int y, int width, int height,int life, BufferedImage[] sprites) {
		
		super(x, y, width, height, null);
		this.sprites = new BufferedImage[3];
		this.sprites[0] = sprites[0];
		this.sprites[1] = sprites[1];
		this.sprites[2] = sprites[2];
		frames = 0;
		maxFrames = 10;
		currAnimation = 0;
		maxAnimation = 2;
		this.life = life;
	}

	@Override 
	public void tick() {
		if(!isCollidingWithPlayer()) {
			if(this.getX() < Game.player.getX() && World.isFree(this.getX() + speed, this.getY())
				&& !isColliding(this.getX() + speed, this.getY())) {
				this.setX(this.getX() + speed);
			}else if(this.getX() > Game.player.getX() && World.isFree(this.getX() - speed, this.getY())
					&& !isColliding(this.getX() - speed, this.getY())) {
				this.setX(this.getX() - speed);
			}
			
			if(this.getY() < Game.player.getY() && World.isFree(this.getX(), this.getY() + speed)
				&& !isColliding(this.getX(), this.getY() + speed)) {
				this.setY(this.getY() + speed);
			}else if(this.getY() > Game.player.getY() && World.isFree(this.getX(), this.getY() - speed)
					&& !isColliding(this.getX(), this.getY() - speed)) {
				this.setY(this.getY() - speed);
			}
		}else {
			if(Game.rand.nextInt(100) < 10) {
				
				Game.player.life -= Game.rand.nextInt(3);
				Game.player.isDamaged = true;
			}
			
		}
		
		
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			currAnimation++;
			
			if(currAnimation > maxAnimation) {
				currAnimation = 0;
			}
		}
		
		isCollidingWithBullet();
		if(life <= 0) {
			destroySelf();
			return;
		}
	}
	
	@Override
	public void render(Graphics surface) {
		surface.drawImage(sprites[currAnimation], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	private void destroySelf() {
		Game.entities.remove(this);
		Game.enemies.remove(this);
		
	}
	public void isCollidingWithBullet() {
		Entity bullet;
		for(int index = 0; index < Game.bullets.size(); index++) {
			bullet = Game.bullets.get(index);
			if(isColliding(this, bullet)) {
				life--;
				Game.bullets.remove(index);
			}
		}
	}
	
	public boolean isCollidingWithPlayer() {
		Rectangle enemyRect = new Rectangle(this.getX() + maskX,this.getY()+ maskY,maskW,maskH);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),Game.player.getWidth(),Game.player.getHeight());
		return enemyRect.intersects(player);
	}
	public boolean isColliding(int xNext, int yNext) {
		
		Rectangle enemyRect = new Rectangle(xNext + maskX,yNext+ maskY,maskW,maskH);
		Rectangle targetRect;
		for(Enemy e : Game.enemies) {
			
			if(e == this) {
				continue;
			}
			
			targetRect = new Rectangle(e.getX() + maskX, e.getY() + maskY,maskW,maskH);
			
			if(enemyRect.intersects(targetRect)) {
				return true;
				
			}
			
		}
		return false;
	}
}
