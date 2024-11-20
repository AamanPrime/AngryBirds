package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Catapult {

    private Image slingshot;
    private Image slingpart;
    private Stage stage;
    private boolean isEmpty = true;
    public final float PPM = 32f;
    public Catapult(Stage stage) {
        this.stage = stage;
    }
    public void getCatapult() {
        slingshot = new Image(new Texture("angrybirds/slingshot.png"));
        slingpart = new Image(new Texture("angrybirds/slingpart.png"));
        slingshot.setPosition(228/PPM,100/PPM);
        slingshot.setSize(80/PPM,160/PPM);
        slingpart.setPosition(228/PPM,150/PPM);
        slingpart.setSize(80/PPM,120/PPM);
        stage.addActor(slingshot);
        stage.addActor(slingpart);
    }

    public void launchbird() {

    }

    public Image getSlingshot() {
        return slingshot;
    }

    public void setSlingshot(Image slingshot) {
        this.slingshot = slingshot;
    }

    public Image getSlingpart() {
        return slingpart;
    }

    public void setSlingpart(Image slingpart) {
        this.slingpart = slingpart;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
