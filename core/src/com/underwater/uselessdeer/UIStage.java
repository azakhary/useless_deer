package com.underwater.uselessdeer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.utils.CompositeActor;

/**
 * Created by azakhary on 7/26/2015.
 */
public class UIStage extends Stage {

    private CompositeActor distancePane;

    public UIStage(SceneLoader sceneLoader) {
        super(new StretchViewport(1920, 1200));

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

    }

    public void setDistanceValue(int distanceValue) {
        Label valueLbl = (Label) distancePane.getItem("value");
        valueLbl.setText(distanceValue+"");
    }
}
