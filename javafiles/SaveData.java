package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

public class SaveData {

	Preferences prefdemo;
	Preferences prefnat;
	MyGame mg;
	
	SaveData(MyGame mygame)
	{
		prefdemo = Gdx.app.getPreferences("enlightenment_prefdemo");
		prefnat = Gdx.app.getPreferences("enlightenment_prefnation");
		mg = mygame;
	}
	
	
	public void SaveEntity()
	{
		int c = 0;
		int cap =  MapScreen.mygame.entity_counter;
		prefdemo.putInteger("howmanyunits", cap);
		for(int i = 0; i < cap; i++) 
		{
			int ii = MapScreen.mygame.entities.get(i).position.ipos;
			int jj = MapScreen.mygame.entities.get(i).position.jpos;
			float h = (float) MapScreen.mygame.entities.get(i).health;
			float d = (float) MapScreen.mygame.entities.get(i).damage;
			int r = MapScreen.mygame.entities.get(i).range;
			String com = MapScreen.mygame.entities.get(i).commander;
			String building = MapScreen.mygame.entities.get(i).building;
			
			System.out.println(ii +" " + jj + " " + h + " " + d + " " + r + " " + com);
			prefdemo.putInteger("demounit" + String.valueOf(c), ii);
			c++;
			prefdemo.putInteger("demounit" + String.valueOf(c), jj);
			c++;
			prefdemo.putFloat("demounit" + String.valueOf(c), h);
			c++;
			prefdemo.putFloat("demounit" + String.valueOf(c), d);
			c++;
			prefdemo.putInteger("demounit" + String.valueOf(c), r);
			c++;
			prefdemo.putString("demounit" + String.valueOf(c), com);
			c++;
			prefdemo.putString("demounit" + String.valueOf(c), building);
			c++;
			
			prefdemo.flush();
		}
		
		
		
	}

