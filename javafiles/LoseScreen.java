package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoseScreen implements Screen
{
	private SpriteBatch batch;
	protected BitmapFont blocky;
	
	public LoseScreen()
	{
		blocky = new BitmapFont(Gdx.files.internal("blocky.fnt"));
		batch = new SpriteBatch();
		MapScreen.mygame = new MyGame();
		MapScreen.sd.mg = MapScreen.mygame;
		MapScreen.sd.clearN();
		MapScreen.sd.clearunits();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        blocky.draw(batch,"Max Turns Reached, you lose :(",350,384);
        batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}