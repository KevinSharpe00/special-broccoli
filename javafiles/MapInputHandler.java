package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class MapInputHandler implements InputProcessor
{

	int w = 0;
	int x = 0;
	int y = 0;
	int z = 0;
	static EntityActor mover;
	@Override
	public boolean keyDown(int keycode) 
	{	
		if((keycode == Input.Keys.LEFT) & (w < 8))
		{
			//object.w++;
			w++;
			x--;
	        MapScreen.camera.translate(-32,0);
		}
	    if((keycode == Input.Keys.RIGHT) & (x < 12))
	    {
	    	x++;
	    	w--;
	        MapScreen.camera.translate(32,0);
	    }
	    if((keycode == Input.Keys.UP) & (y < 11))
	    {
	    	y++;
	    	z--;
	    
	        MapScreen.camera.translate(0,32);
	    }
	    if((keycode == Input.Keys.DOWN) & (z < 8))
	    {
	    	z++;
	    	y--;
	        MapScreen.camera.translate(0,-32);
	    }
	    //if(keycode == Input.Keys.NUM_1)
	      //  tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		//BAD CODE, but it moves it. Consdering we have spent LITERAL HOURS
		//on trying to move stuff, I'm calling it good.
		try
		{//Gdx.graphics.getWidth(); Gdx.graphics.getHeight();
		int X = (screenX/32);
		X = X*32;
		int Y = (screenY/32);
		Y = Y*32+32;	
	    Vector3 clickCoordinates = new Vector3(X,Y,0);
	    Vector3 position = MapScreen.camera.unproject(clickCoordinates);
	    mover.spritePos(position.x, position.y);
	    mover = null;
	    return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) 
	{
		return false;
	}

}
