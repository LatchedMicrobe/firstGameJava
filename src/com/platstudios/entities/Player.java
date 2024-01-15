package com.platstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.platstudios.world.Camera;
import com.platstudios.world.World;

import firstGameJava.Game;

public class Player extends Entity{
	
	public boolean moved,up, down, left, right;
	public int speed = 2;
	
	private int lastPos = 0;
	private int frames = 0, maxFrames = 5, currAnimation = 0, maxAnimation = 4;
	private BufferedImage rightPlayer[];
	private BufferedImage leftPlayer[];
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x,y,width,height,sprite);
		rightPlayer = new BufferedImage[5];
		leftPlayer  = new BufferedImage[5];
		
		for(int index = 0; index < 5; index++) {
			rightPlayer[index] = sprite.getSubimage(index * 16, 0, width, height);
			leftPlayer[index] = sprite.getSubimage(index * 16, 16, width, height);
		}
	}
	
	@Override
	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			this.setX(this.getX() + speed);
			lastPos = 0;
		}else if(left) {
			moved = true;
			this.setX(this.getX() - speed);
			lastPos = 1;
		}
		if(up) {
			moved = true;
			this.setY(this.getY() - speed);
		}else if(down) {
			moved = true;
			this.setY(this.getY() + speed);

		}
		
		if(moved) {
			frames++;
			if(frames > maxFrames) {
				frames = 0;
				currAnimation++;
				
				if(currAnimation >= maxAnimation) {
					currAnimation = 0;
				}
			}
		}
		Camera.x =  Camera.clamp(this.getX() - Game.WIDTH/2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - Game.HEIGHT/2, 0, World.HEIGHT * 16 - Game.HEIGHT);;
	}
	
	@Override
	public void render(Graphics surface) {
		if(lastPos == 1)
			surface.drawImage(leftPlayer[currAnimation], getX() - Camera.x, getY() - Camera.y, null);
		else
			surface.drawImage(rightPlayer[currAnimation], getX() - Camera.x, getY() - Camera.y, null);
			
			
	}
}
