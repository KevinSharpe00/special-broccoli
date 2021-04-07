package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class EntityActor extends Actor
{
	Action action;
	Entity entity;
	static Array<EntityActor> entityActors = new Array<EntityActor>();
	EntityActor current;
	 
	  public EntityActor(Entity e, final String actorName) 
	  {
		current = this;
		entity = e;
		spriteSet(entity.sprite.getX(), entity.sprite.getY());
		setTouchable(Touchable.enabled);
		entityActors.add(current);
		
		addListener(new InputListener() 
		{
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		  {
			 

				  Gdx.app.log("Touch down asset with name ", actorName);
				  MapInputHandler.mover = current;
					return true;
			 
		  }  
		  
		  @Override
		  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				MapScreen.unitinfolabel.setText("\n    HP: " + String.valueOf(entity.health) + "\n    DMG: " + String.valueOf(entity.damage) + "\n    MOV: " + String.valueOf(entity.range));
				
			}
		  
		  
		  @Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
			{
			  MapScreen.unitinfolabel.setText(" ");
			}
		}
		);//end of listener
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
		  
		  MapScreen.mygame.ClearSwords();
		  MapScreen.mygame.SwordCheck(x, y);
	  }
	  
	  public void spriteSet(float x, float y)
	  {
		setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
	  }
	  
	  public static void deathcheck()
	  {
		  for(int i=0; i<entityActors.size; i++)
		  {
			  if(entityActors.get(i).entity.commander == "Dead")
			  {
				  entityActors.get(i).remove();
				  entityActors.removeIndex(i);
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
