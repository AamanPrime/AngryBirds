package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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
    private World world;
    private Body blockBody;

    public Block(Stage stage,int orientation, World world) {
        this.stage = stage;
        this.orientation = orientation;
        this.world = world;
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

        BodyDef blockbodyDef = new BodyDef();
        if (orientation == 2) {
            blockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else {
            blockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        blockbodyDef.position.set(x+width/2, y+height/2);
        blockBody = world.createBody(blockbodyDef);
        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(width/2, height/2);
        blockBody.createFixture(blockShape, 0f); // Static bodies don't need density
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = blockShape;
        groundFixtureDef.isSensor = false; // Ensure it's not a sensor
        groundFixtureDef.friction = 0.0f; // Adjust friction as necessary
        groundFixtureDef.restitution = 0f; // Bounciness, set to 0 for no bounce

        blockBody.createFixture(groundFixtureDef);

        blockShape.dispose();

        if (orientation == 0) {
            if (health >= 20) {
                addNewHorizontalBlock(x,y,width,height);
            }
            else {
                addOldHorizontalBlock(x,y,width,height);
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
        rectangle = new Image(new Texture("ui/horizontalblockNew.png"));
        rectangle.setSize(width, height);
        rectangle.setPosition(x, y);
        stage.addActor(rectangle);

    }

    public void addOldHorizontalBlock(float x, float y, float width, float height) {
        rectangle = new Image(new Texture("ui/horizontalblockOld.png"));
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

                    world.destroyBody(blockBody);

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
    public void updateImagePositionFromBody() {

        Vector2 bodyPosition = blockBody.getPosition();
        float imageX = bodyPosition.x;
        float imageY = bodyPosition.y;

        rectangle.setPosition(imageX-rectangle.getWidth()/2, imageY-rectangle.getHeight()/2);
        rectangle.setRotation(blockBody.getAngle() * MathUtils.radiansToDegrees);
    }


}
