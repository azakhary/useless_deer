package com.underwater.uselessdeer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.label.LabelComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class UselessDeer extends ApplicationAdapter {

    private SceneLoader sl;
    private Viewport viewport;

    private Deer deer;
    private ItemWrapper rootItem;

    private UIStage uiStage;

	@Override
	public void create () {
        viewport = new FitViewport(192, 120); // this should be the size of camera in WORLD units. make sure you check that in editor first.
        sl = new SceneLoader(); // default scene loader loads all resources from default RM as usual.
        sl.loadScene("MainScene", viewport); // loading scene as usual

        //let's get the deer and attach a script to it
        deer = new Deer();
        rootItem = new ItemWrapper(sl.getRoot());
        rootItem.getChild("deer").addScript(deer);

        // ui with actors and tables and shit
        uiStage = new UIStage(sl);
    }

    public void act() {
        OrthographicCamera camera = (OrthographicCamera)viewport.getCamera();
        camera.position.x = deer.getCenterX();

        // camera bounds
        if(camera.position.x < viewport.getWorldWidth()/2) camera.position.x = viewport.getWorldWidth()/2;
        if(camera.position.x > 334f - viewport.getWorldWidth()/2) camera.position.x = 334f - viewport.getWorldWidth()/2;


        uiStage.setDistanceValue((int)deer.getDistancePassed());
    }

	@Override
	public void render () {
        act();
        Gdx.gl.glClearColor(36/225f, 20/225f, 116/225f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sl.getEngine().update(Gdx.graphics.getDeltaTime()); // getting the ashley engine and updating it (it will render things with it's own render system)

        uiStage.act();
        uiStage.draw();
	}
}
