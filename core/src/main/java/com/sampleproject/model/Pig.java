package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static java.lang.Thread.sleep;

public class Pig {
    private World world;
    private Body body;
    private int health;
    private Stage stage;
    private Image pig;
    private Image pigdead;
    public Pig(Stage stage,World world) {
        this.health = health;
        this.stage = stage;
        this.world = world;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public void addPig(int x,int y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.0185208333f, 0.0224895833f);  // 1x1 meter box
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.0f;  // Adjust for mass
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.1f; // Bounciness
        body.createFixture(fixtureDef);

        shape.dispose();

        pig = new Image(new TextureRegion(new Texture("ui/all.png"),22,175,70,85));
        pigdead = new Image(new Texture("ui/deadpig.png"));
        pigdead.setSize(70,85);
        pig.setPosition(x,y);
        pigdead.setPosition(x,y);
        pigdead.setVisible(false);
        stage.addActor(pig);
        stage.addActor(pigdead);
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

    public void updateImagePositionFromBody() {

        Vector2 bodyPosition = body.getPosition();
        float imageX = bodyPosition.x ;
        float imageY = bodyPosition.y;
        pig.setPosition(imageX, imageY);
     //   pig.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

}
