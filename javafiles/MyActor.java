package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class MyActor extends Actor
{
	Sprite sprite;
	 MyActor current;
	//maybe inherit and change a bit to do different things on touch???
	  public MyActor(Texture texture, final String actorName) 
	  {
		  current = this;
		sprite = new Sprite(texture);
	 
		spritePos(sprite.getX(), sprite.getY());
		/*setTouchable(Touchable.enabled);
		
		
		addListener(new InputListener() {
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			Gdx.app.log("Touch down asset with name ", actorName);
			//MapInputHandler.mover = current;
			//MapScreen.movestate();
			return true;
		  }
		});*/
			
	  }
	 
	  public void spritePos(float x, float y){
		sprite.setPosition(x, y);
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	  }
	 
	  @Override
	  public void act(float delta) {
		super.act(delta);
	  }
	 
	  @Override
	  public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
	  }
		
	
}
