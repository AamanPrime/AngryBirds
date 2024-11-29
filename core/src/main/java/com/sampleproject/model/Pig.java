package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.io.Serializable;
import java.util.ArrayList;


public class Pig implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID
    private transient World world;
    private transient Body body;
    private int health;
    private transient Stage stage;
    private transient Image pig;
    private String type;
    private boolean isCandestroy;
    transient BodyDef bodyDef;
    transient FixtureDef fixtureDef;
    private Vector2 currentPosition = new Vector2();

    public Pig() {

    }
    public final float PPM = 32f;
    public Pig(Stage stage, World world,String type) {
        this.health = 100;
        this.stage = stage;
        this.world = world;
        this.type = type;

    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Vector2 currentPosition) {
        this.currentPosition = currentPosition;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public void setFixtureDef(FixtureDef fixtureDef) {
        this.fixtureDef = fixtureDef;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public boolean isCandestroy() {
        return isCandestroy;
    }

    public void setCandestroy(boolean candestroy) {
        isCandestroy = candestroy;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Image getPig() {
        return pig;
    }

    public void setPig(Image pig) {
        this.pig = pig;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCanDestroy() {
        return isCandestroy;
    }

    public void setCanDestroy(boolean candestroy) {
        isCandestroy = candestroy;
    }

    public float getPPM() {
        return PPM;
    }

    public void addPig(int x, int y, ArrayList<Pig> pigs) {
        x /= PPM;
        y /= PPM;
        if (type.equals("king")) {
            pig = new Image(new TextureRegion(new Texture("ui/kings.png")));
            pig.setSize(70/PPM,85/PPM);
        }
        else if (type.equals("helmet")) {
            pig = new Image(new TextureRegion(new Texture("ui/helmetpig.png")));
            pig.setSize(70/PPM,85/PPM);
        }
        else {
            pig = new Image(new TextureRegion(new Texture("ui/normalpig.png")));
            pig.setSize(70/PPM,85/PPM);
        }
        pig.setPosition(x,y);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x+(35f/PPM), y+ (42.5f/PPM));
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(30/PPM);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.3f;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        shape.dispose();

        stage.addActor(pig);
        body.setUserData(this);
        pigs.add(this);
    }

    public void addDamage() {
        pig.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pig.remove();
                return true;
            }
        });
    }

    public void handleCollisionWithPig() {
        health -= 100;
        if (health <= 0) {
            pig.remove();
            this.isCandestroy = true;
        }
    }

    public void updateImagePositionFromBody() {

        Vector2 bodyPosition = body.getPosition();
        float imageX = bodyPosition.x - pig.getWidth() / 2;
        float imageY = bodyPosition.y - pig.getHeight() / 2.6f;
        pig.setPosition(imageX, imageY);
        setCurrentPosition(new Vector2(imageX, imageY));
    }

}
