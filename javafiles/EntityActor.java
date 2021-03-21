package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class EntityActor extends Actor
{
	Action action;
	Entity entity;
	boolean touched;
	 
	//maybe inherit and change a bit to do different things on touch???
	  public EntityActor(Entity e, final String actorName) 
	  {
		
		entity = e;
	 
		spriteSet(entity.sprite.getX(), entity.sprite.getY());
		setTouchable(Touchable.enabled);
	 
		addListener(new InputListener() 
		{
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		  {
			  if(entity.istouched = false)
			  {
				  entity.Touch();
				  Gdx.app.log("Touch down asset with name ", actorName);
				  return true;
			  }
			  else
			  {
				  entity.UnTouch();
				  spriteMove(x, y);
				  return true;
			  }
		  }  
		}
		);//end of listener
	  }
	 
	  public void spriteMove(float x, float y)
	  {
		  entity.sprite.setPosition(x, y);
		  addAction(Actions.moveTo(x, y));
		setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
	  }
	  
	  public void spriteSet(float x, float y)
	  {
		setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
	  }
	  
	 
	  @Override
	  public void act(float delta) {
		super.act(delta);
	  }
	 
	  @Override
	  public void draw(Batch batch, float parentAlpha) {
		  entity.sprite.draw(batch);
	  }
	
	
	
	
	
}


