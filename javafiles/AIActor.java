package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class AIActor extends Actor
{
	Action action;
	Entity entity;
	static Vector<AIActor> AIActors = new Vector<AIActor>();
	AIActor current;
	int aitype;
	 
	  public AIActor(Entity e, final String actorName) 
	  {
		current = this;
		entity = e;
		spriteSet(entity.sprite.getX(), entity.sprite.getY());
		//setTouchable(Touchable.disabled); commented out so that we can mouse over and get unit stats
		AIActors.add(current);
		
		addListener(new InputListener() 
		{
			
			@Override
			  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
				{
					//get unit stats on mouse over
					MapScreen.unitinfolabel.setText("\n    HP: " + String.valueOf(entity.health) + "\n    DMG: " + String.valueOf(entity.damage) + "\n    MOV: " + String.valueOf(entity.range));
					
				}
			  
			  
			  @Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
				{
				  //reset label on mouse off
				  MapScreen.unitinfolabel.setText(" ");
				}
		});
		
		
	  }
	 
	  public void spritePos(float x, float y)
	  {
		  
		  int tx = (int) x/16;
          int ty = (int) y/16;
          Tile T = MapScreen.mygame.map.tiles[tx][ty];
		  if(entity.exhausted == false && entity.MoveCheck(T))
		  {
			  //move visual representation
			  entity.sprite.setPosition(x, y);
			  setBounds(entity.sprite.getX(), entity.sprite.getY(), entity.sprite.getWidth(), entity.sprite.getHeight());
			  
			  //move underlying representation
			  entity.Move(T);
			  System.out.println("X: " + tx + " Y: " + ty);
		  }
		  //TODO: should both of these be combined into one fxn?
		  //Spawns and gets rid of swords (to make sprite overlap not a big problem)
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