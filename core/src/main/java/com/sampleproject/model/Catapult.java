package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Catapult {

    private Image slingshot;
    private Image slingpart;
    private Stage stage;
    public Catapult(Stage stage) {
        this.stage = stage;
    }

    public void getCatapult() {
        slingshot = new Image(new Texture("angrybirds/slingshot.png"));
        slingpart = new Image(new Texture("angrybirds/slingpart.png"));
        slingshot.setPosition(258,100);
        slingshot.setSize(100,200);
        slingpart.setPosition(258,170);
        slingpart.setSize(100,140);
        stage.addActor(slingshot);
        stage.addActor(slingpart);
    }


}
