package com.mygdx.game;


import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
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
   protected Skin skin;
   private Viewport viewport;
   public static Stage stage;
   public static Stage mapstage;
   private InputMultiplexer multiplexer;
   EntityActor entityactor;
   MyActor myactor;
   static MyGame mygame;
   Label label;
   static AI ai;
   static Label unitinfolabel;
   static Label TechInfoLabel;
   static SaveData sd;
// private Texture box;
   TechButtons Tech;
   public Texture texture2;
   public static Label TechBackgroundLabel;
   public static Label StatLabel;
   
  // private Texture box;
   
   public MapScreen(enlightenment game, MyGame mg, boolean b)
   {
	   this.mygame = mg;
	   sd = new SaveData(mygame);
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
	   
	   //load map png
	   tiledMap = new TmxMapLoader().load("testmap.tmx");
	   tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
	   //create debug unit
       texture = new Texture(Gdx.files.internal("orangeswordsman.png"));
       //TODO: Player unit to give edge, for final submission give normal stats,
       //	   For DEMO change to debug stats to show features within 10 mins
       mygame.AddEntity(20, 5, 3, mygame.map.tiles[10][10], "Player 3", texture);
       entityactor = new EntityActor(mygame.entities.get(0), "sword 1");

       //create player castle
       Texture castlegraphic = new Texture(Gdx.files.internal("castle.png"));
       mygame.AddEntity(40,  0,  0,  mygame.map.tiles[8][2], "playerbase", castlegraphic);
       CastleBase castle = new CastleBase(mygame.entities.get(1),"base1");
       
       //setup font
       blocky = new BitmapFont(Gdx.files.internal("blocky.fnt"));
       
       //load technologybackground
       texture2 = new Texture(Gdx.files.internal("TechBackground.png"));
       
       
       //Configure camera
	   viewport = new ExtendViewport(512, 384, camera);     
       viewport.apply();
       Viewport uiview = new ExtendViewport(512,384,mapcamera);
       uiview.apply();
    
       camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
       camera.update();
       mapcamera.position.set(mapcamera.viewportWidth / 2, mapcamera.viewportHeight / 2, 0);
       mapcamera.update();
       

       batch = new SpriteBatch();

       //create a stage for ui, and a stage for the map
       stage = new Stage(uiview, batch);
       mapstage = new Stage(viewport, batch);

      
       
       
       
       
       
       //initialize label textures
       BitmapFont font = new BitmapFont();
       Texture te = new Texture(Gdx.files.internal("Brownbox.png"));
      TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(te));
       
      //money label
       Label.LabelStyle style = new LabelStyle();
       style.background = t; // Set the drawable you want to use
       style.font = font;
       label = new Label("                Money: " + String.valueOf(mygame.N[0].money) +"                                                                          "  , style);
       stage.addActor(label);
       
       //unit info label
       unitinfolabel = new Label("\n                          \n \n \n \n",style);
       stage.addActor(unitinfolabel);
       unitinfolabel.setPosition(0, 280);
       unitinfolabel.setAlignment(Align.topLeft);
       
       //Setup inputmultiplexer, to handle input to different stages for this screen
       multiplexer = new InputMultiplexer();
       multiplexer.addProcessor(stage);
       multiplexer.addProcessor(mapstage);
       InputProcessor mapmove = new MapInputHandler();
       multiplexer.addProcessor(mapmove);
       
       //Add in debug unit and player castle to map
       mapstage.addActor(entityactor);
       entityactor.unrestrictedMove(160, 160);
       mapstage.addActor(castle);
       castle.spritePos(128, 32);
       
       //initial tile bonus
       mygame.N[mygame.turn].TileBonus(mygame.map.tiles[(128/16)]  [(32/16)]);
       
       //create an AI (done on the map as creating an AI creates a castle, and we need a map for that)
       ai = new AI(game,mygame);
       
       //save,load game
       if(b == true)
       {
    	   sd.DestroyUnits();
    	   sd.LoadEntity();
    	   sd.LoadNation();
    	   mygame.N[0].TileBonus(mygame.map.tiles[8][2]);
    	   mygame.N[1].TileBonus(mygame.map.tiles[24][20]);
       }
       
       
       //play music (coooould be done in another class, but its like 3 lines)
       //TODO: Decide if this should be put elsewhere
       Music music = Gdx.audio.newMusic(Gdx.files.internal("loopedmusic.mp3"));
       music.setLooping(true);
	   music.play();
	   
	   
	   
	   
   }
   
@Override
//called once
public void show() 
{
	//set input to either use the map-stage, stage, or arrow keys depending on what's pressed.
	Gdx.input.setInputProcessor(multiplexer);
	 //Set table to fill stage
    mainTable.setFillParent(true);
    //Set alignment of contents in the table.
    mainTable.right().bottom();
	
    //button to end turns
    final ImageButtonStyle etbutton = UImanager.configbutton(skin, "brownclock");
    
    ImageButton endturnbutton = new ImageButton(etbutton);
    
  //TODO: Put right graphics here
    final ImageButtonStyle tbutton = UImanager.configbutton(skin, "Gearbutton");
    ImageButton techbutton = new ImageButton(tbutton);
    
    
    endturnbutton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) 
        {
        	mygame.ChangeTurn();
        	sd.SaveEntity();
        	sd.SaveNation();
        	//print out all actors on stage to console, debugging thing
        	//TODO: Get rid of for final implementation
        	//Array<Actor> stageActors = mapstage.getActors();
        	//int i = 0;
        	//while(i<stageActors.size)
        	//{
        	//	Actor a = stageActors.get(i);
        	//	a.toString();
        	//	Gdx.app.log("actor: ", a.toString());
        	//	 i=i+1;
        	//}
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
        TechBackgroundLabel = new Label("\n " , style2);
        stage.addActor(TechBackgroundLabel);	
    	
        BitmapFont font3 = new BitmapFont();
        Texture te3 = new Texture(Gdx.files.internal("Brownbox.png"));
        TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(te3));
        Label.LabelStyle style3 = new LabelStyle();
        style3.background = t; // Set the drawable you want to use
        style3.font = font3;
        StatLabel = new Label("\n                                         \n\n", style3);
        StatLabel.setPosition(353,0);
        stage.addActor(StatLabel);
    	
        TechInfoLabel = new Label("\n                                         \n\n", style3);
        TechInfoLabel.setPosition(0, 0);
        stage.addActor(TechInfoLabel);
    	
    	Tech = new TechButtons(stage);
    	Tech.techMenu();
    
        }
    });
    
    
    
    
    mainTable.add(techbutton);
    mainTable.add(endturnbutton);
    stage.addActor(mainTable);
    
}

@Override
//called repeatedly, ~60 frames / sec to render graphics
public void render(float delta) 
{
	
	Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();
    //TODO: CHANGE LABEL RESOLUTION TO FIT ALL NUMBERS
    //awful terrible code done in awful terrible place so that the label updates. (this is actually a recommended place to put it in LIBDGX, lol)
    label.setText("                Money: " + String.valueOf(mygame.N[0].money) + "  Materials: " + String.valueOf(mygame.N[0].materials)+ "   Tech Points: " + String.valueOf(mygame.N[0].TechPoints));
    //act() ensures that stages have functionality (ex. clicking on a button), draw() renders the items.
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
	//Auto-generated method stub
	//no need to pause, its a turn based game
	
}
@Override
public void resume() {
	//Auto-generated method stub
	
}
@Override
public void hide() 
{
	//Auto-generated method stub
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
