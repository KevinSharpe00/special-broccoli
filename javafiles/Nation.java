package com.mygdx.game;



//todo: class relations and inheritance and shit
public class Nation 
{
	double money = 0;
	int TechPoints = 40;
	double materials = 20;
	
	boolean[] Tech_tree = new boolean[15];//15* unlockable technologies
	int[] Tech_cost = new int[15];
	
	double upkeep = 0;
	double income = 0;
	int tech_income = 20;
	double mat_income = 2;
	
	String Owner;
	
	int market_count = 0;
	int market_modifier = 2;
	int castle_count = 0;
		
	int watertile_counter = 0;
	int mountaintile_counter = 0;
	int hilltile_counter = 0;
	
	int terr_money = 0;
	int terr_mat = 0;
	
	//tech-related stats
	int castleHP = 40;
	int mine_count = 0;
	int mine_modifier = 2;
	int unitHP = 20;
	int unitmove = 3;
	int swordDMG = 5;
	int tech_money = 0;
	int tech_mats = 0;
	String[] Tech_boosts = new String[15];
	
	
	
	
	
	
	public Nation()
	{
		money = 0;
		TechPoints = 100;
		
		Tech_tree = new boolean[15];
		for(int i = 0; i < 15; i++)
		{
			Tech_tree[i] = false;
		}
		
		for(int j = 0; j < 15; j++)
		{
			Tech_cost[j] = 100;
		}
		
		Tech_boosts[0] = "Income + 5";
		Tech_boosts[1] = "Material \n      Income + 5";
		Tech_boosts[2] = "Tech Points \n      Income + 5";
		Tech_boosts[3] = "Castle HP + 40";
		Tech_boosts[4] = "Material Income \n      from Market + 1";
		Tech_boosts[5] = "Income from \n      Mine + 1";
		Tech_boosts[6] = "Tech Points \n      Income + 10";
		Tech_boosts[7] = "Income + 20";
		Tech_boosts[8] = "Material \n      Income + 20";
		Tech_boosts[9] = "Unit HP + 5";
		Tech_boosts[10] = "Unit move + 1";
		Tech_boosts[11] = "Sword DMG + 5";
		Tech_boosts[12] = "Unit HP + 10";
		Tech_boosts[13] = "Unit HP + 5 \n      Sword DMG + 5";
		Tech_boosts[14] = "Unit HP + 10 \n      Sword DMG + 10 \n      Unit move + 1";
		
		
	}
	