	public void LoadEntity()
	{
		int c = 0;
		int cap = prefdemo.getInteger("howmanyunits");
		for(int n = 0; n < cap; n++)
		{
			
			
			
			int ii = prefdemo.getInteger("demounit" + String.valueOf(c));
			c++;
			int jj = prefdemo.getInteger("demounit" + String.valueOf(c));
			c++;
			float hh = prefdemo.getFloat("demounit" + String.valueOf(c));
			double h = hh;
			c++;
			float dd = prefdemo.getFloat("demounit" + String.valueOf(c));
			double d = dd;
			c++;
			int r = prefdemo.getInteger("demounit" + String.valueOf(c));
			c++;
			String com = prefdemo.getString("demounit" + String.valueOf(c));
			c++;
			String build = prefdemo.getString("demounit" + String.valueOf(c));
			c++;
			System.out.println(ii +" " + jj + " " + h + " " + d + " " + r + " " + com);
			
			
			//ai stuff
			if(com.equals("Player 2"))
			{
				if(d == 0)
				{
					//ai base
					Texture evilcastle = new Texture(Gdx.files.internal("evilcastle.png"));
				    MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, evilcastle);
				    AIActor evilbase = new AIActor(MapScreen.mygame.entities.get(mg.entity_counter - 1), "base2");
				    MapScreen.mapstage.addActor(evilbase);//TODO: Should probably pass this into the constructor instead
				    evilbase.setName("aibase");
				    //aibase starts here, maybe change later?
				    ii = 16*(ii);
				    jj = 16*(jj);
				    evilbase.unrestrictedMove(ii, jj);
					
				}
				else
				{
					//attack
					Texture tex = new Texture(Gdx.files.internal("blueswordman.png"));
					Entity E = new Entity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, tex);
					MapScreen.mygame.entities.add(E);
					AI.aientities.add(E); //TODO:maybe this shouldn't be static? we'll see
					MapScreen.mygame.entity_counter++;
					//add underlying entity representation to actor so it has visual representation
					AIActor aiu = new AIActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "blueswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));
					MapScreen.mapstage.addActor(aiu);
					aiu.aitype = AIActor.AIActors.size()%3;
					ii = 16*(ii);
				    jj = 16*(jj);
				    aiu.unrestrictedMove(ii, jj);;//TODO: Change when AI can have multiple castles
				}
			}
			//player 1 stuff
			else
			{
				if(d == 0)
				{
					if(build.equals("mine"))
					{
						//mine
						Texture t = new Texture(Gdx.files.internal("mine.png"));
						MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, t, build);
						BuildingActor enta = new BuildingActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter - 1), "sword 1");
					    MapScreen.mapstage.addActor(enta);
					    ii = 16*(ii);
					    jj = 16*(jj);
					    enta.unrestrictedMove(ii, jj);
					}
					else if(build.equals("market"))
					{
						//market
						Texture t = new Texture(Gdx.files.internal("market.png"));
						MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, t, build);
						BuildingActor enta = new BuildingActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter - 1), "sword 1");
					    MapScreen.mapstage.addActor(enta);
					    ii = 16*(ii);
					    jj = 16*(jj);
					    enta.unrestrictedMove(ii, jj);
					}
					else
					{
						//player base
						Texture castlegraphic = new Texture(Gdx.files.internal("castle.png"));
					    MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, castlegraphic);
					    CastleBase castle = new CastleBase(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1),"base1");
					    MapScreen.mapstage.addActor(castle);
	                    ii = 16*(ii);
					    jj = 16*(jj);
					    castle.spritePos(ii, jj);
					}
					
				}
				else if(d == 1)
				{
					//settler
					Texture tex = new Texture(Gdx.files.internal("settler.png"));
                    MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, tex);
                    SettlerActor sa = new SettlerActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter-1), "redswordguy" + String.valueOf(MapScreen.mygame.entity_counter-1));//MapScreen.mygame.entity_counter
                    MapScreen.mapstage.addActor(sa);
                    ii = 16*(ii);
				    jj = 16*(jj);
				    sa.unrestrictedMove(ii, jj);
				}
				else
				{
					//attack
					Texture t = new Texture(Gdx.files.internal("redswordman.png"));
					MapScreen.mygame.AddEntity(h, d, r, MapScreen.mygame.map.tiles[ii][jj], com, t);
				    EntityActor enta = new EntityActor(MapScreen.mygame.entities.get(MapScreen.mygame.entity_counter - 1), "sword 1");
				    MapScreen.mapstage.addActor(enta);
				    ii = 16*(ii);
				    jj = 16*(jj);
				    enta.unrestrictedMove(ii, jj);
				}
			}
			
			
		}
	}

	public void clearunits()
	{
		prefdemo.putInteger("iamclearingthis", 0);
		prefdemo.flush();
		prefdemo.clear();
	}
	
	public void DestroyUnits()
    {
        for(int z = 0; z < EntityActor.entityActors.size; z++)
        {
            EntityActor.entityActors.get(z).remove();
            
        }
        for(int z = 0; z < AIActor.AIActors.size(); z++)
        {
            AIActor.AIActors.get(z).remove();
            AIActor.AIActors.remove(z);
        }
        
        for(int z=0; z<CastleBase.castlebases.size; z++)
        {      
            CastleBase.castlebases.get(z).remove();
            CastleBase.castlebases.removeIndex(z);      
        }
              
          
        MapScreen.mygame.entities.clear();
        MapScreen.mygame.entity_counter = 0;
        MapScreen.mapstage.clear();
    }
	
	//got to add tech tree
	public void SaveNation()
	{
		for(int g = 0; g <= 1; g++)
		{
			float money = (float) mg.N[g].money;
			int TechPoints = mg.N[g].TechPoints;
			float upkeep = (float) mg.N[g].upkeep;
			int castle_count = mg.N[g].castle_count;
			float income = (float) mg.N[g].income;
			int tech_income = mg.N[g].tech_income;
			int market_count = mg.N[g].market_count;
			int market_modifier = mg.N[g].market_modifier;
			int mine_count = mg.N[g].mine_count;
			int mine_modifier = mg.N[g].mine_modifier;
			float materials = (float) mg.N[g].materials;
			float mat_income = (float) mg.N[g].mat_income;
			int watertile_counter = mg.N[g].watertile_counter;
			int mountaintile_counter = mg.N[g].mountaintile_counter;
			int hilltile_counter = mg.N[g].hilltile_counter;
			int terr_money = mg.N[g].terr_money;
			int terr_mat = mg.N[g].terr_mat;
			int castleHP = mg.N[g].castleHP;
			int unitHP = mg.N[g].unitHP;
			int unitmove = mg.N[g].unitmove;
			int swordDMG = mg.N[g].swordDMG;
			int tech_money = mg.N[g].tech_money;
			int tech_mats = mg.N[g].tech_mats;
			
			prefnat.putFloat("money" + String.valueOf(g),money);
			prefnat.putInteger("TechPoints" + String.valueOf(g), TechPoints);
			prefnat.putFloat("upkeep" + String.valueOf(g), upkeep);
			prefnat.putInteger("castle_count" + String.valueOf(g), castle_count);
			prefnat.putFloat("income" + String.valueOf(g), income);
			prefnat.putInteger("tech_income" + String.valueOf(g), tech_income);
			prefnat.putInteger("market_count" + String.valueOf(g), market_count);
			prefnat.putInteger("market_modifier" + String.valueOf(g), market_modifier);
			prefnat.putInteger("mine_count" + String.valueOf(g), mine_count);
			prefnat.putInteger("mine_modifier" + String.valueOf(g), mine_modifier);
			prefnat.putFloat("materials" + String.valueOf(g), materials);
			prefnat.putFloat("mat_income" + String.valueOf(g), mat_income);
			prefnat.putInteger("watertile_counter" + String.valueOf(g), watertile_counter);
			prefnat.putInteger("mountaintile_counter" + String.valueOf(g), mountaintile_counter);
			prefnat.putInteger("hilltile_counter" + String.valueOf(g), hilltile_counter);
			prefnat.putInteger("terr_money" + String.valueOf(g), terr_money);
			prefnat.putInteger("terr_mat" + String.valueOf(g), terr_mat);
			prefnat.putInteger("castleHP" + String.valueOf(g), castleHP);
			prefnat.putInteger("unitHP" + String.valueOf(g), unitHP);
			prefnat.putInteger("unitmove" + String.valueOf(g), unitmove);
			prefnat.putInteger("swordDMG" + String.valueOf(g), swordDMG);
			prefnat.putInteger("tech_mats" + String.valueOf(g), tech_mats);
			prefnat.putInteger("tech_money" + String.valueOf(g), tech_money);
			prefnat.flush();
			
			for(int x = 0; x < 15; x++)
			{
				boolean Techtree = mg.N[g].Tech_tree[x];
				int Techcost = mg.N[g].Tech_cost[x];
				String Techboost = mg.N[g].Tech_boosts[x];
				
				prefnat.putBoolean("Techtree" + String.valueOf(g) + " " + String.valueOf(x), Techtree);
				prefnat.putInteger("Techcost" + String.valueOf(g) + " " + String.valueOf(x), Techcost);
				prefnat.putString("Techboost" + String.valueOf(g) + " " + String.valueOf(x), Techboost);
				prefnat.flush();
			}
		
		}
	}
	//got to add tech tree
	public void LoadNation()
	{
		for(int g = 0; g <= 1; g++)
		{
			
			
			float money = prefnat.getFloat("money" + String.valueOf(g));
			int TechPoints = prefnat.getInteger("TechPoints" + String.valueOf(g));
			float upkeep = prefnat.getFloat("upkeep" + String.valueOf(g));
			int castle_count = prefnat.getInteger("castle_count" + String.valueOf(g));
			float income = prefnat.getFloat("income" + String.valueOf(g));
			int tech_income = prefnat.getInteger("tech_income" + String.valueOf(g));
			int market_count = prefnat.getInteger("market_count" + String.valueOf(g));
			int market_modifier = prefnat.getInteger("market_modifier" + String.valueOf(g));
			int mine_count = prefnat.getInteger("mine_count" + String.valueOf(g));
			int mine_modifier = prefnat.getInteger("mine_modifier" + String.valueOf(g));
			float materials = prefnat.getFloat("materials" + String.valueOf(g));
			float mat_income = prefnat.getFloat("mat_income" + String.valueOf(g));
			int watertile_counter = prefnat.getInteger("watertile_counter" + String.valueOf(g));
			int mountaintile_counter = prefnat.getInteger("mountaintile_counter" + String.valueOf(g));
			int hilltile_counter = prefnat.getInteger("hilltile_counter" + String.valueOf(g));
			int terr_money = prefnat.getInteger("terr_money" + String.valueOf(g));
			int terr_mat = prefnat.getInteger("terr_mat" + String.valueOf(g));
			int castleHP = prefnat.getInteger("castleHP" + String.valueOf(g));
			int unitHP = prefnat.getInteger("unitHP" + String.valueOf(g));
			int unitmove = prefnat.getInteger("unitmove" + String.valueOf(g));
			int swordDMG = prefnat.getInteger("swordDMG" + String.valueOf(g));
			int tech_money = prefnat.getInteger("tech_money" + String.valueOf(g));
			int tech_mats = prefnat.getInteger("tech_mats" + String.valueOf(g));
			
			mg.N[g].money = money;
			mg.N[g].TechPoints = TechPoints;
			mg.N[g].upkeep = upkeep;
			mg.N[g].castle_count = castle_count;
			mg.N[g].income = income;
			mg.N[g].tech_income = tech_income;
			mg.N[g].market_count = market_count;
			mg.N[g].market_modifier = market_modifier;
			mg.N[g].mine_count = mine_count;
			mg.N[g].mine_modifier = mine_modifier;
			mg.N[g].materials = materials;
			mg.N[g].mat_income = mat_income;
			mg.N[g].watertile_counter = watertile_counter;
			mg.N[g].mountaintile_counter = mountaintile_counter;
			mg.N[g].hilltile_counter = hilltile_counter;
			mg.N[g].terr_money = terr_money;
			mg.N[g].terr_mat = terr_mat;
			mg.N[g].castleHP = castleHP;
			mg.N[g].unitHP = unitHP;
			mg.N[g].unitmove = unitmove;
			mg.N[g].swordDMG = swordDMG;
			mg.N[g].tech_money = tech_money;
			mg.N[g].tech_mats = tech_mats;
			
			for(int x = 0; x < 15; x++)
			{		
				boolean Techtree = prefnat.getBoolean("Techtree" + String.valueOf(g) + " " + String.valueOf(x));
				int Techcost = prefnat.getInteger("Techcost" + String.valueOf(g) + " " + String.valueOf(x));
				String Techboost = prefnat.getString("Techboost" + String.valueOf(g) + " " + String.valueOf(x));
				
				mg.N[g].Tech_tree[x] = Techtree;
				mg.N[g].Tech_cost[x] = Techcost;
				mg.N[g].Tech_boosts[x] = Techboost;
			}
		
		}
		
		
	}
	public void clearN()
	{
		prefnat.putInteger("iamclearingthis", 0);
		prefnat.flush();
		prefnat.clear();
	}
	
	
	
	public void fullSave()
	{
		clearunits();
        SaveEntity();
        DestroyUnits();
        LoadEntity();
        clearN();
        SaveNation();
        LoadNation();
	}
	
	
	
	
	
	
	
	
	
}