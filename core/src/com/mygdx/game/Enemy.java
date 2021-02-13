package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Enemy extends Sprite {
	int enemyVX = 1;
	int enemyVY = 1;

	public Enemy(Texture texture) {
		super(texture);
	}
}
