package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapScreen implements Screen, InputProcessor
{
	
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
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
   protected Stage stage;
   private Stage mapstage;
   private InputMultiplexer multiplexer;
   MyActor myactor;
  // private Texture box;
   
   public MapScreen(enlightenment game)
   {
	   
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
	   
	   tiledMap = new TmxMapLoader().load("design project.tmx");
	   tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
       texture = new Texture(Gdx.files.internal("pukichow.png"));
       
       myactor = new MyActor(texture, "pukichow");
       
       
       blocky = new BitmapFont(Gdx.files.internal("blocky.fnt"));
       
	   viewport = new FitViewport(800, 480, camera);
       viewport.apply();

       camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
       camera.update();

       batch = new SpriteBatch();
       //stage currently doesn't work??
       stage = new Stage(viewport, batch);
       //not tooooo sure about this line, change to be like other stuff if it dont work
       mapstage = new Stage(new ScreenViewport());
      
       multiplexer = new InputMultiplexer();
       multiplexer.addProcessor(stage);
       multiplexer.addProcessor(mapstage);
       
       mapstage.addActor(myactor);
       
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
	
    ImageButtonStyle imageButtonStyle = new ImageButtonStyle();
    imageButtonStyle.up = skin.getDrawable("brownsword");
    imageButtonStyle.over = skin.getDrawable("brownsword");
    imageButtonStyle.down = skin.getDrawable("brownsword");
    imageButtonStyle.pressedOffsetX = 1;
    imageButtonStyle.pressedOffsetY = -1;
    
    ImageButton swordbutton = new ImageButton(imageButtonStyle);
    
    
    swordbutton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) 
        {
        	//temp thing lol, while this gets tested
        	Gdx.app.exit();
        }
    });
   
    mainTable.add(swordbutton);
    mapstage.addActor(mainTable);
    
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
    //sb.setProjectionMatrix(camera.combined);
    //stage.act(), stage.draw();
    //sb.begin();
    //sprite.draw(sb);
    //sb.end();	
    
    //TODO: MAPSTAGE STUFF
    stage.act();
    mapstage.act();
    
    
    stage.draw();
	mapstage.draw();
}
@Override
public void resize(int width, int height) {
	// TODO Auto-generated method stub
	
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
	sb.dispose();
	stage.dispose();
	mapstage.dispose();
	skin.dispose();
	atlas.dispose();
}

@Override
public boolean keyDown(int keycode) 
{
	if(keycode == Input.Keys.LEFT)
        camera.translate(-32,0);
    if(keycode == Input.Keys.RIGHT)
        camera.translate(32,0);
    if(keycode == Input.Keys.UP)
        camera.translate(0,32);
    if(keycode == Input.Keys.DOWN)
        camera.translate(0,-32);
    if(keycode == Input.Keys.NUM_1)
        tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
	return false;
}

@Override
public boolean keyUp(int keycode) 
{
	
	return false;
}

@Override
public boolean keyTyped(char character) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchDown(int screenX, int screenY, int pointer, int button) 
{
	int X = screenX/48;
	X = X * 48;
	int Y = screenY/34;
	Y = Y * 34 + 34;
    Vector3 clickCoordinates = new Vector3(X,Y,0);
    Vector3 position = camera.unproject(clickCoordinates);
    sprite.setPosition(position.x, position.y);
    return true;
}

@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchDragged(int screenX, int screenY, int pointer) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean mouseMoved(int screenX, int screenY) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean scrolled(float amountX, float amountY) 
{
	camera.zoom = camera.zoom+amountY;
	camera.update();
	return false;
}

    

}
