package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame {

	Map map;
	Nation[] N = new Nation[2];
	public int turn;
	public int turncount;
	SpriteBatch sb;
	public Vector<Entity> entities = new Vector<Entity>();
	int entity_counter = 0;
	MyGame thisgame;
	AI ai;
	
	public MyGame()
	{
		thisgame = this;
		//sprite_batch = new SpriteBatch();
		map = new Map(40, 30);
		
		Nation P1 = new Nation();
		Nation P2 = new Nation();
		N = new Nation[2];
		N[0] = P1;
		N[1] = P2;
		
		turn = 0;
		turncount = 0;
	}
	
	public MyGame(int i, int j)
	{
		//sprite_batch = new SpriteBatch();
		map = new Map(i, j);
		N = new Nation[4];
		turn = 0;
	}
	
	/*
	 * adds a building to the specific tile
	 * 
	 * returns true if building is built
	 * returns false is invalid building
	 */
	boolean AddBuilding(String building, int ipos, int jpos)
	{
		return map.tiles[ipos][jpos].AddBuilding(building);
	}
	
	/*
	 * prints the map on the console
	 * for testing
	 */
	/*
	public void MapPrint()
	{
		for(int n = 0; n < map.height; n++)
		{
			for(int m = 0; m < map.length; m++)
			{
				System.out.print(map.tiles[n][m].test);
			}
			System.out.println();
		}
	}*/
	
	/////////////new Entity Stuff
	
	public void EntityPrint(int l)
	{
		entities.get(l).printInfo();
	}
	
	public void AddEntity(double h, double d, int r, Tile p, String c, Texture t)
	{
		Entity E = new Entity(h, d, r, p, c, t);
		entities.add(E);
		entity_counter++;
	}
	
	public void AddSwordsman(Tile p, String c)
	{
		Texture tex = new Texture(Gdx.files.internal("redswordman.png"));
		Entity E = new Entity(20, 5, 3, p, c, tex);
		entities.add(E);
		entity_counter++;
	}
	
	public void MoveEntity(int l, Tile T)
	{
		if(T.getlocked() == true)
		{//locked, do nothing
			System.out.println("Tile locked");
			return;
		}
		int c = 0;//counter
		boolean move = true;
		
		for(int i = 0; i < entity_counter; i++)
		{
			if(entities.get(i).position == T)
			{
				c++;
				
				
			}
		}
		if(c == 1)
		{
			for(int j = 0; j < entity_counter; j++)
			{
				if(entities.get(j).commander == entities.get(l).commander && j != l)
				{//	current unit has the same commander as input unit
					move = false;
					System.out.println("Bad move");
				}
			}
		}
		
		if(c > 1)
		{
			T.setlocked(true);
		}
		
		if(move == true)
		{
			entities.get(l).Move(T);
			c++;
			if(c > 1)
			{
				T.setlocked(true);
			}
		}
	}
	
	public void Battle(Entity A, Entity B)
	{
		if(A.getpos() == B.getpos())
		{
			if(A.getCommander() != B.getCommander())
			{
				A.hurt(B.getdamage());
				if(A.health <= 0)
				{	
					 if(A.commander == "Player 1")
	                    {
	                        N[0].addUnitUpkeep(-1);
	                    }
	                    else
	                    {
	                        N[1].addUnitUpkeep(-1);
	                    }
					A.commander = "Dead";
					A.damage = 0;
					A.Tired();
				}
				return;
			}
		}		
			System.out.println("Invalid");
	}
	
	public void endTurnBattles()
	{//makes units battle twice
		for(int i = 0; i < entity_counter; i++)
		{
			for(int j = 0; j < entity_counter; j++)
			{
				if(i != j && entities.get(i).position == entities.get(j).position)
				{
					Battle(entities.get(i), entities.get(j));
				}
				
			}
		}
	}
	
	
	
	
	
	
	public void ShiftEntities()
	{
		for(int i = 0; i < entity_counter; i++)
		{
			if(entities.get(i).commander == "Dead" || entities.get(i).health <= 0)
			{
				EntityActor.deathcheck();
				AIActor.deathcheck();
				entities.remove(i);
				i--;
                entity_counter--;
			}
		}
	}
	
	
	public void SwordCheck(float x, float y)
	{//summons swords
		for(int i = 0; i < entity_counter; i++)
		{
			for(int j = 0; j < entity_counter; j++)
			{
				if(i != j && entities.get(i).position == entities.get(j).position)
				{
					if(entities.get(i).getCommander() != entities.get(j).getCommander())
					{
						Texture t = new Texture("bettercrossedswords.png");
						SwordActor sw = new SwordActor(t, "Swords");
						MapScreen.mapstage.addActor(sw);
						sw.spritePos(x, y);
					}
					
				}				
			}
		}
	}
	public void ClearSwords()
	{
		for(int i = 0; i <  SwordActor.swords.size; i++)
		{
			
				SwordActor.swords.get(i).remove();
				SwordActor.swords.removeIndex(i);
				
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	/////////////new Nation stuff
	
	public void AddMoney(int m)
	{
		N[turn].AddMoney(m);
	}
	
	public void AddTechPoints(int t)
	{
		N[turn].AddTechPoints(t);
	}
	
	public void BuildBuilding(String b, int x, int y)
	{
		if(N[turn].Build(b) == true)
		{
			boolean e = map.tiles[x][y].AddBuilding(b);
			if(e == false)
			{
				System.out.println("Invalid");
			}
		}
		
	}
	
	public void Research(int tech)
	{
		N[turn].research(tech);
	}
	
	public void printinfo()
	{
		N[turn].printinfo();
	}
	
	
	public void ChangeTurn()
	{
		N[turn].endturn();
		turn++;
		turncount++;
		if(turn == 2)
		{
			turn = 0;
		}
		//untire each entity
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).notTired();
		}
		//check for battles
		turnlose();
		endTurnBattles();
		ShiftEntities();
		this.ai = MapScreen.ai;
		if(ai.AILost())
		{
	    	((Game)Gdx.app.getApplicationListener()).setScreen(new WinScreen());
		}
		ai.AISimpleCheck();
		
		
	}
	
	
	public void turnlose()
	{
		boolean castle = false;
		boolean turn = false;
		if(CastleBase.castlebases.size == 0)
		{
			castle = true;
		}		
		if(turncount >= 200)
		{
			turn = true;
		}
		
		
		if(turn | castle) 
		{
			((Game)Gdx.app.getApplicationListener()).setScreen(new LoseScreen());
		}
		
	}
		
}
