package com.mygdx.game;

enum landscapes
{
	plains, mountains, impassible
}

enum buildings
{
	church, castle, base
}




public class Tile {
	String terrain;
	int ipos;
	int jpos;
	boolean locked = false;
	landscapes l;
	buildings[] b = new buildings[3];
	//test character for printing from game
	char test;
	
	
	
	
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
		if(land == "impassible")
		{
			test = 'i';
			l = landscapes.impassible;
		}
		else if(land == "mountains")
		{
			test = 'm';
			l = landscapes.mountains;
		}
		else
			{
			test = 'p';
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
		if(build == "church")
		{
			b[0] = buildings.church;
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

