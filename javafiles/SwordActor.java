package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

public class SwordActor extends MyActor
{
	static Array<SwordActor> swords = new Array<SwordActor>();
	Sprite sprite;
	SwordActor current;
	public SwordActor(Texture texture, String actorName) 
	{
		super(texture, actorName);
		setTouchable(Touchable.disabled);
		current = this;
		swords.add(current);
		
	}
	
	

}