	public boolean research(int position)
	{
		boolean researched = false;
		if(Tech_tree[position] == true)
		{
			System.out.println("Tech Already Researched");
			return researched;
		}
	
		//don't have the tech, now must check pre-requisites and cost
		if(TechPoints >= Tech_cost[position])
		{
		//0 resources path
		if(Tech_tree[0] == false && position == 0)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_money += 5;
			researched = true;
			return researched;
		}
		//1
		if(Tech_tree[0] == true && Tech_tree[1] == false && position == 1)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_mats += 5;
			researched = true;
			return researched;
		}
		//2
		if(Tech_tree[0] == true && Tech_tree[2] == false && position == 2)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_income += 5;
			researched = true;
			return researched;
		}
		//3
		if(Tech_tree[2] == true && Tech_tree[1] == true && Tech_tree[3] == false && position == 3)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			castleHP += 40;
			researched = true;
			return researched;
		}
		//4
		if(Tech_tree[3] == true && Tech_tree[4] == false && position == 4)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			market_modifier += 1;
			researched = true;
			return researched;
		}
		//5
		if(Tech_tree[3] == true && Tech_tree[5] == false && position == 5)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			mine_modifier += 1;
			researched = true;
			return researched;
		}
		//6
		if(Tech_tree[3] == true && Tech_tree[6] == false && position == 6)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_income += 10;
			researched = true;
			return researched;
		}
		//7
		if(Tech_tree[6] == true && Tech_tree[7] == false && position == 7)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_mats += 20;
			researched = true;
			return researched;
		}
		//8
		if(Tech_tree[6] == true && Tech_tree[8] == false && position == 8)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			tech_money += 20;
			researched = true;
			return researched;
		}
		
		//9, unit tree start
		if(Tech_tree[9] == false && position == 9)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			unitHP += 5;
			researched = true;
			return researched;
		}
		//10
		if(Tech_tree[9] == true && Tech_tree[10] == false && position == 10)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			unitmove += 1;
			researched = true;
			return researched;
		}
		//11
		if(Tech_tree[10] == true && Tech_tree[11] == false && position == 11)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			swordDMG += 5;
			researched = true;
			return researched;
		}
		//12
		if(Tech_tree[10] == true && Tech_tree[12] == false && position == 12)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			unitHP += 10;
			researched = true;
			return researched;
		}
		//13
		if(Tech_tree[11] == true && Tech_tree[12] == true && Tech_tree[13] == false && position == 13)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			unitHP += 5;
			swordDMG += 5;
			researched = true;
			return researched;
		}
		//14
		if(Tech_tree[13] == true && Tech_tree[14] == false && position == 14)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - Tech_cost[position];
			Tech_tree[position] = true;
			unitHP += 5;
			swordDMG += 5;
			unitmove += 1;
			researched = true;
			return researched;
		}
		
		}
	
		else
		{
			System.out.println("Not enough Tech Points");
		}
		
		return researched;
	}
	
	public void TileBonus(Tile C)
	{
		for(int x = C.ipos - 1; x <= C.ipos +1; x++)
		{
			for(int y = C.jpos - 1; y <= C.jpos + 1; y++)
			{
				Tile T = MapScreen.mygame.map.tiles[x][y];
				if (T.terr == 'w')
				{
					terr_money += 2;
				}
				else if (T.terr == 'm')
				{
					terr_mat += 2;
				}
				else if(T.terr == 'h')
				{
					terr_mat += 1;
				}
				else//(T.terr == 'p')
				{
					terr_money += 1;
				}
			}
		}
					
		System.out.println("Bonuses Gained");
	}
	
	public void UndoTileBonus(Tile C)
	{
		for(int x = C.ipos - 1; x <= C.ipos +1; x++)
		{
			for(int y = C.jpos - 1; y <= C.jpos + 1; y++)
			{
				Tile T = MapScreen.mygame.map.tiles[x][y];
				if (T.terr == 'w')
				{
					terr_money -= 2;
				}
				else if (T.terr == 'm')
				{
					terr_mat -= 2;
				}
				else if(T.terr == 'h')
				{
					terr_mat -= 1;
				}
				else//(T.terr == 'p')
				{
					terr_money -= 1;
				}
			}
		}
					
		System.out.println("Bonuses Lost");
	}
	
	public void AddTechPoints(int sci)
	{
		TechPoints += sci;
	}
	
	public void AddMoney(double i)
	{
		money += i;
	}
	
	public void AddMaterials(double m) 
	{
		materials += m;
	}
	
	public boolean Build(String building)
	{
		boolean built = false;
		
		if(building == "market")
		{
			if(materials >= 20)
			{
				materials -= 20;
				System.out.println("market Built");
				built = true;
				market_count++;
			}
			else
			{
				System.out.println("Not enough Money");
			}
		}
		else if(building == "mine")
		{
			if(money >= 20)
			{
				money -= 20;
				System.out.println("mine built");
				built = true;
				mine_count++;
			}
		}
		else if(building == "castle")
		{
			if(materials > 0)
			{
				materials -= 0;
				System.out.println("Castle Built");
				built = true;
				castle_count++;
			}
			else
			{
				System.out.println("Not enough Money");
			}
		}
		else
		{//catch wrong name entries
			System.out.println("Broken");
			return built;
		}

		return built;
	}
	
	public void endturn()
	{
		
		income = market_count*market_modifier - upkeep + terr_money + tech_money;
		AddMoney(income);
		AddTechPoints(tech_income);
		mat_income = 2 + terr_mat + tech_mats;
		AddMaterials(mat_income);
	}
	
	public void printinfo()
	{
		System.out.println("Money: " + money);
		System.out.println("Science: " + TechPoints);
		System.out.println("Markets: " + market_count);
		System.out.println("Castles: " + castle_count);
	}
	
	
	public void addUnitUpkeep(double m)
    {
        upkeep += m;
    }
	
	
	
	
	
	
	
	
	
	
	
}