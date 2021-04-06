package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TechButtons {

	public Stage stage;
	public Texture texture;
	public SpriteBatch batch;
	int t;
	
	public TechButtons(Stage s)
	{
		this.stage = s;
		t = MapScreen.mygame.turn;
	}
	
	
	public void techMenu()
	{

	final ImageButtonStyle moneybutton = UImanager.configbutton(MapScreen.skin, "browncoin");
	//set to gear
	final ImageButtonStyle techbutton = UImanager.configbutton(MapScreen.skin, "browncoin");
	final ImageButtonStyle hammerbutton = UImanager.configbutton(MapScreen.skin, "hammerbutton");
	final ImageButtonStyle buildingbutton = UImanager.configbutton(MapScreen.skin, "browncastle");
	//set to Xbutton
	final ImageButtonStyle closebutton = UImanager.configbutton(MapScreen.skin, "Brownbox");
	final ImageButtonStyle swordbutton = UImanager.configbutton(MapScreen.skin, "brownsword");
	final ImageButtonStyle personbutton = UImanager.configbutton(MapScreen.skin, "PersonButton");
	
	final ImageButton	Tech0button = new ImageButton(moneybutton);
	final ImageButton	Tech1button = new ImageButton(hammerbutton);
	final ImageButton	Tech2button = new ImageButton(techbutton);
	final ImageButton	Tech3button = new ImageButton(buildingbutton);
	final ImageButton	Tech4button = new ImageButton(buildingbutton);
	final ImageButton	Tech5button = new ImageButton(buildingbutton);
	final ImageButton	Tech6button = new ImageButton(techbutton);
	final ImageButton	Tech7button = new ImageButton(moneybutton);
	final ImageButton	Tech8button = new ImageButton(hammerbutton);
	final ImageButton	Tech9button = new ImageButton(personbutton);
	final ImageButton	Tech10button = new ImageButton(hammerbutton);
	final ImageButton	Tech11button = new ImageButton(swordbutton);
	final ImageButton	Tech12button = new ImageButton(personbutton);
	final ImageButton	Tech13button = new ImageButton(swordbutton);
	final ImageButton	Tech14button = new ImageButton(hammerbutton);
	final ImageButton	TechEbutton = new ImageButton(closebutton);
	
	stage.addActor(Tech0button);
	stage.addActor(Tech1button);
	stage.addActor(Tech2button);
	stage.addActor(Tech3button);
	stage.addActor(Tech4button);
	stage.addActor(Tech5button);
	stage.addActor(Tech6button);
	stage.addActor(Tech7button);
	stage.addActor(Tech8button);
	stage.addActor(Tech9button);
	stage.addActor(Tech10button);
	stage.addActor(Tech11button);
	stage.addActor(Tech12button);
	stage.addActor(Tech13button);
	stage.addActor(Tech14button);
	stage.addActor(TechEbutton);
	
	Tech0button.setPosition(64, 256);
	Tech1button.setPosition(128, 320);
	Tech2button.setPosition(128, 192);
	Tech3button.setPosition(192, 256);
	Tech4button.setPosition(256, 320);
	Tech5button.setPosition(256, 192);
	Tech6button.setPosition(320, 256);
	Tech7button.setPosition(384, 320);
	Tech8button.setPosition(384, 192);
	Tech9button.setPosition(64,  80);
	Tech10button.setPosition(128, 80);
	Tech11button.setPosition(192, 144);
	Tech12button.setPosition(192, 16);
	Tech13button.setPosition(256, 80);
	Tech14button.setPosition(320, 80);
	TechEbutton.setPosition(464, 336);
	
	//0
	Tech0button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(0);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[0]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//1
	Tech1button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(1);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[1]);
    	} 
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//2
	Tech2button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(2);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[2]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//3
	Tech3button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(3);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[3]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//4
	Tech4button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(4);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[4]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//5
	Tech5button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(5);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[5]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//6
	Tech6button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(6);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[6]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//7
	Tech7button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(7);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[7]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//8
	Tech8button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(8);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[8]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//9
	Tech9button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(9);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[9]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//10
	Tech10button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(10);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[10]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//11
	Tech11button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(11);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[11]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//12
	Tech12button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(12);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[12]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//13
	Tech13button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(13);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[13]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		    		
	});
	
	//14
	Tech14button.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			MapScreen.mygame.N[0].research(14);
       	}
	    		    		    		
		public void enter(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
			MapScreen.label3.setText("      " + MapScreen.mygame.N[0].Tech_boosts[14]);
    	}
		
    	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    	{
    		MapScreen.label3.setText(" ");
    	}
		
    	
	});
	
	//E
	TechEbutton.addListener(new ClickListener()
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Tech0button.remove();
			Tech1button.remove();
			Tech2button.remove();
			Tech3button.remove();
			Tech4button.remove();
			Tech5button.remove();
			Tech6button.remove();
			Tech7button.remove();
			Tech8button.remove();
			Tech9button.remove();
			Tech10button.remove();
			Tech11button.remove();
			Tech12button.remove();
			Tech13button.remove();
			Tech14button.remove();
			TechEbutton.remove();
			MapScreen.label2.remove();
			MapScreen.label3.remove();
			
       	}
	    		    		    		
		    		
	});
	
	}
	
	
	
	
}