package com.sampleproject.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TNT {

    private transient Stage stage;
    private Image image;
    int x;
    int y;
    private World world;
    private Body body;
    public TNT(Stage stage, int x, int y,World world) {
        this.stage = stage;
        this.x = x;
        this.y = y;
        this.world = world;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public int getX() {
        return x;

    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void addTNT() {
        image = new Image(new Texture("ui/TNT.png"));
        image.setPosition(x, y);
        image.setSize(100,100);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x+50, y+50);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 50);  // 1x1 meter box
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.0f;  // Adjust for mass
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.1f; // Bounciness
        body.createFixture(fixtureDef);

        shape.dispose();
        stage.addActor(image);

    }

}
