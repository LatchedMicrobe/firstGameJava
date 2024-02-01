package com.platstudios.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.platstudios.entities.Player;

import firstGameJava.Game;

public class Ui {
	public void render(Graphics surface) {
		
		surface.setColor(Color.red);
		surface.fillRect(8, 4, 50, 8);
		
		surface.setColor(Color.green);
		surface.fillRect(8, 4, (int)((Game.player.life / Game.player.maxLife) * 50), 8);
		
		surface.setColor(Color.white);
		surface.setFont(new Font("arial",Font.BOLD,8));
		surface.drawString((int) Game.player.life + "/" + (int) Game.player.life, 20, 11);
	}
}
