package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

//todo: class relations and inheritance and shit
public class Nation 
{
	double money;
	int TechPoints;
	boolean[] Tech_tree;//20* unlockable technologies
	
	int market_count;
	//unused
	int market_modifier;
	int castle_count;
	double income;
	double upkeep;
	double materials;
	double mat_income;
	int science;
	String Owner;
	
	public Nation()
	{
		money = 0;
		TechPoints = 0;
		Tech_tree = new boolean[20];
		income = 0;
		materials = 20;
		
		mat_income = 2;
		market_count = 0;
		market_modifier = 2;
		castle_count = 0;
		//default values, subject to change
		income = 5;
		science = 20;
		
		
	}
	
	public boolean research(int position)
	{
		boolean researched = false;
		if(Tech_tree[position] == true)
		{
			System.out.println("Tech Already Researched");
			return researched;
		}
		
		//actual cost for technology is subject to change, this makes it more expensive as you go further into tree.
		int cost_multiplier = (position/5) + 1;
		int tech_cost = 100*cost_multiplier;
		
		//check if the precondition tech is researched
		if(7 <= position && position <= 9 && Tech_tree[6] == false)
		{
				System.out.println("Research previous tech to unlock this one");
				return researched;
		}
		if(10 <= position && position <= 12 && Tech_tree[5] == false)
		{
				System.out.println("Research previous tech to unlock this one");
				return researched;
		}
		if(13 <= position && position <= 20 && Tech_tree[4] == false)
		{
				System.out.println("Research previous tech to unlock this one");
				return researched;
		}
		
		//check if they can afford it
		if(TechPoints > tech_cost)
		{
			System.out.println("Tech Aquired");
			TechPoints = TechPoints - tech_cost;
			Tech_tree[position] = true;
			researched = true;
		}
		else
		{
			System.out.println("Not enough Tech Points");
		}

		return researched;
	}
	
	public void AddTechPoints(int sci)
	{
		TechPoints += sci;
	}
	
	public void AddMoney(double i)
	{
		money += i;
	}
	
	//TODO NOT DONE, NEED LOCATION
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
	public void addUnitUpkeep(double m)
	{
		upkeep += m;
	}
	public void endturn()
	{
		
		income = 5 + market_count*market_modifier - upkeep;
		AddMoney(income);
		AddTechPoints(science);
	}
	
	public void printinfo()
	{
		System.out.println("Money: " + money);
		System.out.println("Science: " + science);
		System.out.println("Churches: " + market_count);
		System.out.println("Castles: " + castle_count);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}