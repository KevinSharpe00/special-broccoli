package com.mygdx.game;

enum landscapes
{
	plains, hills, mountains, water
}

enum buildings
{
	market, castle, base
}




public class Tile {
	String terrain;
	int ipos;
	int jpos;
	boolean locked = false;
	landscapes l;
	buildings[] b = new buildings[3];
	//terr character for printing from game
	char terr;
	
	
	
	
	/*
	 * basic constructor
	 */
	Tile(int i, int j)
	{
		ipos = i;
		jpos = j;
		l = landscapes.plains;
	}
	
	/*
	 * constructor for landscape, should be used as default
	 * defaults to plains terrain
	 */
	Tile(int i, int j, String land)
	{
		ipos = i;
		jpos = j;
		if(land == "water")
		{
			terr = 'w';
			l = landscapes.water;
		}
		else if(land == "mountains")
		{
			terr = 'm';
			l = landscapes.mountains;
		}
		else if(land == "hills")
		{
			terr = 'h';
			l = landscapes.hills;
		}
		else
		{
			terr = 'p';
			l = landscapes.plains;
		}
	}
	
	void setland(char t)
	{
		if(t == 'w')
		{
			terr = 'w';
			l = landscapes.water;
		}
		else if(t == 'm')
		{
			terr = 'm';
			l = landscapes.mountains;
		}
		else if(t == 'h')
		{
			terr = 'h';
			l = landscapes.hills;
		}
		else
		{
			terr = 'p';
			l = landscapes.plains;
		}
	}
	
	
	
	/*
	 * add a building to the tile
	 * 
	 * returns true if the building was placed
	 * returns false if the building was invalid
	 * 
	 * TODO does not check for duplicates
	 */
	boolean AddBuilding(String build)
	{
		boolean ret = false;
		if(build == "market")
		{
			b[0] = buildings.market;
			ret = true;
		}
		else if(build == "castle")
		{
			b[1] = buildings.castle;
			ret = true;
		}
		else if(build == "base")
		{
			b[0] = buildings.base;
			ret = true;
		}
		return ret;	
		
	}
	
	public void getCoords()
	{
		System.out.println("X position: " + ipos);
		System.out.println("Y position: " + jpos);
	}
	
	public boolean getlocked()
	{
		return locked;
	}
	
	public void setlocked(boolean set)
	{
		locked = set;
	}
	
	
}

