package com.sampleproject.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.sampleproject.Main;
import com.sampleproject.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level1 implements Screen, InputProcessor {
    private World world;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture ground;
    private Texture background;
    private Stage stage;
    private Stage stage2;
    public ShapeRenderer shapeRenderer;
    Box2DDebugRenderer debugRenderer;
    public InputMultiplexer inputMultiplexer;
    public Music backgroundMusic;
    private float waitTime = 0f;
    private final float WAIT_DURATION = 5f;
    private boolean isWaiting = false;

    private BitmapFont font;
    private Label scoreLabel;
    Pig pig1;
    Pig pig2;
    Pig pig3;
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
    private Catapult catapult;
    public Image pause;
    RedBird redBird;
    YellowBird yellowBird;
    BlueBird blueBird;
//    BlackBird blackBird;
    BlueBird b1;
    BlueBird b2;
    private Main main;

    private ArrayList<Block> allBlock = new ArrayList<>();
    private ArrayList<Rock> allRock = new ArrayList<>();
    private ArrayList<Pig> allPig = new ArrayList<>();
    private Queue<BirdInterface> allBirds = new LinkedList<>();
    public Level1(Main main) {
        this.main = main;
    }
    public final float PPM = 32f;
    public final float WORLD_WIDTH_METERS = 1920f / PPM;
    public final float WORLD_HEIGHT_METERS = 1000f / PPM;
    private int score;
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new BirdBlockContactListener());
        stage = new Stage();
        camera = new OrthographicCamera(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS);
        camera.update();
        camera.position.set(0,0, 0);
        viewport = new FitViewport(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        stage2 = new Stage(viewport, batch);
        ground = new Texture("angrybirds/ground.png");
        background = new Texture("ui/level1bg.jpeg");
        pause = new Image(new Texture("ui/pause.png"));
        pause.setPosition(32/PPM, 900/PPM);
        pause.setScale(0.5f/PPM);
        stage.addActor(pause);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
//        backgroundMusic.play();

        shapeRenderer = new ShapeRenderer();


        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1920 / PPM, 100/PPM);
        groundBody.createFixture(groundShape, 0.0f);
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundShape;
        groundFixtureDef.isSensor = false;
        groundFixtureDef.friction = 0.5f;
        groundFixtureDef.restitution = 0f;
        groundBody.createFixture(groundFixtureDef);
        groundShape.dispose();

        ///ground block
        block1 = new Block(stage,2,world, allBlock);
        block1.addBlock(1000,105,800,25);

        block2 = new Block(stage,1,world, allBlock);
        block2.addBlock(1202,128,25,101);
        block2.addDamage();
        block3 = new Block(stage,1,world, allBlock);
        block3.addBlock(1540,128,25,101);
        block3.addDamage();
        block4 = new Block(stage,0,world, allBlock);
        block4.addBlock(1202,225,1540-1202+25,28);
        block4.addDamage();
        pig1 = new Pig(stage,world,"normal");
        pig1.addPig(1350,270,allPig);
        pig1.addDamage();

        block5 = new Block(stage,1,world, allBlock);
        block5.addBlock(1202,225+28,25,152);
        block5.addDamage();
        block6 = new Block(stage,1,world, allBlock);
        block6.addBlock(1540,225+28,25,152);
        block6.addDamage();
        block7 = new Block(stage,0,world, allBlock);
        block7.addBlock(1202,225+28+152,1540-1202+25,28);
        block7.addDamage();
        block8 = new Block(stage,1,world, allBlock);
        block8.addBlock(1202,225+28+152+25,25,152);
        block8.addDamage();
        block9 = new Block(stage,1,world, allBlock);
        block9.addBlock(1540,225+28+152+25,25,152);
        block9.addDamage();
        block10 = new Block(stage,1,world, allBlock);
        block10.addBlock(1202+100,225+28+152+25,25,152);
        block10.addDamage();
        block11 = new Block(stage,1,world, allBlock);
        block11.addBlock(1540-100,225+28+152+25,25,152);
        block11.addDamage();
        block12 = new Block(stage,0,world, allBlock);
        block12.addBlock(1202,225+28+152+25+152,1540-1202+25,28);
        block12.addDamage();



        pig2 = new Pig(stage,world,"king");
        pig2.addPig(1350,225+28+152+50,allPig);
        pig2.addDamage();

        catapult = new Catapult(stage);
        catapult.getCatapult();
        stage.addActor(pause);
        redBird = new RedBird(stage,world,viewport);
        redBird.getBirds(190,105, allBirds);
        redBird.launch();
//
        blueBird = new BlueBird(stage,world,viewport,allBirds);
        blueBird.getBirds(150,105, allBirds);
        blueBird.launch();
        yellowBird = new YellowBird(stage,world,viewport);
        yellowBird.getBirds(100,105, allBirds);
        yellowBird.launch();
