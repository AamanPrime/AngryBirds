package com.sampleproject.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.sampleproject.Main;
import com.sampleproject.model.*;

public class Level1 implements Screen, InputProcessor {

    private World world;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture ground;
    private Texture slingshot;
    private Texture slingpart;
    private Texture background;
    private Texture blocks;
    private Stage stage;
    private Stage stage2;
    private ShapeRenderer shapeRenderer;
    private Vector2 ellipseCenter;
    private float width, height;
    private boolean isDragging = false;
    Box2DDebugRenderer debugRenderer;
    public InputMultiplexer inputMultiplexer;
    public Music backgroundMusic;
    Pig Pig1;
    Pig Pig2;
    Block block1;
    Block block2;
    Block block3;
    Block block4;
    Block block5;
    Block block6;
    Block block7;
    Block block8;
    Block block9;
    Block block10;
    Block block11;
    Block block12;

    private boolean stats;
    public Image pause;

    RedBird redBird1;
    YellowBird yellowBird;
    BlueBird blueBird;
    BlackBird blackBird;
    private Main main;

    public Level1(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1000, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        stage2 = new Stage(viewport, batch);
        ground = new Texture("angrybirds/ground.png");
        slingshot = new Texture("angrybirds/slingshot.png");
        slingpart = new Texture("angrybirds/slingpart.png");
        background = new Texture("ui/level1bg.jpeg");
        pause = new Image(new Texture("ui/pause.png"));
        pause.setPosition(20, 900);
        pause.setScale(0.5f);
        stage.addActor(pause);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();

        shapeRenderer = new ShapeRenderer();
        ellipseCenter = new Vector2(250,190);
        width = 100;
        height = 1;


        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1920, 100);
        groundBody.createFixture(groundShape, 0.0f);
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundShape;
        groundFixtureDef.isSensor = false;
        groundFixtureDef.friction = 0.5f;
        groundFixtureDef.restitution = 0f;
        groundBody.createFixture(groundFixtureDef);
        groundShape.dispose();


        ///ground block
        block1 = new Block(stage,2,world);
        block1.addBlock(1000,105,800,25);


        block2 = new Block(stage,1,world);
        block2.addBlock(1202,128,25,101);
        block2.addDamage();
        block3 = new Block(stage,1,world);
        block3.addBlock(1540,128,25,101);
        block3.addDamage();
        block4 = new Block(stage,0,world);
        block4.addBlock(1202,225,1540-1202+25,28);
        block4.addDamage();
        Pig1 = new Pig(stage,world,"normal");
        Pig1.addPig(1350,270);
        Pig1.addDamage();

        block5 = new Block(stage,1,world);
        block5.addBlock(1202,225+28,25,152);
        block5.addDamage();
        block6 = new Block(stage,1,world);
        block6.addBlock(1540,225+28,25,152);
        block6.addDamage();
        block7 = new Block(stage,0,world);
        block7.addBlock(1202,225+28+152,1540-1202+25,28);
        block7.addDamage();
        block8 = new Block(stage,1,world);
        block8.addBlock(1202,225+28+152+25,25,152);
        block8.addDamage();
        block9 = new Block(stage,1,world);
        block9.addBlock(1540,225+28+152+25,25,152);
        block9.addDamage();
        block10 = new Block(stage,1,world);
        block10.addBlock(1202+100,225+28+152+25,25,152);
        block10.addDamage();
        block11 = new Block(stage,1,world);
        block11.addBlock(1540-100,225+28+152+25,25,152);
        block11.addDamage();
        block12 = new Block(stage,0,world);
        block12.addBlock(1202,225+28+152+25+152,1540-1202+25,28);
        block12.addDamage();



        Pig2 = new Pig(stage,world,"king");
        Pig2.addPig(1350,225+28+152+25);
        Pig2.addDamage();



        stage.addActor(pause);
        redBird1 = new RedBird(stage);
        redBird1.getBirds(190,105);
        blueBird = new BlueBird(stage);
        blueBird.getBirds(150,105);
        yellowBird = new YellowBird(stage);
        yellowBird.getBirds(100,105);
        blackBird = new BlackBird(stage);
        blackBird.getBirds(50,105);
        Catapult catapult = new Catapult(stage);
        catapult.getCatapult();
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        pause.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                inputMultiplexer.removeProcessor(stage);
                removethis();
                inputMultiplexer.addProcessor(stage2);
                backgroundMusic.stop();
                pause.setVisible(false);
                PauseMenu p = new PauseMenu(stage,main,getthis(),stage2);
                p.show();
                return true;
            }
        });

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1f, 3, 3);

        batch.begin();
        batch.draw(background, 0, 0,1920,1200);
        batch.draw(ground, 0, 0,1920,136);
//        batch.draw(slingshot, 258,100,100,200);

        batch.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        drawHalfEllipse(318,270,width,height,90,180);

        shapeRenderer.end();

        batch.begin();
        Pig1.updateImagePositionFromBody();
        Pig2.updateImagePositionFromBody();
        block1.updateImagePositionFromBody();
        block2.updateImagePositionFromBody();
        block3.updateImagePositionFromBody();
        block4.updateImagePositionFromBody();
        block5.updateImagePositionFromBody();
        block6.updateImagePositionFromBody();
        block7.updateImagePositionFromBody();

        batch.end();

        stage.act();
        stage.draw();

        stage2.act();
        stage2.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            main.setScreen(new Won(main,1,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            main.setScreen(new Loss(main,1));
        }

    }

    private void showMenu() {


    }

    public Level1 getthis() {
        return this;
    }

    @Override
    public void resize(int i, int i1) {

    }

    public void removethis() {
        inputMultiplexer.removeProcessor(this);
    }

    public void addthis() {
        inputMultiplexer.addProcessor(this);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        width = 100;
        height = 1;
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Math.abs((screenX - 275)) <= 45) {
            System.out.println("touching");
            isDragging = true;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

            if (isDragging) {
                float deltaX = Math.abs(screenX - ellipseCenter.x);
                float deltaY = Math.abs(screenY - ellipseCenter.y);
                width = deltaX + 50;
                height = 20;

                System.out.println("Dragged: " + deltaX + " " + deltaY);
            }



        return true;
    }
    private void drawHalfEllipse(float cx, float cy, float width, float height, float startAngle, float sweepAngle) {
        float angleStep = sweepAngle / 50;


        for (float angle = startAngle; angle <= startAngle + sweepAngle; angle += angleStep) {
            float rad = (float) Math.toRadians(angle);
            float x = cx + (width / 2f) * (float) Math.cos(rad);
            float y = cy + (height / 2f) * (float) Math.sin(rad);


            if (angle > startAngle) {
                float prevRad = (float) Math.toRadians(angle - angleStep);
                float prevX = cx + (width / 2f) * (float) Math.cos(prevRad);
                float prevY = cy + (height / 2f) * (float) Math.sin(prevRad);

                shapeRenderer.line(prevX, prevY, x, y);


            }
        }
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
