package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;


//named 'entity' cause it might need to include walls or smthn idk
//basically just the unit class
public class Entity //does it extend or implement something?
{
	double health;
    double damage;
    int range;
    Tile position;
    String commander;
    boolean istouched;
    
	Texture tex;
    Sprite sprite;
    
  //TODO  need to add texture
    public Entity(double h, double d, int r, Tile p, String c, Texture t)
    {
        health = h;
        damage = d;
        range = r;//just ignore for now
        position = p;
        commander = c;
        tex = t;
        sprite = new Sprite(tex);
        istouched = false;
    }
    
    boolean Move(Tile T)
    {
        boolean moved = false;
        //unrestricted
        position = T;
        return moved;
    }
    
    public void printInfo()
    {
        System.out.println();
        System.out.println("Health: " + health);
        System.out.println("Damage: " + damage);
        System.out.println("Range: " + range);
        position.getCoords();
        System.out.println("Commander: " + commander);
    }
	//TODO build
	
    public String getCommander()
	{
		return commander;
	}
	
	public double getdamage()
	{
		return damage;
	}
	
	public Tile getpos()
	{
		return position;
	}
	
	public void hurt(double dmg)
	{
		health -= dmg;
	}

	//new stuff ///////////////////////////////////////////////////////////////////////////
	void makesprite(SpriteBatch sprite_batch)
	{
		sprite_batch.begin();
		sprite.draw(sprite_batch);
		sprite_batch.end();
	}
	void setspritepos(float x, float y)
	{
		sprite.setPosition(x, y);
	}
	
	void Touch()
	{
		istouched = true;
	}
	void UnTouch()
	{
		istouched = false;
	}



}


