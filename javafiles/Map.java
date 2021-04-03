package com.mygdx.game;



public class Map {
	
	Tile[][] tiles;
	int length;
	int height;
	
	//TODO convert map to use landscape 
	Map(int x, int y)
	{
		tiles = new Tile[x][y];
		for(int n = 0; n < x; n++)
		{
			for(int m = 0; m < y; m++)
			{
				Tile t = new Tile(n, m , "plains");
				tiles[n][m] = t;
		
			}
		}
		height = x;
		length = y;
		
		char w = 'w';
		char m = 'm';
		char h = 'h';
		char p = 'p';
		//outer perimeter of ocean
		for(int a = 0; a < length; a++)
		{
			tiles[0][29 - a].setland(w);
			
			tiles[38][a].setland(w);
			tiles[39][a].setland(w);
		}
		for(int b = 0; b < height; b++)
		{
			tiles[b][0].setland(w);
			tiles[b][1].setland(w);
			tiles[b][28].setland(w);
			tiles[b][29].setland(w);
		}
		
			//south coast
			tiles[4][1].setland(p);
			tiles[5][1].setland(p);
			tiles[9][1].setland(p);
			tiles[10][1].setland(p);
			tiles[11][1].setland(p);
			tiles[14][1].setland(p);
			tiles[15][1].setland(p);
			tiles[26][1].setland(p);
			tiles[27][1].setland(p);
			tiles[29][1].setland(p);
			tiles[30][1].setland(p);
			tiles[31][1].setland(p);
			
			tiles[20][2].setland(w);
			tiles[21][2].setland(w);
			tiles[34][2].setland(w);
			tiles[35][2].setland(w);
			tiles[36][2].setland(w);
			
			//north coast
			tiles[11][29 - 1].setland(p);
			tiles[12][29 - 1].setland(p);
			tiles[19][29 - 1].setland(p);
			tiles[20][29 - 1].setland(p);
			tiles[21][29 - 1].setland(p);
			tiles[22][29 - 1].setland(p);
			tiles[23][29 - 1].setland(p);
			tiles[24][29 - 1].setland(p);
			tiles[25][29 - 1].setland(p);
			tiles[32][29 - 1].setland(p);
			tiles[33][29 - 1].setland(p);
			tiles[34][29 - 1].setland(p);
		
			tiles[13][29 - 2].setland(w);
			tiles[14][29 - 2].setland(w);
		
			//left to right
			
			//river
			tiles[1][29 - 1].setland(w);
			tiles[1][29 - 2].setland(w);
			tiles[1][29 - 3].setland(w);
			tiles[1][29 - 4].setland(w);
			tiles[1][29 - 8].setland(w);
			tiles[1][29 - 9].setland(w);
			tiles[1][29 - 13].setland(w);
			tiles[1][29 - 14].setland(w);
			tiles[1][29 - 20].setland(w);
			tiles[1][29 - 21].setland(w);
			tiles[1][29 - 22].setland(w);
	
			tiles[2][29 - 2].setland(w);
			tiles[2][29 - 9].setland(w);
			tiles[2][29 - 10].setland(w);
			tiles[2][29 - 21].setland(w);
			tiles[2][29 - 22].setland(w);
		
			tiles[3][29 - 10].setland(w);
			tiles[3][29 - 11].setland(w);
			tiles[3][29 - 20].setland(w);
			tiles[3][29 - 21].setland(w);
			
			tiles[4][29 - 11].setland(w);
			tiles[4][29 - 12].setland(w);
			tiles[4][29 - 13].setland(w);
			tiles[4][29 - 18].setland(w);
			tiles[4][29 - 19].setland(w);
			tiles[4][29 - 20].setland(w);
			tiles[4][29 - 21].setland(w);
	
			tiles[5][29 - 13].setland(w);
			tiles[5][29 - 14].setland(w);
			tiles[5][29 - 15].setland(w);
			tiles[5][29 - 16].setland(w);
			tiles[5][29 - 17].setland(w);
			tiles[5][29 - 18].setland(w);
			tiles[5][29 - 19].setland(w);
		
			tiles[6][29 - 14].setland(w);
			tiles[6][29 - 15].setland(w);
			tiles[6][29 - 16].setland(w);
			tiles[6][29 - 17].setland(w);
		
			//big pond
			tiles[12][29 - 5].setland(w);
			tiles[12][29 - 6].setland(w);
			tiles[12][29 - 7].setland(w);
			tiles[12][29 - 8].setland(w);
	
			tiles[13][29 - 5].setland(w);
			tiles[13][29 - 6].setland(w);
			tiles[13][29 - 7].setland(w);
			tiles[13][29 - 8].setland(w);
		
			tiles[14][29 - 4].setland(w);
			tiles[14][29 - 5].setland(w);
			tiles[14][29 - 6].setland(w);
			tiles[14][29 - 7].setland(w);
			tiles[14][29 - 8].setland(w);
			tiles[14][29 - 9].setland(w);
		
			tiles[15][29 - 4].setland(w);
			tiles[15][29 - 5].setland(w);
			tiles[15][29 - 6].setland(w);
			tiles[15][29 - 8].setland(w);
			tiles[15][29 - 9].setland(w);
		
			tiles[16][29 - 4].setland(w);
			tiles[16][29 - 5].setland(w);
			tiles[16][29 - 8].setland(w);
			tiles[16][29 - 9].setland(w);
	
			tiles[17][29 - 4].setland(w);
			tiles[17][29 - 5].setland(w);
			tiles[17][29 - 6].setland(w);
			tiles[17][29 - 7].setland(w);
			tiles[17][29 - 8].setland(w);
			tiles[17][29 - 9].setland(w);
	
			tiles[18][29 - 4].setland(w);
			tiles[18][29 - 5].setland(w);
			tiles[18][29 - 6].setland(w);
			tiles[18][29 - 7].setland(w);
			tiles[18][29 - 8].setland(w);
			
			tiles[19][29 - 5].setland(w);
			tiles[19][29 - 6].setland(w);
			tiles[19][29 - 7].setland(w);
	
			//little pond
			tiles[23][29 - 13].setland(w);
			tiles[23][29 - 14].setland(w);
			tiles[23][29 - 15].setland(w);
			tiles[23][29 - 16].setland(w);
		
			tiles[24][29 - 13].setland(w);
			tiles[24][29 - 14].setland(w);
			tiles[24][29 - 15].setland(w);
			tiles[24][29 - 16].setland(w);
	

			tiles[25][29 - 14].setland(w);
			tiles[25][29 - 15].setland(w);
			tiles[25][29 - 16].setland(w);
		
			tiles[26][29 - 14].setland(w);
			tiles[26][29 - 15].setland(w);
	
			//right coast
			tiles[34][29 - 15].setland(w);
	
			tiles[35][29 - 14].setland(w);
			tiles[35][29 - 15].setland(w);
			tiles[35][29 - 16].setland(w);
			tiles[35][29 - 17].setland(w);
			tiles[35][29 - 18].setland(w);
		
			tiles[36][29 - 13].setland(w);
			tiles[36][29 - 14].setland(w);
			tiles[36][29 - 15].setland(w);
			tiles[36][29 - 16].setland(w);
			tiles[36][29 - 17].setland(w);
			tiles[36][29 - 18].setland(w);
			tiles[36][29 - 19].setland(w);
			tiles[36][29 - 20].setland(w);
			tiles[36][29 - 21].setland(w);
			tiles[36][29 - 22].setland(w);
			
			tiles[37][29 - 2].setland(w);
			tiles[37][29 - 12].setland(w);
			tiles[37][29 - 13].setland(w);
			tiles[37][29 - 14].setland(w);
			tiles[37][29 - 15].setland(w);
			tiles[37][29 - 16].setland(w);
			tiles[37][29 - 17].setland(w);
			tiles[37][29 - 18].setland(w);
			tiles[37][29 - 19].setland(w);
			tiles[37][29 - 20].setland(w);
			tiles[37][29 - 21].setland(w);
			tiles[37][29 - 22].setland(w);
			tiles[37][29 - 23].setland(w);
	
			tiles[38][29 - 5].setland(p);
			tiles[38][29 - 6].setland(p);
			tiles[38][29 - 7].setland(p);
			tiles[38][29 - 8].setland(p);
			tiles[38][29 - 9].setland(p);
			tiles[38][29 - 10].setland(p);
		
			
			//hill patch above big pond
			
			//make a square of hills
			for(int d = 11; d <= 18; d++)
			{
				for(int e = 13; e <= 19; e++)
				{
					tiles[d][29 - e].setland(h);
				}
			}
			//modify the square to match map
			tiles[10][29 - 16].setland(h);
			
			tiles[11][29 - 13].setland(p);
			tiles[11][29 - 19].setland(p);
			
			tiles[12][29 - 19].setland(p);
			
			tiles[17][29 - 13].setland(p);
			tiles[17][29 - 14].setland(p);
			tiles[17][29 - 15].setland(p);
			
			tiles[18][29 - 13].setland(p);
			tiles[18][29 - 14].setland(p);
			tiles[18][29 - 15].setland(p);
			tiles[18][29 - 16].setland(p);
			tiles[18][29 - 19].setland(p);
			
			//mountain patch right of big pond
			
			//make mountain square
			for(int f = 29; f <= 33; f++)
			{
				for(int g = 5; g <= 9; g++)
				{
					tiles[f][29 - g].setland(m);
				}
			}
			
			//fix square
			tiles[28][29 - 5].setland(h);
			tiles[28][29 - 6].setland(h);
			tiles[28][29 - 7].setland(h);
			tiles[28][29 - 8].setland(m);
			tiles[28][29 - 9].setland(m);
			
			tiles[29][29 - 4].setland(h);
			tiles[29][29 - 10].setland(h);
			
			tiles[30][29 - 4].setland(h);
			tiles[30][29 - 10].setland(m);
			
			tiles[31][29 - 4].setland(h);
			tiles[31][29 - 10].setland(m);
			tiles[31][29 - 11].setland(h);

			tiles[32][29 - 4].setland(h);
			tiles[32][29 - 10].setland(m);
			tiles[32][29 - 11].setland(h);
			
			tiles[33][29 - 10].setland(h);
			
			tiles[34][29 - 5].setland(h);
			tiles[34][29 - 6].setland(h);
			tiles[34][29 - 7].setland(h);
			tiles[34][29 - 8].setland(h);
			
			//make "japan" mountains south-east of big pond
			tiles[22][29 - 23].setland(m);
			tiles[22][29 - 24].setland(m);
	
			tiles[23][29 - 22].setland(m);
			tiles[23][29 - 23].setland(m);
			tiles[23][29 - 24].setland(m);
			
			tiles[24][29 - 21].setland(m);
			tiles[24][29 - 22].setland(m);
			tiles[24][29 - 23].setland(m);
		
			tiles[25][29 - 18].setland(m);
			tiles[25][29 - 19].setland(m);
			tiles[25][29 - 20].setland(m);
			tiles[25][29 - 21].setland(m);
			tiles[25][29 - 22].setland(m);
			tiles[25][29 - 23].setland(m);
			
			tiles[26][29 - 18].setland(m);
			tiles[26][29 - 19].setland(m);
			tiles[26][29 - 20].setland(m);
			tiles[26][29 - 21].setland(m);
			tiles[26][29 - 22].setland(m);
			
			tiles[27][29 - 17].setland(m);
			tiles[27][29 - 18].setland(m);
			tiles[27][29 - 19].setland(m);
		
			tiles[28][29 - 17].setland(m);
			tiles[28][29 - 18].setland(m);
		
			tiles[29][29 - 15].setland(m);
			tiles[29][29 - 16].setland(m);
			tiles[29][29 - 17].setland(m);
			tiles[29][29 - 18].setland(m);
			
			tiles[30][29 - 15].setland(m);
			tiles[30][29 - 16].setland(m);
			tiles[30][29 - 17].setland(m);
			
	
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