//        blackBird = new BlackBird(stage,world,viewport);
//        blackBird.getBirds(50,105, allBirds);



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

        debugRenderer = new Box2DDebugRenderer(
            true, /* draw bodies */
            false, /* don't draw joints */
            true, /* draw aabbs */
            true, /* draw inactive bodies */
            false, /* don't draw velocities */
            true /* draw contacts */);

        b1 = new BlueBird(stage,world,viewport,new LinkedList<>());
        b2 = new BlueBird(stage,world,viewport,new LinkedList<>());


    }

    @Override
    public void render(float v) {

        camera.zoom = 1f;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0,1920/PPM,1000/PPM);
        batch.draw(ground, 0, 0,1920/PPM,136/PPM);
        batch.end();
        debugRenderer.render(world, camera.combined);

        ArrayList<BirdInterface> allBirdsCopy = new ArrayList<>(allBirds);
        for (BirdInterface b : allBirdsCopy) {
            if (catapult.isEmpty()) {
                BirdInterface topBird = allBirds.peek();
                if (topBird != null && topBird.getHealth() >= 100 && topBird.getActionTime() == 0) {

                    topBird.setPosition(272/PPM,240/PPM);
                }
                catapult.setEmpty(false);
            }
            if (b.getInAction() && b.getActionTime() < 5) {
                b.setActionTime(b.getActionTime() + Gdx.graphics.getDeltaTime());
                if (b.getActionTime() >= 5) {
                    b.setCanDestory(true);
                    b.setInAction(false);
                    return;
                }
            }
            b.updateImagePositionFromBody();
            if (b.isCanDestory()) {
                if (world.isLocked()) continue;
                b.Attack();
                b.setCanDestory(false);
                allBirds.remove(b);
                catapult.setEmpty(true);
            }


            if (Gdx.input.isTouched() && b.getInAction() && b.getBird().getUserData() instanceof BlueBird) {

                if (!b.isUsePower()) {

                    b1.getBirds(b.getPosition().x*32 + 50,b.getPosition().y*32+ 50, new LinkedList<>());
                    b2.getBirds(b.getPosition().x*32+ 50,b.getPosition().y*32+ 50, new LinkedList<>());
                    b1.getBird().setGravityScale(1);
                    b1.getBird().setLinearVelocity(b.getVelocity().scl(1,1.5f));
                    b2.getBird().setLinearVelocity(b.getVelocity().scl(1,-1.5f));
                    b2.getBird().setGravityScale(1);
                    b1.setCanDestory(true);
                    b2.setCanDestory(true);
                    b.setUsePower(true);
                }

            }
            else if (Gdx.input.isTouched() && b.getInAction()) {

                if (!b.isUsePower()) {
                    b.activateSuperPower();
                    b.setUsePower(true);
                }

            }
        }



        if (!allBirds.contains(blueBird) && b1.isCanDestory()) {

            if (!world.isLocked()) {
                System.out.println("dele");
                b1.Attack();
                b1.setCanDestory(false);
            }
        }
        if (!allBirds.contains(blueBird) && b2.isCanDestory()) {

            if (!world.isLocked()) {
                System.out.println("dele");
                b2.Attack();
                b2.setCanDestory(false);

            }
        }

        ArrayList<Block> allBlockCopy = new ArrayList<>(allBlock);

        for(Block b: allBlockCopy) {
            b.updateImagePositionFromBody();
            if (b.getCanDestroy()) {
                if (world.isLocked()) continue;
                world.destroyBody(b.getBlockBody());
                catapult.setEmpty(true);
                b.setCandestroy(false);
                allBlock.remove(b);
            }
        }
        ArrayList<Pig> allPigsCopy = new ArrayList<>(allPig);
        for (Pig p : allPigsCopy) {
            p.updateImagePositionFromBody();
            if (p.isCanDestroy()) {
                if (world.isLocked()) continue;
                world.destroyBody(p.getBody());
                block1.setCandestroy(false);
                allPig.remove(p);
            }
        }

        if (allPig.isEmpty()) {
            if (!isWaiting) {
                isWaiting = true;
                waitTime = 0f;
            } else {
                waitTime += Gdx.graphics.getDeltaTime();
                if (waitTime >= WAIT_DURATION) {
                    score = ((12 - allBlock.size()) * 100) + (allBirds.size() * 200);
                    System.out.println(score);
                    main.setScreen(new Won(main, 1, score));
                    return;
                }
            }
        }


        if (allBirds.isEmpty() && !allPig.isEmpty()) {
            if (!isWaiting) {
                isWaiting = true;
                waitTime = 0f;
            } else {
                waitTime += Gdx.graphics.getDeltaTime();
                if (waitTime >= WAIT_DURATION) {
                    main.setScreen(new Loss(main, 1));
                    return;
                }
            }
        }



        stage.act();
        stage.draw();
        stage2.act();
        stage2.draw();
        redBird.renderRope();
        redBird.renderTrajectory();
        blueBird.renderRope();
        blueBird.renderTrajectory();
        yellowBird.renderRope();
        yellowBird.renderTrajectory();

        if (b1.getBird() != null) {
            b1.updateImagePositionFromBody();
        }
        if (b2.getBird() != null) {
            b2.updateImagePositionFromBody();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            main.setScreen(new Won(main,1,0));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            main.setScreen(new Loss(main,1));
        }
        world.step(1/60f, 6, 2);



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
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
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
