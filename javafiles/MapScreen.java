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

public class MapScreen implements Screen
{
	
    Texture img;
    TiledMap tiledMap;
   public static OrthographicCamera camera;
   TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
   enlightenment game;
   protected BitmapFont blocky;
   protected Table mainTable;
   private TextureAtlas atlas;
   protected static Skin skin;
   private Viewport viewport;
   public static Stage stage;
   public static Stage mapstage;
   private InputMultiplexer multiplexer;
   EntityActor entityactor;
   MyActor myactor;
   static MyGame mygame;
   Label label;
   static AI ai;
  // private Texture box;
   TechButtons Tech;
   public Texture texture2;
   public static Label label2;
   public static Label label3;
   
   
   public MapScreen(enlightenment game, MyGame mg)
   {
	   this.mygame = mg;
	   atlas = new TextureAtlas("menusprites.txt");
	   skin = new Skin(atlas);
	   mainTable = new Table(skin);
	   mainTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	   
	   float w = Gdx.graphics.getWidth();
	   float h = Gdx.graphics.getHeight();
	   this.game = game;
	   
	   camera = new OrthographicCamera();
	   camera.setToOrtho(false,w,h);
	   camera.update();
	   
	   OrthographicCamera mapcamera = new OrthographicCamera();
	   
	   tiledMap = new TmxMapLoader().load("testmap.tmx");
	   tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
       texture = new Texture(Gdx.files.internal("orangeswordsman.png"));
       mygame.AddEntity(40, 20, 99, mygame.map.tiles[10][10], "Player 3", texture);
       entityactor = new EntityActor(mygame.entities.get(0), "sword 1");
       //change type later
	   //TODO: add ai castle
       Texture castlegraphic = new Texture(Gdx.files.internal("castle.png"));
       CastleBase castle = new CastleBase(castlegraphic,"base1");
       
       texture2 = new Texture(Gdx.files.internal("TechBackground.png"));
       
       blocky = new BitmapFont(Gdx.files.internal("blocky.fnt"));
       
	   viewport = new ExtendViewport(512, 384, camera);
       
       viewport.apply();
       
       
       Viewport uiview = new ExtendViewport(512,384,mapcamera);

       uiview.apply();
       
       camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
       camera.update();
       
       mapcamera.position.set(mapcamera.viewportWidth / 2, mapcamera.viewportHeight / 2, 0);
       mapcamera.update();
       

       batch = new SpriteBatch();
       //stage currently doesn't work??
       stage = new Stage(uiview, batch);
       mapstage = new Stage(viewport, batch);
       //not tooooo sure about this line, change to be like other stuff if it dont work
       //mapstage = new Stage(new ScreenViewport());
      
       
       
       
       
       
       //money label stuff
       BitmapFont font = new BitmapFont();
       Texture te = new Texture(Gdx.files.internal("Brownbox.png"));
      TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(te));
       
      //money label
       Label.LabelStyle style = new LabelStyle();
       style.background = t; // Set the drawable you want to use
       style.font = font;
       	
       label = new Label("   Tech Points: " + String.valueOf(mygame.N[0].TechPoints) +"                                                        "  , style);
       stage.addActor(label);
       
       //label.setText("   Money: " + String.valueOf(mygame.N[0].money) +"   ");
       
       multiplexer = new InputMultiplexer();
       multiplexer.addProcessor(stage);
       multiplexer.addProcessor(mapstage);
       InputProcessor mapmove = new MapInputHandler();
       multiplexer.addProcessor(mapmove);
       mapstage.addActor(entityactor);
       entityactor.unrestrictedMove(160, 160);
       mapstage.addActor(castle);
       castle.spritePos(128, 32);
       mygame.N[mygame.turn].TileBonus(mygame.map.tiles[(128/16)]  [(32/16)]);
       ai = new AI(game,mygame);
       
       

       
       
       
       
       
       
       
       //Music music = Gdx.audio.newMusic(Gdx.files.internal("loopedmusic.mp3"));
       //music.setLooping(true);
	   //music.play();
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
   }
   
@Override
//called once
public void show() 
{
	//Gdx.input.setInputProcessor(this);
	Gdx.input.setInputProcessor(multiplexer);
	 //Set table to fill stage
    mainTable.setFillParent(true);
    //Set alignment of contents in the table.
    mainTable.right().bottom();
	
    //TODO:Change this graphic to end turn button once the asset is created
    final ImageButtonStyle swbutton = UImanager.configbutton(skin, "brownclock");
    //set to gear
    final ImageButtonStyle tbutton = UImanager.configbutton(skin, "Brownbox");
    
    ImageButton swordbutton = new ImageButton(swbutton);
    ImageButton techbutton = new ImageButton(tbutton);
    
    swordbutton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) 
        {
        	mygame.ChangeTurn();
        	System.out.print("Current turn:  " );
        	System.out.print(mygame.turn);
        	System.out.println();
        	//temporary
        	Array<Actor> stageActors = mapstage.getActors();
        	int i = 0;
        	while(i<stageActors.size)
        	{
        		Actor a = stageActors.get(i);
        		a.toString();
        		Gdx.app.log("actor: ", a.toString());
        		 i=i+1;
        	}
        }
    });
   
    
    
    techbutton.addListener(new ClickListener() 
    {	@Override
    	public void clicked(InputEvent event, float x, float y) 
        {
  
        BitmapFont font = new BitmapFont();
        TextureRegionDrawable t2 = new TextureRegionDrawable(new TextureRegion(texture2));
        Label.LabelStyle style2 = new LabelStyle();
        style2.background = t2; // Set the drawable you want to use
        style2.font = font;
        label2 = new Label("\n " , style2);
        stage.addActor(label2);	
    	
        BitmapFont font3 = new BitmapFont();
        Texture te3 = new Texture(Gdx.files.internal("Brownbox.png"));
        TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(te3));
        Label.LabelStyle style3 = new LabelStyle();
        style3.background = t; // Set the drawable you want to use
        style3.font = font3;
        label3 = new Label("\n                                         \n\n", style3);
        label3.setPosition(353,0);
        stage.addActor(label3);
    	
    	
    	
    	Tech = new TechButtons(stage);
    	Tech.techMenu();
    
        }
    });
    
    
    mainTable.add(techbutton);
    mainTable.add(swordbutton);
    stage.addActor(mainTable);
}

@Override
//called repeatedly, ~60 frames / sec
public void render(float delta) 
{
	
	Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();
    
    //batch.setProjectionMatrix(camera.combined);
    //batch.begin();
    //font.draw(batch,"Money: "+ String.valueOf(mygame.N[0].money),20,20);
    //batch.end();
    
    //awful terrible code done in awful terrible place so that the label updates.
    label.setText("    Money: " + String.valueOf(mygame.N[0].money) + "   Tech Points: " + String.valueOf(mygame.N[0].TechPoints)+ "   Materials: " + String.valueOf(mygame.N[0].materials));
    stage.act();
    mapstage.act();
    mapstage.draw();
    stage.draw();
}
@Override
public void resize(int width, int height) 
{
	viewport.update(width, height);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    camera.update();
	
}
@Override
public void pause() {
	// TODO Auto-generated method stub
	
}
@Override
public void resume() {
	// TODO Auto-generated method stub
	
}
@Override
public void hide() {
	// TODO Auto-generated method stub
	
}
@Override
public void dispose() 
{
	//TODO: make sure EVERYTHING is disposed
	sb.dispose();
	stage.dispose();
	mapstage.dispose();
	skin.dispose();
	atlas.dispose();
}




}
