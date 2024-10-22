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

public class Rock {

    private Stage stage;
    private int health = 100;
    private Image rectangle;
    private int orientation;
    private World world;
    private Body RockBody;

    public Rock(Stage stage,int orientation, World world) {
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
    public void addRock(float x, float y, float width, float height) {

        BodyDef RockbodyDef = new BodyDef();
        if (orientation == 2) {
            RockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else {
            RockbodyDef.type = BodyDef.BodyType.StaticBody;
        }
        RockbodyDef.position.set(x+width/2, y+height/2);
        RockBody = world.createBody(RockbodyDef);

        PolygonShape RockShape = new PolygonShape();
        RockShape.setAsBox(width/2, height/2);
        RockBody.createFixture(RockShape, 0f); // Static bodies don't need density

        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = RockShape;
        groundFixtureDef.isSensor = false; // Ensure it's not a sensor
        groundFixtureDef.friction = 0.0f; // Adjust friction as necessary
        groundFixtureDef.restitution = 0f; // Bounciness, set to 0 for no bounce

        RockBody.createFixture(groundFixtureDef);

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
        rectangle.setPosition(x, y);
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

                    world.destroyBody(RockBody);

                }
                return true;
            }
        });
    }
    public void updateImagePositionFromBody() {

        Vector2 bodyPosition = RockBody.getPosition();
        float imageX = bodyPosition.x;
        float imageY = bodyPosition.y;

        rectangle.setPosition(imageX-rectangle.getWidth()/2, imageY-rectangle.getHeight()/2);
        rectangle.setRotation(RockBody.getAngle() * MathUtils.radiansToDegrees);
    }


}
