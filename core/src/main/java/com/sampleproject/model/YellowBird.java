package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class YellowBird {

    private int health = 100;
    private Stage stage;
    private Image bird;
    public YellowBird(Stage stage) {

        this.stage = stage;

    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void getBirds(int x, int y) {
        Image bird = new Image(new Texture("ui/yellowbird.png"));
        bird.setPosition(x, y);
        stage.addActor(bird);
    }

    public void Attack() {

    }

    public void launch() {

    }



}
