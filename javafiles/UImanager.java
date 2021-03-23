package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

public class UImanager 
{
	public static  ImageButtonStyle configbutton(Skin skin, String button)
	{
	    
		ImageButtonStyle name = new ImageButtonStyle();
		name.up = skin.getDrawable(button);
		name.over = skin.getDrawable(button);
		name.down = skin.getDrawable(button);
		name.pressedOffsetX = 1;
		name.pressedOffsetY = -1;
		return name;
	}
}
