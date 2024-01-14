package com.platstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	private int WIDTH;
	private int HEIGHT;
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
					
					if(tileAtual == 0xFFFFFFFF) {
						tiles[x_index + y_index * WIDTH] = new WallTile(x_index * 16, y_index * 16);
					}else if(tileAtual == 0xFF000000) {
						tiles[x_index + y_index * WIDTH] = new FloorTile(x_index * 16, y_index * 16);
					}else if(tileAtual == 0xFF0026FF) {
						tiles[x_index + y_index * WIDTH] = new FloorTile(x_index * 16, y_index * 16);
					}else {
						tiles[x_index + y_index * WIDTH] = new FloorTile(x_index * 16, y_index * 16);
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void render(Graphics g) {
		Tile tile;
		for(int x_index = 0; x_index < WIDTH; x_index++) {
			for(int y_index = 0; y_index < HEIGHT; y_index++) {
				tile = tiles[x_index + y_index * WIDTH];
				tile.render(g);
			}
		}
	}
}
