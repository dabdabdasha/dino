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
	int dino_Vx = 4;
	int dino_Vy = 4;
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
		dino.setX((float) (Math.random() * Gdx.graphics.getWidth() - pie.getWidth()));
		dino.setY((float) (Math.random() * Gdx.graphics.getHeight() - pie.getHeight()));
		CreatePie();

		BitmapFont font = new BitmapFont();
		String text = "" + a;
		Random r = new Random();
		int eNumber = 3;
		enemies = new Enemy[eNumber];

		EnemySpawn(r,eNumber);

		CountLabel(font, text);
	}

	private void CreatePie() {
		pie.setX((float) (Math.random() * Gdx.graphics.getWidth() - pie.getWidth()));
		pie.setY((float) (Math.random() * Gdx.graphics.getHeight() - pie.getHeight()));
	}

	private void CountLabel(BitmapFont font, String text) {
		Label.LabelStyle style = new Label.LabelStyle(font, Color.BROWN);
		label = new Label(text,style);
		label.setFontScale(2);
		label.setPosition(Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight() - 40);
	}

	private void EnemySpawn(Random r,int eNumber) {

		for (int i=0; i<eNumber;i++) {
			enemies[i] = new Enemy(new Texture("enemy.png"));
			enemies[i].enemyVX = r.nextInt(3) - 1;
			enemies[i].enemyVY = r.nextInt(3) - 1;
			enemies[i].setY((float) (Math.random()* Gdx.graphics.getHeight() - enemies[i].getHeight()));
			enemies[i].setX((float) (Math.random()*Gdx.graphics.getWidth() - enemies[i].getWidth()));
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);

		int eNumber = 3;

		Rectangle r1 = pie.getBoundingRectangle();
		Rectangle r2 = dino.getBoundingRectangle();

		GoToFinger();

		EnemyDoIfCollision(r2,eNumber);

		PieDoIfCollision(r1, r2);

		batch.begin();
		label.draw(batch,1);

		for (int i = 0; i < eNumber; i++) {
			enemies[i].draw(batch);
		}
		pie.draw(batch);
		dino.draw(batch);
		batch.end();
	}

	private void PieDoIfCollision(Rectangle r1, Rectangle r2) {
		if ((r2.overlaps(r1)) || (r2.contains(r1))){
			dino_Vy++;
			dino_Vx++;
			pie.setX((float) (Math.random() * Gdx.graphics.getWidth() - pie.getWidth()));
			pie.setY((float) (Math.random() * Gdx.graphics.getHeight() - pie.getHeight()));
			a++;
			label.setText(""+a);
		}
	}

	private void GoToFinger() {
		if (Gdx.input.isTouched()) {
			int x1 = (int) (Gdx.input.getX() - dino.getWidth() / 2);
			int y1 = (int) (Gdx.graphics.getHeight() - Gdx.input.getY() - dino.getHeight() / 2);
			movingByX(x1);
			movingByY(y1);
		}
	}

	private void movingByY(int y1) {
		if (dino.getY()<0){
			dino.setY(0);
		}
		if (dino.getY()> Gdx.graphics.getHeight()){
			dino.setY(Gdx.graphics.getHeight());
		}
		if(dino.getY() < y1){
			dino.translateY(dino_Vy);
		}
		else{
			dino.translateY(-dino_Vy);
		}
	}

	private void movingByX(int x1) {
		if (dino.getX()<0){
			dino.setX(0);
		}
		if (dino.getX()> Gdx.graphics.getWidth() ){
			dino.setX(Gdx.graphics.getWidth() );
		}
		if (dino.getX() < x1){
			dino.translateX(dino_Vx);
		}
		else{
			dino.translateX(-dino_Vx);
		}
	}

	private void EnemyDoIfCollision(Rectangle r2,int eNumber) {

		for (int i=0; i<eNumber;i++) {
		   Rectangle r3 = enemies[i].getBoundingRectangle();

			enemies[i].translateX(enemies[i].enemyVX);
			enemies[i].translateY(enemies[i].enemyVY);
		   if (enemies[i].getX()> Gdx.graphics.getWidth()-enemies[i].getWidth()) {
		   	   enemies[i].enemyVX = enemies[i].enemyVX*(-1);
		   }

		   if (enemies[i].getX()<1) {
			   enemies[i].enemyVX = enemies[i].enemyVX*(-1);
		   }

		   if (enemies[i].getY()>Gdx.graphics.getHeight()-enemies[i].getHeight()) {
			   enemies[i].enemyVY = enemies[i].enemyVY*(-1);
		   }

		   if (enemies[i].getY()<1) {
			   enemies[i].enemyVY = enemies[i].enemyVY*(-1);
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
	}

	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
