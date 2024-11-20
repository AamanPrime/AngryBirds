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

import java.util.ArrayList;
import java.util.Objects;

public class Block {
    private Stage stage;
    private int health = 182;
    private Image rectangle;
    private int orientation;
    private World world;
    private Body blockBody;
    private boolean candestroy = false;
    private boolean birdtouched = false;
    private Vector2 initialPosition = new Vector2();
    public final float PPM = 32f;
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isBirdtouched() {
        return birdtouched;
    }

    public void setBirdtouched(boolean birdtouched) {
        this.birdtouched = birdtouched;
    }

    public Image getRectangle() {
        return rectangle;
    }

    public void setRectangle(Image rectangle) {
        this.rectangle = rectangle;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(Body blockBody) {
        this.blockBody = blockBody;
    }

    public boolean isCandestroy() {
        return candestroy;
    }

    public void setCandestroy(boolean candestroy) {
        this.candestroy = candestroy;
    }


    public Block(Stage stage,int orientation, World world,ArrayList<Block> allBlock) {
        this.stage = stage;
        this.orientation = orientation;
        this.world = world;
        allBlock.add(this);
    }

    public boolean getCanDestroy() {
        return candestroy;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //0 -> Horizontal
    //1 -> Vertical


    public Vector2 getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Vector2 initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void addBlock(float x, float y, float width, float height) {
        x = x/PPM;
        y = y/PPM;
        width = width/PPM;
        height = height/PPM;

        BodyDef blockbodyDef = new BodyDef();
        if (orientation == 2) {
            blockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else {
            blockbodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        blockbodyDef.position.set(x+width/2, y+height/2);
        blockBody = world.createBody(blockbodyDef);

        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(width/2, height/2);

        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = blockShape;
        groundFixtureDef.density = 2f;

        groundFixtureDef.friction = 2f;
        groundFixtureDef.restitution = 0f;

        blockBody.createFixture(groundFixtureDef);
        blockBody.setUserData(this);
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
        blockBody.setUserData(this);
        this.setInitialPosition(new Vector2(x, y));
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
                    candestroy = true;
                }
                else if (health <= 100) {
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
        health-=81;
        System.out.println(health);
        if (health <= 0) {
            rectangle.remove();
            candestroy = true;
        }
        else if (health <= 100) {
            if (orientation == 0) {
                rectangle.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("ui/Block.png"), 48, 90, 190, 22)));
            }
            else {
                rectangle.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("ui/verticalblockOld.png"))));
            }
        }
    }
    public void updateImagePositionFromBody() {

        rectangle.setOrigin(rectangle.getWidth() / 2, rectangle.getHeight() / 2);


        Vector2 bodyPosition = blockBody.getPosition();
        float imageX = bodyPosition.x - rectangle.getWidth() / 2;
        float imageY = bodyPosition.y - rectangle.getHeight() / 2;
        rectangle.setPosition(imageX, imageY);


        rectangle.setRotation(blockBody.getAngle() * MathUtils.radiansToDegrees);

    }

}
