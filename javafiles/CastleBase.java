package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

public class CastleBase extends MyActor 
{
	float X;
	float Y;
	public CastleBase(Texture texture, final String actorName) 
	{
		super(texture, actorName);
		TextureAtlas atlas = new TextureAtlas("menusprites.txt");
		Skin skin = new Skin(atlas);
		
		//ImageButtonStyle personbutton = new ImageButtonStyle();
		//personbutton.up = skin.getDrawable("PersonButton");
		//personbutton.over = skin.getDrawable("PersonButton");
		//personbutton.down = skin.getDrawable("PersonButton");
		//personbutton.pressedOffsetX = 1;
		//personbutton.pressedOffsetY = -1; 
		final ImageButtonStyle personbutton = UImanager.configbutton(skin, "PersonButton");
		final ImageButtonStyle swordbutton = UImanager.configbutton(skin, "brownsword");
		final ImageButtonStyle hammerbutton = UImanager.configbutton(skin, "hammerbutton");
		addListener(new InputListener() {
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		  {
			  
			  final ImageButton pbutton = new ImageButton(personbutton);
			  final ImageButton hbutton = new ImageButton(hammerbutton);
			  final ImageButton bbutton = new ImageButton(hammerbutton);
			  
			  MapScreen.mapstage.addActor(pbutton);
			  //player castle is always at set position, so this is done.
			    pbutton.setPosition(X+16,Y+48);
	            pbutton.addListener(new ClickListener(){
	                @Override
	                public void clicked(InputEvent event, float x, float y) 
	                {
	                	pbutton.remove();
	                	hbutton.remove();
	                	bbutton.remove();
	                	
	                	final ImageButton sbutton = new ImageButton(swordbutton);
	                	
	                	
	                	MapScreen.mapstage.addActor(sbutton);
	    	            sbutton.setPosition(X+16,Y+48);
	    	            sbutton.addListener(new ClickListener(){
	    	                @Override
	    	                public void clicked(InputEvent event, float x, float y) 
	    	                {
	    	                	sbutton.remove();
	    	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].money >= 5)
                                {
                                Texture tex = new Texture(Gdx.files.internal("redswordman.png"));
                                //MapScreen.mygame.AddSwordsman(MapScreen.mygame.map.tiles[1][1],"Player 1");
                                MapScreen.mygame.AddEntity(20,5,3,MapScreen.mygame.map.tiles[(int) (X/16-1)][(int) (Y/16)],"Player 1",tex);
                                //Gdx.app.log("entitycounter is:", String.valueOf(MapScreen.mygame.entity_counter));       [MapScreen.mygame.entity_counter-1]
                                EntityActor ea = new EntityActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "redswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));//MapScreen.mygame.entity_counter
                                MapScreen.mapstage.addActor(ea);
                                ea.unrestrictedMove(X-16,Y);
                                ea.setName("redsword"+String.valueOf(MapScreen.mygame.entity_counter-1));
                                //takes away money and adds to unit upkeep
                                MapScreen.mygame.N[MapScreen.mygame.turn].money = MapScreen.mygame.N[MapScreen.mygame.turn].money -5;
                                MapScreen.mygame.N[MapScreen.mygame.turn].addUnitUpkeep(1);
                                
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
			  
	            MapScreen.mapstage.addActor(hbutton);
	            hbutton.setPosition(X+16,Y);
	            hbutton.addListener(new ClickListener(){
	                @Override
	                public void clicked(InputEvent event, float x, float y) 
	                {
	                	pbutton.remove();
	                	hbutton.remove();
	                	bbutton.remove();
	                	/*
	                	if(MapScreen.mygame.N[MapScreen.mygame.turn].materials >= 20)
	                	{
	                		MapScreen.mygame.N[MapScreen.mygame.turn].Build("market");
	                		//visual market
	                		/*
	                		Texture t = new Texture("market.png");
	                		Sprite m = new Sprite(t);
	                		MapScreen.batch.begin();
	                		m.draw(MapScreen.batch);
	                		m.setPosition(152, 32);
	                		MapScreen.batch.end();
	                		
	                		
	                		
	                				
	                	}
	                	else
	                	{
	                		System.out.println("not enough materials");
	                	} */
	                }
	            });
	            
	            MapScreen.mapstage.addActor(bbutton);
	            bbutton.setPosition(X+80,Y);
	            //bbutton.setPosition(208, 32);
	            bbutton.addListener(new ClickListener(){
	                @Override
	                public void clicked(InputEvent event, float x, float y) 
	                {
	                	pbutton.remove();
	                	hbutton.remove();
	                	bbutton.remove();
	                	//if(MapScreen.mygame.N[MapScreen.mygame.turn].materials >= 0)
	                	//{
	                		MapScreen.mygame.N[MapScreen.mygame.turn].Build("castle");
	                		//visual market
	                		/*
	                		Texture t = new Texture("market.png");
	                		Sprite m = new Sprite(t);
	                		MapScreen.batch.begin();
	                		m.draw(MapScreen.batch);
	                		m.setPosition(152, 32);
	                		MapScreen.batch.end();
	                		*/
	                		Texture castlegraphic = new Texture(Gdx.files.internal("castle.png"));
	                	    CastleBase castle2 = new CastleBase(castlegraphic,"base1");
	                	    MapScreen.mapstage.addActor(castle2);
	                	    castle2.spritePos(320, 320);
	                		
	                		
	                				
	                	//}
	                	//else
	                	//{
	                	//	System.out.println("not enough materials");
	                	//}
	                }
	            });
	            
			return true;
		  }
		});
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