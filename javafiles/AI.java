package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.lang.Math;
public class AI 
{	

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
	int techcounter = 0;
	
	public AI(enlightenment game, MyGame mg)
	{
		this.mygame = mg;
		Texture evilcastle = new Texture(Gdx.files.internal("evilcastle.png"));
	       mygame.AddEntity(40, 0, 0, mygame.map.tiles[24][20], "Player 2", evilcastle);
	       AIActor evilbase = new AIActor(mygame.entities.get(2), "base2");
	       MapScreen.mapstage.addActor(evilbase);//TODO: Should probably pass this into the constructor instead
	       evilbase.setName("aibase");
	       //aibase starts here, maybe change later?
	       evilbase.unrestrictedMove(384,320);
	       evilbase.aitype = 4;
	       //ai tile bonus income
	       mygame.N[1].TileBonus(mygame.map.tiles[(384/16)]  [(320/16)]);
	      
	       
	}
	

	
	public void AISimpleCheck()
	{
		if(mygame.turn == 1)
		{
			
			
			if(mygame.turncount == 1) //build a market on the first turn, help with income and make more troops
			{
				MapScreen.mygame.N[MapScreen.mygame.turn].Build("market");
    			//visual market
    			Texture t = new Texture("market.png");
    			MyActor ma = new MyActor(t, "market");
    			MapScreen.mapstage.addActor(ma);
    			ma.spritePos(384+16, 320);//to the right of ai spawn
			}
			
			//recruit a unit if enough money
			if(mygame.N[mygame.turn].money>=10)
			{
				AIRecruit();
			}
			
			//research a tech if enough points
			if(mygame.N[1].TechPoints >= 100 && techcounter < 15)
			{
				mygame.N[1].research(techcounter);
				techcounter++;
			}
			
			
			//TODO: PUT THIS INTO A SEPARATE FXN THATS CALLED HERE
			if(CastleBase.castlebases.size>0)
			{	//could be used to get proximity, but that would take a very long time.
				//allows ai to hunt down mutliple castles
				int x = (int) (CastleBase.castlebases.get(0).getX()/16);
				int y = (int) (CastleBase.castlebases.get(0).getY()/16);
				Tile T = new Tile(x, y);
				
				//move ai units
				for(int i = 0; i < AIActor.AIActors.size(); i++)
				{ //AIstarMove(T, AIActor.AIActors.get(i));//ai target your base
					if(AIActor.AIActors.get(i).aitype==1)
					{
						AIstarMove(T, AIActor.AIActors.get(i));//ai target your base
					}
					
					if(AIActor.AIActors.get(i).aitype==2 )//|| AIActor.AIActors.get(i).aitype ==1)
					{
						if(EntityActor.entityActors.size>0)
						{
							//target 0th entityactor
							for(int j = 0; j < EntityActor.entityActors.size; j++)
							{
								//if position of entity actor is within range of ai actor
								if(Math.abs(EntityActor.entityActors.get(j).entity.sprite.getX()-AIActor.AIActors.get(i).entity.sprite.getX()) + Math.abs(EntityActor.entityActors.get(j).entity.sprite.getX()-AIActor.AIActors.get(i).entity.sprite.getX()) > AIActor.AIActors.get(i).entity.range*16)
								{
									x = (int) (EntityActor.entityActors.get(0).getX()/16);
									y= (int) (EntityActor.entityActors.get(0).getY()/16);
									Tile T2 = new Tile(x,y);
									AIstarMove(T2, AIActor.AIActors.get(i));
								}
								
							}
						}
						else //else target base
						AIstarMove(T, AIActor.AIActors.get(i));//ai target your base
					}
					
					if(AIActor.AIActors.get(i).aitype==0)
					{
						
						//randomly move within an interval close to the base.
						AIrandMove(AIActor.AIActors.get(i), 448, 320, 384, 256);
						
					}
					
				}
			}
			
			mygame.ChangeTurn();
		}
	}
	
	
	
