package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Touchable;

public class BuildingActor extends EntityActor{

	
	
	public BuildingActor(Entity e, String actorName) {
		super(e, actorName);
		setTouchable(Touchable.enabled);
		current = this;
		
		entity = e;
		spriteSet(entity.sprite.getX(), entity.sprite.getY());
		setTouchable(Touchable.disabled);
		entityActors.add(current);
		
	}

}