package com.platstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.platstudios.entities.*;

import firstGameJava.Game;

public class World {
	
	public static int WIDTH;
	public static int HEIGHT;
	private Tile tiles[];
	
	public World(String path) {
		
		try {
			
			BufferedImage mapSprite = ImageIO.read(getClass().getResource(path));
			WIDTH = mapSprite.getWidth();
			HEIGHT = mapSprite.getHeight();
			
			int pixels[] = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			mapSprite.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			int tileAtual = 0;
			
			for(int x_index = 0; x_index < WIDTH; x_index++) {
				for(int y_index = 0; y_index < HEIGHT; y_index++) {
					
					tileAtual = pixels[x_index + y_index * WIDTH];
					tiles[x_index + y_index * WIDTH] = new FloorTile(x_index * 16, y_index * 16);
					
					switch(tileAtual) {
						case 0xFFFFFFFF:
							tiles[x_index + y_index * WIDTH] = new WallTile(x_index * 16, y_index * 16);
							break;
						case 0xFF0026FF:
							Game.entities.get(0).setX(x_index * 16);
							Game.entities.get(0).setY(y_index * 16);
							break;
						case 0xFF404040:
							Game.entities.add(new Weapon(x_index * 16, y_index * 16, 16, 16));
							break;
						case 0xFFFFD800:
							Game.entities.add(new Bullet(x_index * 16, y_index * 16, 16, 16));
							break;
						case 0xFF00FF21:
							Game.entities.add(new LifePack(x_index * 16, y_index * 16, 16, 16));
							break;
						case 0xFFFF0000:
							Game.entities.add(new Enemy(x_index * 16, y_index * 16, 16, 16));
							break;
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void render(Graphics g) {
		Tile tile;
		int xStart = (Camera.x >> 4);
		int yStart = (Camera.y >> 4);
		
		int xEnd = xStart + (Game.WIDTH >> 4);
		int yEnd = yStart + (Game.HEIGHT >> 4);
		
		for(int x_index = xStart; x_index <= xEnd; x_index++) {
			for(int y_index = yStart; y_index <= yEnd; y_index++) {
				if(x_index < 0 || y_index < 0 || x_index >= WIDTH || y_index >= HEIGHT) {
					continue;
				}
				tile = tiles[x_index + y_index * WIDTH];
				tile.render(g);
			}
		}
	}
}
