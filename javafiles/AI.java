package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.lang.Math;
public class AI 
{	
	//things to do:
	//ai needs to:
	//use commands to make units
	//use commands to move those units
	/* units will move:
	 * 1st priority: have at least 50% ish of all active units defending settlements
	 * set it up so that ai "intercepts" player units near its base
	 * 2nd priority: have other half of units set to be "attack units"
	 * attack units will move towards enemy base
	 * todo later: have units take different paths towards base in an attempt to ovewhelm?
	 * (for now its fine if they all just congaline lol)
	 * 
	 * later on, ai also has to:
	 * -build buildings
	 * -settle
	 * -other stuff?
	 * 
	 * idea: let ai cheat (lol) to manage stuff, that way only the pathfinding has to be half decentish
	 */
	MyGame mygame;
	public static Vector<Entity> aientities = new Vector<Entity>();
	public AI(enlightenment game, MyGame mg)
	{
		this.mygame = mg;
		   Texture evilcastle = new Texture(Gdx.files.internal("evilcastle.png"));
	       mygame.AddEntity(40, 0, 0, mygame.map.tiles[24][20], "Player 2", evilcastle);
	       AIActor evilbase = new AIActor(mygame.entities.get(1), "base2");
	       MapScreen.mapstage.addActor(evilbase);//TODO: Should probably pass this into the constructor instead
	       evilbase.setName("aibase");
	       //aibase starts here, maybe change later?
	       evilbase.unrestrictedMove(384,320);
	       mygame.N[1].TileBonus(mygame.map.tiles[24][20]);
	       
	}
	

	
	public void AISimpleCheck()
	{
		if(mygame.turn == 1)
		{

			if(mygame.N[mygame.turn].money>=5)
			{
				AIRecruit();
			}
		
			AIrandMove();
			mygame.ChangeTurn();
		}
	}
	
	
	
	public void AIRecruit()
	{
		//subtract money, add upkeep
		mygame.N[mygame.turn].money = mygame.N[mygame.turn].money -5;
        mygame.N[mygame.turn].addUnitUpkeep(1);
        //for testing: Gdx.app.log("money ", String.valueOf(mygame.N[mygame.turn].money));
        //define entity texture, create it
		Texture tex = new Texture(Gdx.files.internal("blueswordman.png"));
		Entity E = new Entity(20, 5, 3, mygame.map.tiles[23][20], "Player 2", tex);
		mygame.entities.add(E);
		aientities.add(E);
		mygame.entity_counter++;
		//add underlying entity representation to actor so it has visual representation
		AIActor aiu = new AIActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "blueswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));
		MapScreen.mapstage.addActor(aiu);
		aiu.unrestrictedMove(368, 320);//TODO: Change when AI can have multiple castles
		
	}
	
	public void AIrandMove()
	{
		for(int i = 0; i < AIActor.AIActors.size(); i++)
		{
			if(AIActor.AIActors.get(i).entity.exhausted == false) 
			{
				int rx = (int) (Math.random() * (AIActor.AIActors.get(i).entity.range - 0));
				int ry = (int) (Math.random() * (AIActor.AIActors.get(i).entity.range - 0) - rx);
				double nx = Math.random();
				double ny = Math.random();
				//enable movement in negative directions
				if(nx > 0.5)
				{
					rx*=-1;
				}
				if(ny > 0.5)
				{
					ry*=-1;
				}
				float x = (float) (AIActor.AIActors.get(i).entity.sprite.getX()+(rx*16));
				float y = (float) (AIActor.AIActors.get(i).entity.sprite.getY()+(ry*16));
				//make sure ai doesn't go oob
				if(x<640 && y <480 && x>0 && y>0)
				{
					AIActor.AIActors.get(i).spritePos(x,y);
				}
			}
		}
	}
	
	public boolean AILost()
	{
		//hotfix for a bug we found last minute
		//crashes game when AI has lost, so we just make it return true in that case
		try
		{
		for(int i = 0; i < AIActor.AIActors.size(); i++)
		{
			
			if(AIActor.AIActors.get(i).getName().equals("aibase"))
			{
				return false;
			}
			
		}
		}
		catch(Exception e)
		{
		return true;
		}
		return true;
	}
	
	//our own implementation of a* pathfinding
	public void AIstarMove()
	{
		//create open and closed search vectors
		Vector<Tile> OpenTiles = new Vector<Tile>();
		Vector<Tile> ClosedTiles = new Vector<Tile>();
		Tile[][] allTiles=MapScreen.mygame.map.tiles;
		Tile starttile;
		float xpos;
		float ypos;
		int tx;
		int ty;
		
		for(int i = 0; i < AIActor.AIActors.size(); i++)
		{
			if(AIActor.AIActors.get(i).entity.exhausted == false) 
			{
				//get current position
				xpos = AIActor.AIActors.get(i).entity.sprite.getX();
				ypos = AIActor.AIActors.get(i).entity.sprite.getY();
				//get current position as tile
				tx = (int) xpos/16;
		        ty = (int) ypos/16;
		        starttile = allTiles[tx][ty];
		        
		        /////somesort of loop/////
		        /*
				* add start tile to open list
				* look at all tiles up, down, left, right of starttile
				* ignore illegal terrain (ttype, aiunits occupy)
				* add all movable tiles to list, saving startpoint as their parent
				* add start tile to closed vector
				* pick one from open vector where F = G+H is lowest
				* (estimate H using Manhattan)
				* repeat
				* --add in a check for if path faster thru current one?????
				* repeat until target added to closedlist
				* then, work backwards from target to get path (look at its parents)
				* then move through each tile to get there.
				*/
			}
			
		}
		
	}
}
