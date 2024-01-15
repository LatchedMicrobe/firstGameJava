package com.platstudios.world;

public class Camera {
	
	public static int x;
	public static int y;
	
	public static int clamp(int atual, int min, int max) {
		if(atual < min) {
			atual = min;
		}else if(atual > max) {
			atual = max;
		}
		return atual;
	}
}
