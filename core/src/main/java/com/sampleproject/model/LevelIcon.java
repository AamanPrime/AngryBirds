package com.sampleproject.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LevelIcon {


    private Stage stage;
    public LevelIcon(Stage stage) {
        this.stage = stage;
    }

    public Image addLevel(int x, int y, int stars, int whichlevel) {
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/b.fnt"));
        font.setColor(0, 0, 0, 1);
        font.getData().setScale(1.5f);
        Image image = new Image(new Texture("ui/level.png"));
        Image star1 = new Image(new Texture("ui/levelstar.png"));
        Image star2 = new Image(new Texture("ui/levelstar.png"));
        Image star3 = new Image(new Texture("ui/levelstar.png"));
        image.setPosition(x, y);
        image.setScale(0.3f);
        star1.setScale(0.3f);star1.setPosition(x+47, y+3);star1.setVisible(false);
        star2.setScale(0.3f);star2.setPosition(x+47*2+6, y+3);star2.setVisible(false);
        star3.setScale(0.3f);star3.setPosition(x+47*3+6, y+3);star3.setVisible(false);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        Label level = new Label(Integer.toString(whichlevel), labelStyle);
        level.setPosition(x+110, y+130);
        if (stars == 1) {
            star1.setVisible(true);
        }
        if (stars == 2) {
            star1.setVisible(true);
            star2.setVisible(true);

        }
        if (stars == 3) {
            star1.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
        }




        image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                image.setScale(0.31f);
                star1.setScale(0.31f);
                star2.setScale(0.31f);
                star3.setScale(0.31f);
                level.setPosition(level.getX()+3, level.getY()+3);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                image.setScale(0.3f);
                star1.setScale(0.3f);
                star2.setScale(0.3f);
                star3.setScale(0.3f);
                level.setPosition(level.getX()-3, level.getY()-3);
            }
        });

        stage.addActor(image);
        stage.addActor(level);
        stage.addActor(star1);
        stage.addActor(star2);
        stage.addActor(star3);
        return image;
    }

}
