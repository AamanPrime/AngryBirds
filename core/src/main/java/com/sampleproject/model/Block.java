package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Block {

    private Stage stage;
    private int health = 100;
    private Image rectangle;
    private int orientation;
    public Block(Stage stage,int orientation) {
        this.stage = stage;
        this.orientation = orientation;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //0 -> Horizontal
    //1 -> Vertical
    public void addBlock(float x, float y, float width, float height) {
        if (orientation == 0) {
            if (health >= 20) {
                addNewHorizontalBlock(x,y,width,height);
            }
            else {
                addOldVerticalBlock(x,y,width,height);
            }
        }
        else {
            if (health >= 20) {
                addNewVerticalBlock(x,y,width,height);
            }
            else {
                addOldVerticalBlock(x,y,width,height);
            }
        }
    }

    public void addNewVerticalBlock(float x, float y, float width, float height) {
        rectangle = new Image(new Texture("ui/verticalblockNew.png"));
        rectangle.setPosition(x, y);
        rectangle.setSize(width, height);
        stage.addActor(rectangle);
    }

    public void addOldVerticalBlock(float x, float y, float width, float height) {
        rectangle = new Image(new Texture("ui/verticalblockOld.png"));
        rectangle.setPosition(x, y);
        rectangle.setSize(width, height);
        stage.addActor(rectangle);
    }


    public void addNewHorizontalBlock(float x, float y, float width, float height) {
        rectangle = new Image(new TextureRegion(new Texture("ui/Block.png"), 48, 133, 190, 22));
        rectangle.setSize(width, height);
        rectangle.setPosition(x, y);
        stage.addActor(rectangle);

    }

    public void addBrokenHorizontalBlock(float x, float y, float width, float height) {
        rectangle = new Image(new TextureRegion(new Texture("ui/Block.png"), 48, 90, 190, 22));
        rectangle.setSize(width, height);
        rectangle.setPosition(x, y);
        stage.addActor(rectangle);

    }

    public void addDamage() {
        rectangle.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                health-=81;
                System.out.println(health);
                if (health <= 0) {
                    rectangle.remove();
                }
                else {
                    if (orientation == 0) {
                        rectangle.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("ui/Block.png"), 48, 90, 190, 22)));
                    }
                    else {
                        rectangle.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("ui/verticalblockOld.png"))));
                    }
                }
                return true;
            }
        });
    }


}
