package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.math.MathUtils.random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite dino;
	Sprite cake;
	int dinoVX=1;
	int dinoVY=1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		dino = new Sprite(new Texture("dino.png"));
		dino.setX(500);
		dino.setY(500);
		cake = new Sprite(new Texture("apple_pie.png"));
		cake.setScale(3,3);

		cake.setX(random(2000));
		cake.setY(random(1000));

	}

	@Override
	public void render () {
		Rectangle r = dino.getBoundingRectangle();
		Rectangle r2 = cake.getBoundingRectangle();

		int x1=Gdx.input.getX();
		int y1=Gdx.graphics.getHeight() - Gdx.input.getY();
		if (Gdx.input.isTouched()) {

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

		if ((r.overlaps(r2)) || (r.contains(r2))) {
            cake.translateX(x1-random(500));
			cake.translateY(y1+random(500));
		}

		ScreenUtils.clear(6, 6, 6, 1);

		batch.begin();
		cake.draw(batch);
		dino.draw(batch);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
