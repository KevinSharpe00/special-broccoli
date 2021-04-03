package com.mygdx.game;

import java.util.Vector;

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
import com.badlogic.gdx.utils.Array;

public class AIActor extends Actor
{
	Action action;
	Entity entity;
	static Vector<AIActor> AIActors = new Vector<AIActor>();
	AIActor current;
	 
	//maybe inherit and change a bit to do different things on touch???
	  public AIActor(Entity e, final String actorName) 
	  {
		current = this;
		entity = e;
		spriteSet(entity.sprite.getX(), entity.sprite.getY());
		setTouchable(Touchable.disabled);
		
		AIActors.add(current);
		
	  }
	 
	  public void spritePos(float x, float y)
	  {
		  
		  int tx = (int) x/16;
          int ty = (int) y/16;
          Tile T = MapScreen.mygame.map.tiles[tx][ty];
		  if(entity.exhausted == false && entity.MoveCheck(T))
		  {
			  
		  entity.sprite.setPosition(x, y);
		  setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
		  //coords and entity movement
		  
		  entity.Move(T);
		  System.out.println("X: " + tx + " Y: " + ty);
		  }
		  else
		  {
			  System.out.println("no");
		  }
		  MapScreen.mygame.ClearSwords();
		  MapScreen.mygame.SwordCheck(x, y);
	  }
	  
	  public void unrestrictedMove(float x, float y)
	  {
		  entity.sprite.setPosition(x, y);
		  setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
	  }
	  
	  public void spriteSet(float x, float y)
	  {
		setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
	  }
	  
	  public static void deathcheck()
	  {
		  for(int i=0; i<AIActors.size(); i++)
		  {
			  if(AIActors.get(i).entity.commander == "Dead")
			  {
				  AIActors.get(i).remove();
				  AIActors.remove(i);
			  }
		  }
		  
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