package com.mygdx.game;



public class Map {
	
	Tile[][] tiles;
	int length;
	int height;
	
	//TODO convert map to use landscape 
	Map(int i, int j)
	{
		tiles = new Tile[i][j];
		for(int n = 0; n < i; n++)
		{
			for(int m = 0; m < j; m++)
			{
				Tile t = new Tile(n, m , "plains");
				tiles[n][m] = t;
		
			}
		}
		height = i;
		length = j;
	}
	
	/*
	 *specialized map generation mode, used to create special preset maps
	 *
	 *currently generates 10x10 map of plains with impassible exterior
	 *
	 *TODO make better maps
	 *
	 *
	 *
	 */
	Map(String mode)
	{
		tiles = new Tile[10][10];
		for(int n = 0; n < 10; n++)
		{
			for(int m = 0; m < 10; m++)
			{
				if(n == 0 || n == 9 || m == 0 || m == 9)
				{
					Tile t = new Tile(n, m , "impassible");
					tiles[n][m] = t;
				}
				else
				{
					Tile t = new Tile(n, m , "plains");
					tiles[n][m] = t;
			
				}
		
			}
			Tile mound = new Tile(4, 4, "mountains");
			tiles[4][4] = mound;
			Tile mound2 = new Tile(5, 4, "mountains");
			tiles[5][4] = mound2;
			Tile mound3 = new Tile(4, 5, "mountains");
			tiles[4][5] = mound3;
			Tile mound4 = new Tile(5, 5, "mountains");
			tiles[5][5] = mound4;
		}
		height = 10;
		length = 10;
	}
	
	
	
	public Tile getTile(int i, int j)
	{
		return tiles[i][j];
	}

	

}