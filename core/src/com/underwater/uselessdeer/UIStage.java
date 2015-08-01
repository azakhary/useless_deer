package com.underwater.uselessdeer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.scene2d.ButtonClickListener;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;

/**
 * Created by azakhary on 7/26/2015.
 */
public class UIStage extends Stage {

    private CompositeActor distancePane;

    public UIStage(SceneLoader sceneLoader) {
        super(new StretchViewport(1920, 1200));

        Gdx.input.setInputProcessor(this);

        CompositeItemVO distancePaneVo = sceneLoader.loadVoFromLibrary("distancePane");
        distancePane = new CompositeActor(distancePaneVo, sceneLoader.getRm());

        Table mainUITable = new Table();
        mainUITable.setFillParent(true);

        Table topUI = new Table();
        topUI.add(distancePane).padTop(10);

        Table bottomUI = new Table();

        mainUITable.add(topUI).expandX();
        mainUITable.row();
        mainUITable.add(bottomUI).expand();

        addActor(mainUITable);


        CompositeActor button = new CompositeActor(sceneLoader.loadVoFromLibrary("button"), sceneLoader.getRm());
        button.setX(getWidth()-button.getWidth());
        button.setY(getHeight()-button.getHeight());
        button.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                // Do some stuff
            }
        });
        addActor(button);


    }

    public void setDistanceValue(int distanceValue) {
        Label valueLbl = (Label) distancePane.getItem("value");
        valueLbl.setText(distanceValue+"");
    }
}
