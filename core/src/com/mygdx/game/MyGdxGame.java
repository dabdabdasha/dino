package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	Sprite pie;

	Sprite dino;
	Sprite enemy;
	Enemy[] enemies;
	int dino_Vx = 1;
	int dino_Vy = 1;
	int enemy_Vx = 1;
	int enemy_Vy = 1;
	Label label;
	int a = 0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		dino = new Sprite(new Texture("dino.png"));
		pie = new Sprite(new Texture("apple_pie.png"));
		pie.setSize(100,100);
		//enemy = new Sprite(new Texture("enemy.png"));
		pie.setX((float) (Math.random()*Gdx.graphics.getWidth() - pie.getWidth()));
		pie.setY((float) (Math.random()*Gdx.graphics.getHeight() - pie.getHeight()));

		BitmapFont font = new BitmapFont();
		String text = "" + a;
		Random r = new Random();
		enemies = new Enemy[6];
		for (int i=0; i<6;i++) {
			enemies[i] = new Enemy(new Texture("enemy.png"));
			enemies[i].enemyVX = r.nextInt(3) - 1;
			enemies[i].enemyVY = r.nextInt(3) - 1;
			enemies[i].setY((float) (Math.random()*Gdx.graphics.getHeight() - enemies[i].getHeight()));
			enemies[i].setX((float) (Math.random()*Gdx.graphics.getWidth() - enemies[i].getWidth()));
		}

		Label.LabelStyle style = new Label.LabelStyle(font, Color.BROWN);
		label = new Label(text,style);
		label.setFontScale(2);
		label.setPosition(Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight() - 40);
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);

		Rectangle r1 = pie.getBoundingRectangle();
		Rectangle r2 = dino.getBoundingRectangle();

		for (int i=0; i<6;i++) {
		   Rectangle r3 = enemies[i].getBoundingRectangle();

			enemies[i].translateX(enemy_Vx);
			enemies[i].translateY(enemy_Vy);
		   if (enemies[i].getX()>Gdx.graphics.getWidth()-1) {
			   enemy_Vx = enemy_Vx*(-1);
		   }

		   if (enemies[i].getX()<1) {
		   	   enemy_Vx = enemy_Vx*(-1);
		   }

		   if (enemies[i].getY()>Gdx.graphics.getHeight()-1) {
			   enemy_Vy = enemy_Vy*(-1);
		   }

		   if (enemies[i].getY()<1) {
			   enemy_Vy = enemy_Vy*(-1);
		   }

		   if (r2.overlaps(r3)) {
				a = 0;
				label.setText("" + a);
				dino.setX(1);
				dino.setY(1);
				enemies[i].setY((float) (Math.random()*Gdx.graphics.getHeight() - enemies[i].getWidth()));
				enemies[i].setX((float) (Math.random()*Gdx.graphics.getWidth() - enemies[i].getHeight()));
				dino_Vy=1;
				dino_Vx=1;
		   }
		}

		//Если тыкать по устройству
		if (Gdx.input.isTouched()) {
			int x1 = (int) (Gdx.input.getX() - dino.getWidth() / 2);
			int y1 = (int) (Gdx.graphics.getHeight() - Gdx.input.getY() - dino.getHeight() / 2);
			if (dino.getX() < x1){
				dino.translateX(dino_Vx);
			}
			else{
				dino.translateX(-dino_Vx);
			}
			if(dino.getY() < y1){
				dino.translateY(dino_Vy);
			}
			else{
				dino.translateY(-dino_Vy);
			}

		}



		if ((r2.overlaps(r1)) || (r2.contains(r1))){
			dino_Vy++;
			dino_Vx++;
			pie.setX((float) (Math.random() * Gdx.graphics.getWidth() - pie.getWidth()));
			pie.setY((float) (Math.random() * Gdx.graphics.getHeight() - pie.getHeight()));
			a++;
			label.setText(""+a);
		}

		batch.begin();
		label.draw(batch,1);

		for (int i = 0; i < 6; i++) {
			enemies[i].draw(batch);
		}
		pie.draw(batch);
		dino.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
