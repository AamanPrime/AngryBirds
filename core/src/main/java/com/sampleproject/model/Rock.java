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

public class Rock {

    private Stage stage;
    private int health = 100;
    private Image rectangle;
    private int orientation;
    private World world;
    private Body RockBody;
    private boolean candestroy;
    public Rock(Stage stage, int orientation, World world, ArrayList<Rock> allRocks) {
        this.stage = stage;
        this.orientation = orientation;
        this.world = world;
        allRocks.add(this);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public Body getRockBody() {
        return RockBody;
    }

    public void setRockBody(Body rockBody) {
        RockBody = rockBody;
    }

    public boolean isCandestroy() {
        return candestroy;
    }

    public void setCandestroy(boolean candestroy) {
        this.candestroy = candestroy;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //0 -> Horizontal
    //1 -> Vertical
    public void addRock(float x, float y, float width, float height) {

        BodyDef RockbodyDef = new BodyDef();
        if (orientation == 2) {
            RockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else {
            RockbodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        RockbodyDef.position.set(x+width/2, y+height/2);
        RockBody = world.createBody(RockbodyDef);

        PolygonShape RockShape = new PolygonShape();
        RockShape.setAsBox(width/2, height/2);
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = RockShape;
        groundFixtureDef.friction = 2f; // Adjust friction as necessary
        groundFixtureDef.density = 1f;
        groundFixtureDef.restitution = 0f; // Bounciness, set to 0 for no bounce

        RockBody.createFixture(groundFixtureDef);
        RockBody.setUserData(this);
        RockShape.dispose();

        if (orientation == 0) {
                addNewHorizontalRock(x,y,width,height);
            }

        else {
                addNewVerticalRock(x,y,width,height);
        }
    }

    public void addNewVerticalRock(float x, float y, float width, float height) {
        rectangle = new Image(new Texture("ui/verticalstone.png"));
        rectangle.setPosition(x, y+9);
        rectangle.setSize(width, height);
        stage.addActor(rectangle);
    }



    public void addNewHorizontalRock(float x, float y, float width, float height) {
        rectangle = new Image(new Texture("ui/horizontalstone.png"));
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
                return true;
            }
        });
        health-=81;
        System.out.println(health);
        if (health <= 0) {
            rectangle.remove();
            candestroy = true;
        }

    }
    public void updateImagePositionFromBody() {
        rectangle.setOrigin(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
        Vector2 bodyPosition = RockBody.getPosition();
        float imageX = bodyPosition.x - rectangle.getWidth() / 2;
        float imageY = bodyPosition.y - rectangle.getHeight() / 2;
        rectangle.setPosition(imageX, imageY);
        rectangle.setRotation(RockBody.getAngle() * MathUtils.radiansToDegrees);

    }


}
