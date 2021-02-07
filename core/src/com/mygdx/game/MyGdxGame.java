package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite dino;
	int dinoVX=1;
	int dinoVY=1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		dino = new Sprite(new Texture("dino.png"));
		dino.setX(0);
		dino.setY(0);

	}

	@Override
	public void render () {
		if (Gdx.input.isTouched()) {
			int x1=Gdx.input.getX();
			int y1=Gdx.graphics.getHeight() - Gdx.input.getY();
			if (dino.getX()+dino.getWidth()/2<x1) {
				dino.translateX(dinoVX);
			}
			if (dino.getX()+dino.getWidth()/2>x1) {
				dino.translateX(-dinoVX);
			}
			if (dino.getY()+dino.getHeight()/2<y1) {
				dino.translateY(dinoVY);
			}
			if (dino.getY()+dino.getHeight()/2>y1) {
				dino.translateY(-dinoVY);
			}
		}
		ScreenUtils.clear(5, 2, 9, 1);

		batch.begin();
		dino.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
