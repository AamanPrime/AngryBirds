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

public class Level2 implements Screen, InputProcessor {

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
    Pig Pig1;
    Pig Pig2;
    Block block1;
    Block block2;
    Block block3;
    Block block4;
    Rock rock5;
    Rock rock6;
    Block block7;
    Rock rock8;
    Rock rock9;
    Block block10;
    Block block11;
    Block block12;

    private boolean stats;
    public Image pause;


    RedBird redBird1;
    YellowBird yellowBird;
    BlueBird blueBird;
    BlackBird blackBird;
    private Pig Pig3;
    private Pig Pig4;
    private Main main;
    public Music backgroundMusic;
    public Level2(Main main) {
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
        background = new Texture("ui/level2bg.png");
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
        debugRenderer = new Box2DDebugRenderer();

        ///ground block
        block1 = new Block(stage,2,world);
        block1.addBlock(1000,105,800,25);


        block2 = new Block(stage,1,world);
        block2.addBlock(1202,128,25,101+152+25);
        block3 = new Block(stage,1,world);
        block3.addBlock(1540,128,25,101+152+25);


        rock5 = new Rock(stage,1,world);
        rock5.addRock(1102,132,25,3*152 + 100);
        rock6 = new Rock(stage,1,world);
        rock6.addRock(1640,132,25,3*152+100);

        block7 = new Block(stage,0,world);
        block7.addBlock(1202,225+28+152,1540-1202+25,28);

        rock8 = new Rock(stage,1,world);
        rock8.addRock(1202,225+28+152+25,25,152);
        rock9 = new Rock(stage,1,world);
        rock9.addRock(1540,225+28+152+25,25,152);

        block10 = new Block(stage,1,world);
        block10.addBlock(1202+100,225+28+152+25,25,152);
        block11 = new Block(stage,1,world);
        block11.addBlock(1540-100,225+28+152+25,25,152);

        block12 = new Block(stage,0,world);
        block12.addBlock(1202,225+28+152+25+152,1540-1202+25,28);


        block1.addDamage();
        block2.addDamage();
        block3.addDamage();


        rock5.addDamage();
        rock6.addDamage();
        block7.addDamage();
        rock8.addDamage();
        rock9.addDamage();
        block10.addDamage();
        block11.addDamage();
        block12.addDamage();
        TNT tnt1 = new TNT(stage,1350,132,world);
        tnt1.addTNT();
        Pig1 = new Pig(stage,world,"normal");
        Pig1.addPig(1370,270);
        Pig1.addDamage();
        Pig3 = new Pig(stage,world,"helmet");
        Pig3.addPig(1470,132);
        Pig3.addDamage();
        Pig4 = new Pig(stage,world,"helmet");
        Pig4.addPig(1270,132);
        Pig4.addDamage();

        Pig2 = new Pig(stage,world,"king");
        Pig2.addPig(1350,225+28+152+25);
        Pig2.addDamage();



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

        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

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
        rock5.updateImagePositionFromBody();
        rock6.updateImagePositionFromBody();
        block7.updateImagePositionFromBody();
        rock8.updateImagePositionFromBody();
        rock9.updateImagePositionFromBody();
        block10.updateImagePositionFromBody();
        block11.updateImagePositionFromBody();
        block12.updateImagePositionFromBody();

        batch.end();

        stage.act();
        stage.draw();

        stage2.act();
        stage2.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            main.setScreen(new Won(main,2,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            main.setScreen(new Loss(main,2));
        }

    }

    public Level2 getthis() {
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
