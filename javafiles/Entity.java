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
    boolean exhausted;
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
        exhausted = false;
        
    }
    
    boolean Move(Tile T)
    {
    	exhausted = true;
        //unrestricted
        position = T;
        return exhausted;
        
        
    }
    
    boolean MoveCheck(Tile T)
    {
    	int dx = position.ipos - T.ipos;
    	int dy = position.jpos - T.jpos;
    	//make both positive;
    	if(dx < 0)
    	{
    		dx *= -1;
    	}
    	if(dy < 0)
    	{
    		dy *= -1;
    	}
    	  	
    	if(dx+dy > range)
    	{
    		System.out.println("Too Far");
    		return false;
    	}
    
    		return true;
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
	
	void Tired()
	{
		exhausted = true;
	}
	void notTired()
	{
		exhausted = false;
	}
	
	
	
}