	public void AIRecruit()
	{
		//subtract money, add upkeep
		mygame.N[mygame.turn].money = mygame.N[mygame.turn].money -10;
        mygame.N[mygame.turn].addUnitUpkeep(1);
        //define entity texture, create it
		Texture tex = new Texture(Gdx.files.internal("blueswordman.png"));
		Entity E = new Entity(mygame.N[1].unitHP, mygame.N[1].swordDMG, mygame.N[1].unitmove, mygame.map.tiles[23][20], "Player 2", tex);
		mygame.entities.add(E);
		aientities.add(E);
		mygame.entity_counter++;
		//add underlying entity representation to actor so it has visual representation
		AIActor aiu = new AIActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "blueswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));
		MapScreen.mapstage.addActor(aiu);
		aiu.unrestrictedMove(368, 320);//TODO: Change when AI can have multiple castles
		
		//set ai type (they move in different ways)

		//type 0: Defend
		//type 1: Attack Settlement
		//type 2: Attack Units
		//type 3 (not created here): stationary
		//makes ai less competent 
		aiu.aitype = AIActor.AIActors.size()%3;
		
	}
	
	public void AIrandMove(AIActor mover, int uboundx,int lboundx, int uboundy,int lboundy)
	{

			if(mover.entity.exhausted == false) 
			{
				int rx = (int) (Math.random() * (mover.entity.range - 0));
				int ry = (int) (Math.random() * (mover.entity.range - 0) - rx);
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
				float x = (float) (mover.entity.sprite.getX()+(rx*16));
				float y = (float) (mover.entity.sprite.getY()+(ry*16));
				//make sure ai doesn't go oob
				if(x<uboundx && y <uboundy && x>lboundx && y>lboundy)
				{
					mover.spritePos(x,y);
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
	//TODO: Add in restriction to prevent 2 ai units from moving onto same tile
	//TODO: set it up so different ai can move in diff ways? ex. each 2nd unit follows a path prioritizing left isntead of down.
	//      can do the todo mentioned in the for loop instead of this too.
	//TODO: FIGURE OUT HOW TO ACTUALLY DYNAMICALLY DETERMINE TARGET
	public void AIstarMove(Tile targettile, AIActor mover) 
	{

		//TODO: currently calls all, maybe get rid of this loop and have the actor to be moved passed into fxn?
		// would mean we could move ai in different ways
		//for(int i = 0; i < AIActor.AIActors.size(); i++)
		//{
			Vector<Tile> OpenTiles = new Vector<Tile>();
			Vector<Tile> ClosedTiles = new Vector<Tile>();
			Tile[][] allTiles=MapScreen.mygame.map.tiles;
			Tile starttile;
			float xpos;
			float ypos;
			int tx;
			int ty;
			if(mover.entity.exhausted == false &&  mover.entity.range > 0) 
			{
				//get current position
				xpos = mover.entity.sprite.getX();
				ypos = mover.entity.sprite.getY();
				//get current position as tile
				tx = (int) xpos/16;
		        ty = (int) ypos/16;
		        starttile = allTiles[tx][ty];
		        Tile currenttile = starttile; 
		        int smallestH = 99999999;

		        /////somesort of loop/////
		        while(!(currenttile.ipos == targettile.ipos && currenttile.jpos==targettile.jpos))
		        {
				 //add start tile to open list
				 OpenTiles.add(currenttile);
				// Gdx.app.log("X: ", String.valueOf(currenttile.ipos));
			 	// Gdx.app.log("Y: ", String.valueOf(currenttile.jpos));
			 	 
				 //look at all tiles up, down, left, right of starttile
				 //ignore illegal terrain (ttype, aiunits occupy)
				 //add all movable tiles to list, saving startpoint as their parent
				 
				 //check to see if unit should prioritize y movement or x movement first.

				 if(allTiles[tx][ty-1].terr != 'w' && !ClosedTiles.contains(allTiles[tx][ty-1])) //&& ai occupies? check later for this &&not on closed list)
				 {
				 	OpenTiles.add(allTiles[tx][ty-1]);//if not in open list
				 	allTiles[tx][ty-1].Parent = currenttile; 
				 }
				 if(allTiles[tx][ty+1].terr != 'w' && !ClosedTiles.contains(allTiles[tx][ty+1])) //&& ai occupies? && not on closed list)
				 {
				 	OpenTiles.add(allTiles[tx][ty+1]); //if not in open list
				 	allTiles[tx][ty+1].Parent = currenttile;

				 }
				 if(allTiles[tx+1][ty].terr != 'w' && !ClosedTiles.contains(allTiles[tx+1][ty])) //&& ai occupies?&& noton clsoed list)
				 {
				 	OpenTiles.add(allTiles[tx+1][ty]); //if not in open list
				 	allTiles[tx+1][ty].Parent = currenttile;
				 }
				 //for some reason here, at 22,3 it doesn't see 21,3 as a valid target. y?
				 if(allTiles[tx-1][ty].terr != 'w' && !ClosedTiles.contains(allTiles[tx-1][ty])) //&& ai occupies? && not on  closed list)
				 {
				 	OpenTiles.add(allTiles[tx-1][ty]); //if not in open list
				 	allTiles[tx-1][ty].Parent = currenttile;
				 }

				 

				 //add start tile to closed vector
				 ClosedTiles.add(currenttile);
				 OpenTiles.remove(currenttile); 
				
				 
				 //pick one from open vector where F = G+H is lowest
				 //(estimate H using Manhattan)
				 //note:Since we're only considering movement in cardinal directions and
				 //all tiles have same move cost, we don't need G.
				 //Manahttan estimation:
				 //Tile besttile;
				 //for everything in open vector
				 Tile checkcurrent = currenttile;
				 for(int j = 0; j<OpenTiles.size(); j++)
				 {					 
					int evalx = OpenTiles.get(j).ipos;
					int evaly = OpenTiles.get(j).jpos;
					int targetx = targettile.ipos;
					int targety = targettile.jpos;
					
					
					int H = Math.abs(evalx-targetx) + Math.abs(evaly-targety);
				//	Gdx.app.log("evaluating X: ", String.valueOf(OpenTiles.get(j).ipos));
				//	Gdx.app.log("evaluating Y: ", String.valueOf(OpenTiles.get(j).jpos));
				//	Gdx.app.log("evaluating H", String.valueOf(H));
					if(H < smallestH)
				 	{
				 		smallestH = H;
				 		currenttile = OpenTiles.get(j);
				 //		Gdx.app.log("ParentX",String.valueOf(currenttile.Parent.ipos));
				//	 	Gdx.app.log("ParentY",String.valueOf(currenttile.Parent.jpos));
				 		tx = currenttile.ipos;
						ty = currenttile.jpos;
				 	}
 
				 }
				 //If it cannot move, go back a space and try
				 if(checkcurrent == currenttile)
				 {
					 currenttile=currenttile.Parent;//doesn't work for some reason?
					 tx = currenttile.ipos;
					 ty = currenttile.jpos;
					 smallestH = 999;///reset heuristics
				 }
				 
				 OpenTiles.clear();//get rid of all open tiles
				 //repeat until target added to closedlist
				 
				 if(currenttile.ipos == targettile.ipos && currenttile.jpos==targettile.jpos)
				 {
				 	//then, work backwards from target to get path (look at its parents)
				 	//then move through each tile to get there.
					// Gdx.app.log("Xpos is ", String.valueOf(mover.entity.sprite.getX()));
					// Gdx.app.log("ypos is ", String.valueOf(mover.entity.sprite.getY()));
					 
					 
					 Tile holdtile = currenttile;
					 while(((Math.abs(((currenttile.ipos)*16)-mover.entity.sprite.getX())) + (Math.abs(((currenttile.jpos)*16)-mover.entity.sprite.getY()))) > mover.entity.range*16)
					 {
						 currenttile=currenttile.Parent;
					 }
					 
					 //if(current tile is within unit range)
					 //move there
					 mover.spritePos(currenttile.ipos*16,currenttile.jpos*16);
					 currenttile = holdtile;
	 
				 }
				 

				 //end of while loop below
		        }
				 //--add in a check for if path faster thru current one?????
			 
				
			//}//end of for loop
			
		}
		
	}
}
