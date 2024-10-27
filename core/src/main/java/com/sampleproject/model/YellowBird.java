package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class YellowBird implements BirdInterface {

    private int health = 100;
    private Stage stage;
    private Image bird;
    public YellowBird(Stage stage) {

        this.stage = stage;

    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void setHealth(int health) {
        this.health = health;
    }
    @Override
    public void getBirds(int x, int y) {
        Image bird = new Image(new Texture("ui/yellowbird.png"));
        bird.setPosition(x, y);
        stage.addActor(bird);
    }

    @Override
    public void Attack() {

    }

    @Override
    public void launch() {

    }



}
