package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.platstudios.graphics.Spritesheet;
import com.platstudios.world.Camera;
import com.platstudios.world.World;

import firstGameJava.Game;

public class Player extends Entity{
	
	public boolean moved,up, down, left, right;
	public int speed = 2;
	
	private int lastPos = 0;
	private int frames = 0, maxFrames = 5, currAnimation = 0, maxAnimation = 4;
	private int damagedFrames = 0,damagedMaxFrames = 5;
	private BufferedImage rightPlayer[];
	private BufferedImage leftPlayer[];
	private BufferedImage damagedPlayer;
	
	public double life = 100, maxLife = 100;
	public int ammo = 0, maxAmmo = 30;
	public boolean isDamaged = false, hasGun = false, isShooting = false;
	public int mouseX, mouseY;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		
		super(x,y,width,height,sprite);
		rightPlayer = new BufferedImage[5];
		leftPlayer  = new BufferedImage[5];
		damagedPlayer = sprite.getSubimage(80, 0, width, height);
		
		for(int index = 0; index < 5; index++) {
			rightPlayer[index] = sprite.getSubimage(index * 16, 0, width, height);
			leftPlayer[index] = sprite.getSubimage(index * 16, 16, width, height);
		}
	}
	
	@Override
	public void tick() {
		moved = false;
		if(right && World.isFree(this.getX() + speed, this.getY())) {
			moved = true;
			this.setX(this.getX() + speed);
			lastPos = 0;
		}else if(left && World.isFree(this.getX() - speed, this.getY())) {
			moved = true;
			this.setX(this.getX() - speed);
			lastPos = 1;
		}
		if(up && World.isFree(this.getX(), this.getY() - speed)) {
			moved = true;
			this.setY(this.getY() - speed);
		}else if(down && World.isFree(this.getX(), this.getY() + speed)) {
			moved = true;
			this.setY(this.getY() + speed);

		}
		
		if(life <= 0) {
			Game.spritesheet = new Spritesheet("/spritesheet.png");
			Game.image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
			Game.entities = new ArrayList<Entity>();
			Game.enemies = new ArrayList<Enemy>();
			Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSpriteSheet(64, 0, 96, 32));
			Game.entities.add(Game.player);
			Game.world = new World("/map.png");
		}
		
		if(moved) {
			frames++;
			if(frames > maxFrames) {
				frames = 0;
				currAnimation++;
				
				if(currAnimation > maxAnimation) {
					currAnimation = 0;
				}
			}
		}
		
		if(isDamaged) {
			damagedFrames++;
			if(damagedFrames > damagedMaxFrames) {
				damagedFrames = 0;
				isDamaged = false;
			}
		}
		
		if(isShooting) {
			isShooting = false;
			
			double dirX = 0;
			double dirY = 0;
			
			int deslX = 0;
			int deslY = 6;
			double angle = 0;
			
			
			if(hasGun && ammo > 0) {
				ammo --;
				
				if(lastPos == 1) {
					deslX = -8;
					angle = Math.atan2(mouseY - this.getY() - deslY + Camera.y, mouseX - this.getX() - deslX + Camera.x);
				}else {
					deslX = 16;
					angle = Math.atan2(mouseY - this.getY() -  deslY + Camera.y,mouseX - this.getX() - deslX + Camera.x);
					
				}
				
				dirX = Math.cos(angle);
				dirY = Math.sin(angle);
				
				BulletShoot bullet = new BulletShoot(this.getX() + deslX, this.getY() + deslY, 3, 3, dirX, dirY);
				Game.bullets.add(bullet);
			}
		}
		
		itemsCollision();
		
		Camera.x =  Camera.clamp(this.getX() - Game.WIDTH/2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - Game.HEIGHT/2, 0, World.HEIGHT * 16 - Game.HEIGHT);;
	}
	
	public void itemsCollision() {
		for(int index = 0; index < Game.entities.size(); index++) {
			Entity atual = Game.entities.get(index);
			if(atual instanceof LifePack) {
				if(Entity.isColliding(this,atual)) {
					life += 10;
					if(life >= maxLife) {
						life = maxLife;
					}
					Game.entities.remove(atual);
				}
			}else if(atual instanceof Bullet) {
				if(Entity.isColliding(this,atual)) {
					ammo += 10;
					if(ammo >= maxAmmo) {
						ammo = maxAmmo;
					}
					Game.entities.remove(atual);
				}
			}else if(atual instanceof Weapon) {
				if(Entity.isColliding(this,atual)) {
					hasGun = true;
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	@Override
	public void render(Graphics surface) {
		if(!this.isDamaged) {
			if(lastPos == 1) {
				surface.drawImage(leftPlayer[currAnimation], getX() - Camera.x, getY() - Camera.y, null);
				if(hasGun)
					surface.drawImage(Entity.WEAPON_LEFT_EN, getX() - 9 - Camera.x, getY() + 2 - Camera.y, null);
			}else {
				surface.drawImage(rightPlayer[currAnimation], getX() - Camera.x, getY() - Camera.y, null);
				if(hasGun)
					surface.drawImage(Entity.WEAPON_RIGHT_EN, getX() + 9 - Camera.x, getY() + 2 - Camera.y, null);
			}
		}else {
			surface.drawImage(damagedPlayer, getX() - Camera.x, getY() - Camera.y, null);
		}
		
	}
}
