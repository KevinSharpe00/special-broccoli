package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen
{
	//stuff for rendering
	private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
	protected BitmapFont blocky;
	protected Table mainTable;
	enlightenment game;
	private Texture texture;
    private TextureRegion region;
	
    public MainMenuScreen(enlightenment game)
    {
    	
    	this.game = game;
        atlas = new TextureAtlas("buttonsprites.txt"); 
        blocky = new BitmapFont(Gdx.files.internal("blocky.fnt"));
        skin = new Skin(atlas);
        mainTable = new Table(skin);
        mainTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        //think we need a worldwidth and height thing, do that here i guess. (in px?)
        //TODO: make a constants class with all of this info in it. temp values put in
        //what was here before was something like Constants.WorldWidth, Constants.WorldHeight
        viewport = new FitViewport(512, 384, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);   
        
        //Texture.setEnforcePotImages(false);
        texture = new Texture(Gdx.files.internal("mapimageblurred.png"));
                
    }


	@Override
	public void show() 
	{
		//Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        //Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();
        
        //create greenbutton.png as a drawable
        
        //Create buttons
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("greenbutton");
        textButtonStyle.over = skin.getDrawable("greenbutton");
        textButtonStyle.down = skin.getDrawable("greenbutton");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = blocky;
        
        TextButton playButton = new TextButton("Play", textButtonStyle);
        TextButton optionsButton = new TextButton("Options", textButtonStyle);
        TextButton exitButton = new TextButton("Exit", textButtonStyle);
        
        
        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
            	MyGame mg = new MyGame();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MapScreen(game,mg));
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
                Gdx.app.exit();
            }   
        });

        //Add buttons to table
        
        mainTable.add(playButton).pad(20);
        mainTable.row();
        //mainTable.add(optionsButton).pad(20); //TODO: make options do something
        mainTable.row();
        mainTable.add(exitButton);

        //Add table to stage
        stage.addActor(mainTable);
		
	}
	@Override
	public void render(float delta) 
	{
		//TODO: look up what this shit does, i just copied it from stack overflow lol
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(texture,0,0);
        blocky.draw(batch,"ENLIGHTENMENT",154,300);
        batch.end();
        
        stage.act();
        stage.draw();
        
      //batch.setProjectionMatrix(camera.combined);
        //batch.begin();
        //font.draw(batch,"Money: "+ String.valueOf(mygame.N[0].money),20,20);
        //batch.end();
		
	}

	//called when window needs to change size i guess?
	@Override
	public void resize(int width, int height) 
	{
		//TODO: find out what the fuck a viewport is
		viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
		
	}

	@Override
	public void pause() 
	{
		// no need for pause functionality on main menu
	}

	@Override
	public void resume() 
	{
		// left blank for same reason as above
	}

	@Override
	public void hide() 
	{
		//don't take input for this screen anymore
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() 
	{
		skin.dispose();
        atlas.dispose();
        stage.dispose();
        texture.dispose();
	}
	
	
}
