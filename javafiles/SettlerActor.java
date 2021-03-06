package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

public class SettlerActor extends EntityActor
{
	Action action;
	Entity entity;
	static Array<EntityActor> entityActors = new Array<EntityActor>();
	SettlerActor current;	
	ImageButton bbutton;
	ImageButton movebutton;

	
	//button stuff
	TextureAtlas atlas = new TextureAtlas("menusprites.txt");
	 Skin skin = new Skin(atlas);
	 final ImageButtonStyle castlebutton = UImanager.configbutton(skin, "browncastle");
	 final ImageButtonStyle mvbutton = UImanager.configbutton(skin, "Brownbox");
	 
	  public SettlerActor(Entity e, final String actorName) 
	  {
		 super(e, actorName);
		 current = this;
		 entity = e;
		 spriteSet(entity.sprite.getX(), entity.sprite.getY());
		 setTouchable(Touchable.enabled);
		 entityActors.add(current);
		 
		 
		
		//specify right click to add castle
		 addListener(new ClickListener(Buttons.RIGHT) 
			{
			  @Override
			  public void clicked(InputEvent event, float x, float y)
			  {
					 
				  final ImageButton bbutton = new ImageButton(castlebutton);
				  MapScreen.mapstage.addActor(bbutton);
				  final ImageButton movebutton = new ImageButton(mvbutton);
				  MapScreen.mapstage.addActor(movebutton);
				  		  
				  System.out.println("touched");
	  	          bbutton.setPosition(entity.sprite.getX()+16,entity.sprite.getY()+48);
	  	          bbutton.addListener(new ClickListener(){
	  	          @Override
	  	          public void clicked(InputEvent event, float x, float y) 
	  	          {
	  	        	  bbutton.remove();
	  	        	  movebutton.remove();
	  	                
	  	                if(MapScreen.mygame.N[MapScreen.mygame.turn].materials >= 0)
	  	                {
	  	                	MapScreen.mygame.N[MapScreen.mygame.turn].Build("castle");
	  	                		//TODO: ADD CASTLEHP PARAMETER
	  	                	Texture castlegraphic = new Texture(Gdx.files.internal("castle.png"));
	  	                	MapScreen.mygame.AddEntity(40,  0,  0, MapScreen.mygame.map.tiles[entity.position.ipos][entity.position.jpos], "Player 1", castlegraphic);
	  	                	CastleBase castle2 = new CastleBase(MapScreen.mygame.entities.get(MapScreen.mygame.entities.size() -1),"base1");
	  	                	MapScreen.mapstage.addActor(castle2);
	  	                	castle2.spritePos(entity.sprite.getX(), entity.sprite.getY());
	  	                	
	  	                	current.remove();
	  	                	entity.health = 0;
	  	                	
	  	                	}
	  	                }
	  	            });
	  	          movebutton.setPosition(entity.sprite.getX()+16,entity.sprite.getY());
		          movebutton.addListener(new ClickListener(){
		          @Override
		          public void clicked(InputEvent event, float x, float y) 
		          {
		        	  
		        	  bbutton.remove();
		              movebutton.remove();
		              Gdx.app.log("Touch down asset with name ", actorName);
		              MapInputHandler.mover = current;
		          }
		          });
				 }
			  	
			}
			);//end of listener	
		
		//specify left click to move
		 addListener(new ClickListener(Buttons.LEFT) 
			{
			  @Override
			  public void clicked(InputEvent event, float x, float y)
			  {
				 

					  Gdx.app.log("Touch down asset with name ", actorName);
					  MapInputHandler.mover = current;

				 
			  }  
			}
			);//end of listener
		  	
		
		
	
	  }
	  
	  @Override
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
		  
		  bbutton.remove();
          movebutton.remove();
          
          MapScreen.mygame.ClearSwords();
    	  MapScreen.mygame.SwordCheck(x, y);
	  }
	  
	  
}
