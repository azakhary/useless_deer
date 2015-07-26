package com.underwater.uselessdeer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by azakhary on 7/26/2015.
 */
public class Deer implements IScript {

    Entity entity;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private final float speed = 100f;

    private float distancePassed = 0;

    @Override
    public void init(Entity entity) {
        this.entity = entity;

        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
        transformComponent.originX = dimensionsComponent.width/2;
        transformComponent.originY = dimensionsComponent.height/2;
    }

    @Override
    public void act(float delta) {
        float diff = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            diff = -speed*delta;
            transformComponent.scaleX = -1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            diff = +speed*delta;
            transformComponent.scaleX = 1f;
        }

        transformComponent.x+=diff;
        distancePassed+=Math.abs(diff);

        // when reached end start from begining
        if(transformComponent.x + dimensionsComponent.width > 334) {
            transformComponent.x = 0;
        }
        // when reached start go to end
        if(transformComponent.x < 0) {
            transformComponent.x = 334 - dimensionsComponent.width;
        }
    }

    @Override
    public void dispose() {

    }

    public float getCenterX() {
        return transformComponent.x+dimensionsComponent.width/2;
    }
    public float getCenterY() {
        return transformComponent.y+dimensionsComponent.height/2;
    }

    public float getDistancePassed() {
        return distancePassed;
    }

}
