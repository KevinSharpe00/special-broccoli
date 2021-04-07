package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

public class CastleBase extends MyActor 
{
	
	static Array<CastleBase> castlebases = new Array<CastleBase>();
	float X;
	CastleBase current;
	float Y;
	Entity ent;
	public CastleBase(Entity e, final String actorName) 
	{
		super(e.sprite.getTexture(), actorName);
		TextureAtlas atlas = new TextureAtlas("menusprites.txt");
		Skin skin = new Skin(atlas);
		current = this;
		ent = e;
		castlebases.add(current);
 
		final ImageButtonStyle personbutton = UImanager.configbutton(skin, "PersonButton");
		final ImageButtonStyle swordbutton = UImanager.configbutton(skin, "brownsword");
		final ImageButtonStyle hammerbutton = UImanager.configbutton(skin, "hammerbutton");
		final ImageButtonStyle marketbutton = UImanager.configbutton(skin, "browncoin");
		final ImageButtonStyle castlebutton = UImanager.configbutton(skin, "browncastle");
		
		
		//TODO top level castle clicking
		addListener(new InputListener() {
			
			
			 @Override
			  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
				{
					//Gdx.app.log("HP:", String.valueOf(entity.health));
					MapScreen.unitinfolabel.setText("\n    HP: " + String.valueOf(ent.health) + "\n    DMG: " + String.valueOf(ent.damage) + "\n    MOV: " + String.valueOf(ent.range));
					
				}
			  
			  
			  @Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
				{
				  MapScreen.unitinfolabel.setText(" ");
				}
			
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		  {
			  ////////////////////////////////////////////////////////////////////////////////////////////////
			  //touch castle
			  final ImageButton pbutton = new ImageButton(personbutton);
			  final ImageButton hbutton = new ImageButton(hammerbutton);
			 
			  
			  //TODO Unit Creation
			  MapScreen.mapstage.addActor(pbutton);
			  //player castle is always at set position, so this is done.
			    pbutton.setPosition(X+16,Y+48);
	            pbutton.addListener(new ClickListener(){
	            	
	                @Override
	                public void clicked(InputEvent event, float x, float y) 
	                {
	                	pbutton.remove();
	                	hbutton.remove();

	                	final ImageButton sbutton = new ImageButton(swordbutton);
	                	final ImageButton settlerbutton = new ImageButton(castlebutton);
	                	
	                	MapScreen.mapstage.addActor(sbutton);
	    	            sbutton.setPosition(X+16,Y+48);
	    	            sbutton.addListener(new ClickListener(){
	    	                @Override
	    	                public void clicked(InputEvent event, float x, float y) 
	    	                {
	    	                	sbutton.remove();
	    	                	settlerbutton.remove();
	    	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].money >= 5)
                                {
                                Texture tex = new Texture(Gdx.files.internal("redswordman.png"));

                                //MapScreen.mygame.AddEntity(MapScreen.mygame.,5,3,MapScreen.mygame.map.tiles[(int) (X/16-1)][(int) (Y/16)],"Player 1",tex);
                                MapScreen.mygame.AddEntity(MapScreen.mygame.N[0].unitHP,MapScreen.mygame.N[0].swordDMG,MapScreen.mygame.N[0].unitmove,MapScreen.mygame.map.tiles[(int) (X/16-1)][(int) (Y/16)],"Player 1",tex);//diff values to account for nation modifier
                                EntityActor ea = new EntityActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "redswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));//MapScreen.mygame.entity_counter
                                MapScreen.mapstage.addActor(ea);
                                ea.unrestrictedMove(X-16,Y);
                                ea.setName("redsword"+String.valueOf(MapScreen.mygame.entity_counter-1));
                                //takes away money and adds to unit upkeep
                                MapScreen.mygame.N[MapScreen.mygame.turn].money = MapScreen.mygame.N[MapScreen.mygame.turn].money -10;
                                MapScreen.mygame.N[MapScreen.mygame.turn].addUnitUpkeep(1);
                                
                                }
                                //if not, prints out that they dont
                                else
                                {
                                    System.out.println("not enough money");
                                }
	    	                	
	    	                	
	    	                }
	    	            });
	                	
	    	            MapScreen.mapstage.addActor(settlerbutton);
	    	            settlerbutton.setPosition(X+16,Y);
	    	            settlerbutton.addListener(new ClickListener(){
	    	                @Override
	    	                public void clicked(InputEvent event, float x, float y) 
	    	                {
	    	                	sbutton.remove();
	    	                	settlerbutton.remove();
	    	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].money >= 10)
                                {
                                Texture tex = new Texture(Gdx.files.internal("settler.png"));
                                MapScreen.mygame.AddEntity(10,1,3,MapScreen.mygame.map.tiles[(int) (X/16-1)][(int) (Y/16)],"Player 1",tex);
                                SettlerActor sa = new SettlerActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "redswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));//MapScreen.mygame.entity_counter
                                MapScreen.mapstage.addActor(sa);
                                sa.unrestrictedMove(X-16,Y);
                                sa.setName("redsword"+String.valueOf(MapScreen.mygame.entity_counter-1));
                                //takes away money and adds to unit upkeep
                                MapScreen.mygame.N[MapScreen.mygame.turn].money = MapScreen.mygame.N[MapScreen.mygame.turn].money -10;
                                
                                }
                                //if not, prints out that they dont
                                else
                                {
                                    System.out.println("not enough money");
                                }
	    	                	
	    	                	
	    	                }
	    	            });
	                	
	                }
	            });
	            
	            
	            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	            //TODO Building 
	            MapScreen.mapstage.addActor(hbutton);
	            hbutton.setPosition(X+16,Y);
	            hbutton.addListener(new ClickListener(){ 
	                @Override
	                public void clicked(InputEvent event, float x, float y) 
	                {
	                	pbutton.remove();
	                	hbutton.remove();
	                	
	                	final ImageButton mbutton = new ImageButton(marketbutton);
	                	
	                	
	                	final ImageButton bbutton = new ImageButton(hammerbutton);
	      			  	
	                	
	                	
	                	
	                	MapScreen.mapstage.addActor(bbutton);
	    	            bbutton.setPosition(X+16,Y+48);
	    	            bbutton.addListener(new ClickListener(){
	    	                @Override
	    	                public void clicked(InputEvent event, float x, float y) 
	    	                {
	    	                	bbutton.remove();
	    	                	mbutton.remove();
	    	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].money >= 20)
	    	                	{
	    	                		MapScreen.mygame.N[MapScreen.mygame.turn].Build("mine");
	    	                		Texture t = new Texture("mine.png");
	    	                		MapScreen.mygame.AddEntity(15, 0, 0, MapScreen.mygame.map.tiles[(int) (X/16 )][(int) (Y/16 + 1)], actorName, t, "mine");
									BuildingActor ma = new BuildingActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter - 1), "mine");
									MapScreen.mapstage.addActor(ma);
									ma.unrestrictedMove(X, Y+16);
	    	                	   
	    	                	
	    	                	
	    	                	}
	    	                }
	    	            });
	    	            
	    	            MapScreen.mapstage.addActor(mbutton);
	    	            mbutton.setPosition(X+16,Y);
	    	            mbutton.addListener(new ClickListener(){
	    	                @Override
	    	                public void clicked(InputEvent event, float x, float y) 
	    	                {
	    	                	bbutton.remove();
	    	                	mbutton.remove();
	    	                	//bbutton.remove();
	    	                	    	                	
	    	                	
	    	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].materials >= 20)
	    	                	{
	    	                		MapScreen.mygame.N[MapScreen.mygame.turn].Build("market");
	    	                		//visual market
	    	                		
	    	                		Texture t = new Texture("market.png");
	    	                		MapScreen.mygame.AddEntity(15, 0, 0, MapScreen.mygame.map.tiles[(int) (X/16 + 1)][(int) (Y/16)], actorName, t, "market");
									BuildingActor ma = new BuildingActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter - 1), "market");
									MapScreen.mapstage.addActor(ma);
									ma.unrestrictedMove(X+ 16, Y);
	    	                	}
	    	                	else
	    	                	{
	    	                		System.out.println("not enough materials");
	    	                	}
	    	               
	    	                }
	    	            });
	                	
	                }
	            });
	                
	            
			return true;
		  }
		});
	}
	
	public static void deathcheck()
	  {
		  for(int i=0; i<castlebases.size; i++)
		  {
			  if(castlebases.get(i).ent.health <= 0)//.ent.commander == "Dead")
			  {
				  castlebases.get(i).remove();
				  castlebases.removeIndex(i);
			  }
		  }
		  
		  
		  
	  }
	
	public void spritePos(float x, float y){
		sprite.setPosition(x, y);
		//entitiy.move(x,y);
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		X = sprite.getX();
		Y = sprite.getY();
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