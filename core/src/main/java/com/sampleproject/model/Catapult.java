package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.io.Serializable;


public class Catapult implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID
    private transient Image slingshot;
    private transient Image slingpart;
    private transient Stage stage;
    private boolean isEmpty = true;
    public final float PPM = 32f;
    public Catapult() {

    }

    public Catapult(Stage stage) {
        this.stage = stage;
    }
    public void getCatapult(String s) {
        switch (s) {
            case "rock":
                slingshot = new Image(new Texture("ui/rockPult.png"));
                slingshot.setPosition(228/PPM,90/PPM);
                slingshot.setSize(80/PPM,160/PPM);
                stage.addActor(slingshot);
                break;
            case "golden":
                slingshot = new Image(new Texture("ui/goldenPult.png"));
                slingshot.setPosition(228/PPM,90/PPM);
                slingshot.setSize(80/PPM,160/PPM);
                stage.addActor(slingshot);
                break;
            case "leaf":
                slingshot = new Image(new Texture("ui/leafPult.png"));
                slingshot.setPosition(228/PPM,90/PPM);
                slingshot.setSize(80/PPM,160/PPM);
                stage.addActor(slingshot);
                break;
            default:
                slingshot = new Image(new Texture("ui/slingshot.png"));
                slingpart = new Image(new Texture("ui/slingpart.png"));
                slingshot.setPosition(228/PPM,100/PPM);
                slingshot.setSize(80/PPM,160/PPM);
                slingpart.setPosition(228/PPM,150/PPM);
                slingpart.setSize(80/PPM,120/PPM);
                stage.addActor(slingshot);
                stage.addActor(slingpart);
                break;
        }

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
