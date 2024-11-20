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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level3 implements Screen, InputProcessor {

    private World world;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture ground;
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
    private Pig Pig1;
    private Pig Pig2;
    private Block block1;
    private Block block2;
    private Block block3;
    private Block block7;
    private Block block10;
    private Block block11;
    private Block block12;
    private Block block13;
    private Block block14;
    private Block block15;
    private Block block16;
    private Block block17;
    private Block block18;
    private Block block19;
    private Block block20;
    private Block block21;
    private Block block22;
    private Block block23;
    private Block block24;
    private Rock rock1;
    private Rock rock2;
    private Rock rock3;
    private Rock rock4;
    private Rock rock5;
    private Rock rock6;
    private Rock rock7;
    private Rock rock8;
    private Rock rock9;
    private Rock rock10;
    private Rock rock11;
    private Rock rock12;
    private Rock rock13;
    private Rock rock14;
    private Rock rock20;
    private Rock rock21;
    public Image pause;
//    private RedBird redBird1;
//    private YellowBird yellowBird;
//    private BlueBird blueBird;
//    private BlackBird blackBird;
    private Pig Pig3;
    private Pig Pig4;
    private Pig Pig5;
    private Pig Pig6;
    private Pig Pig7;
    private Pig pig8;
    private Main main;
    private ArrayList<Block> allBlock = new ArrayList<>();
    private ArrayList<Rock> allRock = new ArrayList<>();
    public Music backgroundMusic;
    private Queue<BirdInterface> allBirds = new LinkedList<>();
    public Level3(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        stage = new Stage();
        camera = new OrthographicCamera(16,9);
        camera.position.set(0, 0, 0);
        camera.update();
        viewport = new FitViewport(1920, 1000, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        stage2 = new Stage(viewport, batch);


        ground = new Texture("angrybirds/ground.png");
        background = new Texture("ui/level3bg.png");
        pause = new Image(new Texture("ui/pause.png"));
        pause.setPosition(20, 900);
        pause.setScale(0.5f);
        stage.addActor(pause);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
//        backgroundMusic.play();

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
        block1 = new Block(stage,2,world,allBlock);
        block1.addBlock(700,105,1200,25);
        block1.addDamage();


//        Pig1 = new Pig(stage,world,"king");
//        Pig1.addPig(500,800,al);




//        redBird1 = new RedBird(stage,world,viewport);
//        redBird1.getBirds(190,105,allBirds);
//        redBird1.launch();
//        blueBird = new BlueBird(stage,world,viewport);
//        blueBird.getBirds(150,105,allBirds);
//        yellowBird = new YellowBird(stage,world,viewport);
//        yellowBird.getBirds(100,105,allBirds);
//        blackBird = new BlackBird(stage,world,viewport);
//        blackBird.getBirds(50,105,allBirds);
        Catapult catapult = new Catapult(stage);
        catapult.getCatapult();

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
//        inputMultiplexer.addProcessor(this);

        Gdx.input.setInputProcessor(inputMultiplexer);

        debugRenderer = new Box2DDebugRenderer(
            true, /* draw bodies */
            false, /* don't draw joints */
            true, /* draw aabbs */
            true, /* draw inactive bodies */
            false, /* don't draw velocities */
            true /* draw contacts */);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        camera.update();

        debugRenderer.render(world, camera.combined);

//        Pig1.updateImagePositionFromBody();
//        redBird1.updateImagePositionFromBody();
        stage.act();
        stage.draw();


        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            main.setScreen(new Won(main,3,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            main.setScreen(new Loss(main,3));
        }

    }

    public Level3 getthis() {
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
        float angleStep = sweepAngle / 50;  // Divide the sweep angle by the number of segments


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
