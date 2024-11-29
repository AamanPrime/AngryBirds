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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.sampleproject.Main;
import com.sampleproject.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Level3 implements Screen, InputProcessor, Serializable {
    private static final long serialVersionUID = 1L;
    private transient World world;
    private transient SpriteBatch batch;
    private transient OrthographicCamera camera;
    private transient Viewport viewport;
    private transient Texture ground;
    private transient Texture background;
    private transient Stage stage;
    private transient Stage stage2;
    public transient ShapeRenderer shapeRenderer;
    transient Box2DDebugRenderer debugRenderer;
    public transient InputMultiplexer inputMultiplexer;
    public transient Music backgroundMusic;
    private float waitTime = 0f;
    private final float WAIT_DURATION = 5f;
    private boolean isWaiting = false;
    transient Body groundBody;
    private transient BitmapFont font;
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
    public transient Image pause;
    RedBird redBird;
    YellowBird yellowBird;
    BlueBird blueBird;
    //    BlackBird blackBird;
    BlueBird b1;
    BlueBird b2;
    private Main main;
    ArrayList<Block> allBlockCopy;
    transient BodyDef groundBodyDef;
    private ArrayList<Block> allBlock = new ArrayList<>();
    private ArrayList<Rock> allRock = new ArrayList<>();
    private ArrayList<Pig> allPig = new ArrayList<>();
    private Queue<BirdInterface> allBirds = new LinkedList<>();
    private UserManager.User user;
    private UserManager userManager;
    public boolean isloaded;
    private transient Label currentScoreLabel;
    private transient Label highScoreLabel;
    transient Stage stage3;

    public Level3(Main main, UserManager.User user) {
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new BirdBlockContactListener());
        camera = new OrthographicCamera(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS);
        camera.update();
        camera.position.set(0,0, 0);
        viewport = new FitViewport(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        stage2 = new Stage(viewport, batch);
        stage3 = new Stage();
        this.main = main;
        this.user = user;
        catapult = new Catapult(stage);
        catapult.getCatapult("normal");
        userManager = new UserManager();
    }


    public Level3(Main main,UserManager.User user,ArrayList<Block> allOldBlock, ArrayList<Rock> allOldRock, ArrayList<Pig> allOldPig, Queue<BirdInterface> allOldBirds) {
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new BirdBlockContactListener());
        camera = new OrthographicCamera(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS);
        camera.update();
        camera.position.set(0,0, 0);
        viewport = new FitViewport(WORLD_WIDTH_METERS,WORLD_HEIGHT_METERS, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        stage2 = new Stage(viewport, batch);
        stage3 = new Stage();
        this.main = main;
        this.user = user;
        catapult = new Catapult(stage);
        catapult.getCatapult("roc");
        userManager = new UserManager();
        for (Block b : allOldBlock) {
            Block nb= new Block(stage,b.getOrientation(),world,allBlock);
            nb.addBlock(b.getInitialPosition().x * 32,b.getInitialPosition().y * 32,(float) b.width , (float) b.height,b.getInitialPosition().z);
            nb.setRotation(b.getInitialPosition().z);
            nb.addDamage();
            nb.setHealth(b.getHealth());
        }
        for (Rock r : allOldRock) {
            Rock nr = new Rock(stage,r.getOrientation(),world,allRock);
            nr.addRock(r.getInitialPosition().x * 32,r.getInitialPosition().y * 32,r.width,r.height);
            nr.setRotation(r.getInitialPosition().z);
            nr.addDamage();
            nr.setHealth(r.getHealth());
        }
        for (BirdInterface b : allOldBirds) {
            if (b instanceof RedBird) {
                RedBird rb = (RedBird) b;
                redBird = new RedBird(stage,world,viewport,user);
                redBird.getBirds(rb.getInitialPosition().x * 32,rb.getInitialPosition().y * 32,allBirds);
                redBird.setInAction(rb.getInAction());
                redBird.setActionTime(rb.getActionTime());
                redBird.getBird().setLinearVelocity(rb.getCurrentVelocity());
                redBird.launch();
            }
            if (b instanceof BlueBird) {
                BlueBird bb = (BlueBird) b;
                blueBird = new BlueBird(stage,world,viewport,allBirds,user);
                blueBird.getBirds(bb.getInitialPosition().x * 32,bb.getInitialPosition().y * 32,allBirds);
                blueBird.setInAction(bb.getInAction());
                blueBird.setActionTime(bb.getActionTime());
                blueBird.getBird().setLinearVelocity(bb.getCurrentVelocity());
                blueBird.launch();
            }
            if (b instanceof YellowBird) {
                YellowBird yb = (YellowBird) b;
                yellowBird = new YellowBird(stage,world,viewport,user);
                yellowBird.getBirds(yb.getInitialPosition().x * 32,yb.getInitialPosition().y * 32,allBirds);
                yellowBird.setInAction(yb.getInAction());
                yellowBird.setActionTime(yb.getActionTime());
                yellowBird.getBird().setLinearVelocity(yb.getCurrentVelocity());
                yellowBird.launch();
            }
        }

        for (Pig p : allOldPig) {
            Pig np = new Pig(stage,world,p.getType());
            np.addPig((int)p.getCurrentPosition().x*32,(int )p.getCurrentPosition().y*32,allPig);
            np.addDamage();
        }

        this.isloaded = true;
    }

    public final float PPM = 32f;
    public final float WORLD_WIDTH_METERS = 1920f / PPM;
    public final float WORLD_HEIGHT_METERS = 1000f / PPM;
    private int score;
    @Override
    public void show() {
        ground = new Texture("angrybirds/ground.png");
        background = new Texture("ui/level3bg.png");
        pause = new Image(new Texture("ui/pause.png"));
        pause.setPosition(32/PPM, 900/PPM);
        pause.setScale(0.5f/PPM);
        stage.addActor(pause);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        if (userManager.getUsers(user.getUsername()).isMusicStatus()) {
            backgroundMusic.play();
        }
        shapeRenderer = new ShapeRenderer();
        groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        groundBody = world.createBody(groundBodyDef);
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
                PauseMenu p = new PauseMenu(stage,main,getthis(),stage2,user);
                p.show();
                backgroundMusic.stop();

                return true;
            }
        });

        debugRenderer = new Box2DDebugRenderer(true, false, true, true, false, true );
        stage.addActor(pause);
        b1 = new BlueBird(stage,world,viewport,new LinkedList<>(),user);
        b2 = new BlueBird(stage,world,viewport,new LinkedList<>(),user);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (camera.viewportHeight * 2f);
        parameter.minFilter = Texture.TextureFilter.Linear;     // Smooth scaling
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(255,255,255,1);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        currentScoreLabel = new Label("Current Score: " + score, labelStyle);
        currentScoreLabel.setPosition(1420 , 920 );
        currentScoreLabel.setScale(4f);

        highScoreLabel = new Label("High Score: " + user.getLevel3score(), labelStyle);
        highScoreLabel.setPosition(1450,850);


        stage3.addActor(currentScoreLabel);
        stage3.addActor(highScoreLabel);

        if (!isloaded) {
            init();
        }


    }

    void init() {

        ///ground block
        block1 = new Block(stage,2,world,allBlock);
        block1.addBlock(700,105,1200,25);
        block1.addDamage();

        block2 = new Block(stage,1,world,allBlock);
        block2.addBlock(1402,128,40,101+52+25);
        block2.addDamage();
        block3 = new Block(stage,1,world,allBlock);
        block3.addBlock(1740,128,40,101+52+25);
        block3.addDamage();

        Rock rock5 = new Rock(stage,1,world,allRock);
        rock5.addRock(1502,132,25,97);
        rock5.addDamage();
        Rock rock6 = new Rock(stage,1,world,allRock);
        rock6.addRock(1640,132,25,97);
        rock6.addDamage();
        Rock rock7 = new Rock(stage,0,world,allRock);
        rock7.addRock(1502,132+97,1640-1502+25,25);
        rock7.addDamage();

        block7 = new Block(stage,0,world,allBlock);
        block7.addBlock(1402,125+28+152,1540-1202+40,28);
        block7.addDamage();
        Rock rock8 = new Rock(stage,1,world,allRock);
        rock8.addRock(1402,125+28+152+25,25,97);
        rock8.addDamage();
        Rock rock9 = new Rock(stage,1,world,allRock);
        rock9.addRock(1740-75+90,125+28+152+25,25,97);
        rock9.addDamage();
        block10 = new Block(stage,1,world,allBlock);
        block10.addBlock(1402+100,125+28+152+25,25,97);
        block10.addDamage();
        block11 = new Block(stage,1,world,allBlock);
        block11.addBlock(1740-100,125+28+152+25,25,97);
        block11.addDamage();
        block12 = new Block(stage,0,world,allBlock);
        block12.addBlock(1402,70+28+152+25+152,1540-1202+40,28);
        block12.addDamage();
        Pig Pig2 = new Pig(stage,world,"king");
        Pig2.addPig(1550,225+28+50+25,allPig);
        Pig2.addDamage();
         Pig Pig3 = new Pig(stage,world,"normal");
        Pig3.addPig(1550,132,allPig);
        Pig3.addDamage();

//---------------------------------------------------------------------------------------
        int delta = 125;
        Block block13 = new Block(stage,1,world,allBlock);
        block13.addBlock(802+delta,128,40,101+52+25);
        block13.addDamage();
        Block block14 = new Block(stage,1,world,allBlock);
        block14.addBlock(1140+delta,128,40,101+52+25);
        block14.addDamage();

        Rock rock1 = new Rock(stage,1,world,allRock);
        rock1.addRock(852+delta,132,25,60+25);
        rock1.addDamage();
        Rock rock2 = new Rock(stage,1,world,allRock);
        rock2.addRock(942+delta+10,132,25,60+25);
        rock2.addDamage();
        Rock rock3 = new Rock(stage,0,world,allRock);
        rock3.addRock(852+delta,132+60+25,1640-1550+25+12,25);
        rock3.addDamage();
        Pig Pig4 = new Pig(stage,world,"helmet");
        Pig4.addPig(880+delta+20,130,allPig);
        Pig4.addDamage();

        Rock rock11 = new Rock(stage,1,world,allRock);
        rock11.addRock(992+delta,132,25,60+25);
        rock11.addDamage();
        Rock rock12 = new Rock(stage,1,world,allRock);
        rock12.addRock(1082+delta,132,25,60+25);
        rock12.addDamage();
        Rock rock13 = new Rock(stage,0,world,allRock);
        rock13.addRock(992+delta,132+60+25,1640-1550+25,25);
        rock13.addDamage();
        Pig Pig5 = new Pig(stage,world,"helmet");
        Pig5.addPig(1025+delta,130,allPig);
        Pig5.addDamage();

        Block block15 = new Block(stage,0,world,allBlock);
        block15.addBlock(802+delta,125+28+152,1540-1202+40,28);
        block15.addDamage();
        Rock rock4 = new Rock(stage,1,world,allRock);
        rock4.addRock(802+delta,125+28+152+25,25,97);
        rock4.addDamage();
        Rock rock10 = new Rock(stage,1,world,allRock);
        rock10.addRock(1140-75+delta+90,125+28+152+25,25,97);
        rock10.addDamage();
        Block block16 = new Block(stage,1,world,allBlock);
        block16.addBlock(802+100+delta,125+28+152+25,25,97);
        block16.addDamage();
        Block block17 = new Block(stage,1,world,allBlock);
        block17.addBlock(1140-100+delta,125+28+152+25,25,97);
        block17.addDamage();
        Block block18 = new Block(stage,0,world,allBlock);
        block18.addBlock(802+delta,70+28+152+25+152,1540-1202+40,28);
        block18.addDamage();


        Pig Pig1 = new Pig(stage,world,"king");
        Pig1.addPig(950+delta,255+28+50,allPig);
        Pig1.addDamage();

        //***************************

        Block block19 = new Block(stage,1,world,allBlock);
        block19.addBlock(1102+delta,70+28+152+25+152+28,40,101+52+25);
        block19.addDamage();
        Block block20 = new Block(stage,1,world,allBlock);
        block20.addBlock(1440,70+28+152+25+152+28,40,101+52+25);
        block20.addDamage();

//        rock14 = new Rock(stage,0,world,allRock);
//        rock14.addRock(802+1540-1202+40,70+28+152+25+152,20,25);
//        rock14.addDamage();
        Block block21 = new Block(stage,0,world,allBlock);
        block21.addBlock(802+300+delta,70+28+152+25+152+28+28+152,1540-1202-85,28);
        block21.addDamage();
        Rock rock20 = new Rock(stage,1,world,allRock);
        rock20.addRock(802+300+delta+5,70+28+152+25+152+28+28+152+25,25,97);
        rock20.addDamage();
        Rock rock21 = new Rock(stage,1,world,allRock);
        rock21.addRock(1140-75+300+85,70+28+152+25+152+28+28+152+25,25,97);
        rock21.addDamage();
//        block22 = new Block(stage,1,world,allBlock);
//        block22.addBlock(802+100+300,70+28+152+25+152+28+28+152+25,25,97);
//        block22.addDamage();
//        block23 = new Block(stage,1,world,allBlock);
//        block23.addBlock(1140-100+300-75,70+28+152+25+152+28+28+152+25,25,97);
//        block23.addDamage();
        Block block24 = new Block(stage,0,world,allBlock);
        block24.addBlock(802+300+delta,70+28+152+25+152+28+152+152,1540-1202-85,28);
        block24.addDamage();


        Pig Pig6 = new Pig(stage,world,"king");
        Pig6.addPig(1025+220+75,70+28+152+25+152+28+210,allPig);
        Pig6.addDamage();
//        Pig7 = new Pig(stage,world,"normal");
//        Pig7.addPig(tnt1.getX()+20, tnt1.getY()+100,allPig);
//        Pig7.addDamage();
//        pig8 = new Pig(stage,world,"king");
//        pig8.addPig(tnt1.getX(),128,allPig);
//        pig8.addDamage();
        redBird = new RedBird(stage,world,viewport,user);
        redBird.getBirds(190,105, allBirds);
        redBird.launch();

        blueBird = new BlueBird(stage,world,viewport,allBirds,user);
        blueBird.getBirds(150,105, allBirds);
        blueBird.launch();
        yellowBird = new YellowBird(stage,world,viewport,user);
        yellowBird.getBirds(100,105, allBirds);
        yellowBird.launch();


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
            b.renderTrajectory();
            b.renderRope();
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

                b1.Attack();
                b1.setCanDestory(false);
            }
        }
        if (!allBirds.contains(blueBird) && b2.isCanDestory()) {

            if (!world.isLocked()) {

                b2.Attack();
                b2.setCanDestory(false);

            }
        }

        allBlockCopy = new ArrayList<>(allBlock);

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

        ArrayList<Rock> allRockCopy = new ArrayList<>(allRock);
        for(Rock b: allRockCopy) {
            b.updateImagePositionFromBody();
            if (b.getCanDestroy()) {
                if (world.isLocked()) continue;
                world.destroyBody(b.getRockBody());
                catapult.setEmpty(true);
                b.setCandestroy(false);
                allRock.remove(b);
            }
        }

        ArrayList<Pig> allPigsCopy = new ArrayList<>(allPig);
        for (Pig p : allPigsCopy) {
            p.updateImagePositionFromBody();
            if (p.isCanDestroy()) {
                if (world.isLocked()) continue;
                world.destroyBody(p.getBody());
                allPig.remove(p);
            }
        }
        score = ((22 - allBlock.size()) * 100);
        if (allPig.isEmpty()) {
            if (!isWaiting) {
                isWaiting = true;
                waitTime = 0f;
            } else {
                waitTime += Gdx.graphics.getDeltaTime();
                if (waitTime >= WAIT_DURATION) {
                    score += (allBirds.size() * 200);
                    backgroundMusic.stop();
                    main.setScreen(new Won(main, 3, score,user));
                    return;
                }
            }
        }

        currentScoreLabel.setText("Current Score: " + score);

        if (allBirds.isEmpty() && !allPig.isEmpty()) {
            if (!isWaiting) {
                isWaiting = true;
                waitTime = 0f;
            } else {
                waitTime += Gdx.graphics.getDeltaTime();
                if (waitTime >= WAIT_DURATION) {
                    backgroundMusic.stop();
                    main.setScreen(new Loss(main, 3,user));
                    return;
                }
            }
        }



        stage.act();
        stage.draw();
        stage2.act();
        stage2.draw();
        stage3.act();
        stage3.draw();

//        for (BirdInterface b : allBirds) {
//            b.renderRope();
//            b.renderTrajectory();
//        }

        if (b1.getBird() != null) {
            b1.updateImagePositionFromBody();
        }
        if (b2.getBird() != null) {
            b2.updateImagePositionFromBody();
        }


        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            main.setScreen(new Won(main,1,0,user));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            main.setScreen(new Loss(main,1,user));
        }
        world.step(1/60f, 6, 2);
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
    public void saveGame(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this); // Serialize the current object
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Level3 loadGame(String filename) {

        Level3 level = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            level = (Level3) in.readObject(); // Deserialize the object
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return level;
    }

    public void load() {
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

    //Getter And Setter

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Texture getGround() {
        return ground;
    }

    public void setGround(Texture ground) {
        this.ground = ground;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage2() {
        return stage2;
    }

    public void setStage2(Stage stage2) {
        this.stage2 = stage2;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
        this.debugRenderer = debugRenderer;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(Music backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(float waitTime) {
        this.waitTime = waitTime;
    }

    public float getWAIT_DURATION() {
        return WAIT_DURATION;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public void setGroundBody(Body groundBody) {
        this.groundBody = groundBody;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public Pig getPig1() {
        return pig1;
    }

    public void setPig1(Pig pig1) {
        this.pig1 = pig1;
    }

    public Pig getPig2() {
        return pig2;
    }

    public void setPig2(Pig pig2) {
        this.pig2 = pig2;
    }

    public Pig getPig3() {
        return pig3;
    }

    public void setPig3(Pig pig3) {
        this.pig3 = pig3;
    }

    public Block getBlock1() {
        return block1;
    }

    public void setBlock1(Block block1) {
        this.block1 = block1;
    }

    public Block getBlock2() {
        return block2;
    }

    public void setBlock2(Block block2) {
        this.block2 = block2;
    }

    public Block getBlock3() {
        return block3;
    }

    public void setBlock3(Block block3) {
        this.block3 = block3;
    }

    public Block getBlock4() {
        return block4;
    }

    public void setBlock4(Block block4) {
        this.block4 = block4;
    }

    public Block getBlock5() {
        return block5;
    }

    public void setBlock5(Block block5) {
        this.block5 = block5;
    }

    public Block getBlock6() {
        return block6;
    }

    public void setBlock6(Block block6) {
        this.block6 = block6;
    }

    public Block getBlock7() {
        return block7;
    }

    public void setBlock7(Block block7) {
        this.block7 = block7;
    }

    public Block getBlock8() {
        return block8;
    }

    public void setBlock8(Block block8) {
        this.block8 = block8;
    }

    public Block getBlock9() {
        return block9;
    }

    public void setBlock9(Block block9) {
        this.block9 = block9;
    }

    public Block getBlock10() {
        return block10;
    }

    public void setBlock10(Block block10) {
        this.block10 = block10;
    }

    public Block getBlock11() {
        return block11;
    }

    public void setBlock11(Block block11) {
        this.block11 = block11;
    }

    public Block getBlock12() {
        return block12;
    }

    public void setBlock12(Block block12) {
        this.block12 = block12;
    }

    public Catapult getCatapult() {
        return catapult;
    }

    public void setCatapult(Catapult catapult) {
        this.catapult = catapult;
    }

    public Image getPause() {
        return pause;
    }

    public void setPause(Image pause) {
        this.pause = pause;
    }

    public RedBird getRedBird() {
        return redBird;
    }

    public void setRedBird(RedBird redBird) {
        this.redBird = redBird;
    }

    public YellowBird getYellowBird() {
        return yellowBird;
    }

    public void setYellowBird(YellowBird yellowBird) {
        this.yellowBird = yellowBird;
    }

    public BlueBird getBlueBird() {
        return blueBird;
    }

    public void setBlueBird(BlueBird blueBird) {
        this.blueBird = blueBird;
    }

    public BlueBird getB1() {
        return b1;
    }

    public void setB1(BlueBird b1) {
        this.b1 = b1;
    }

    public BlueBird getB2() {
        return b2;
    }

    public void setB2(BlueBird b2) {
        this.b2 = b2;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public BodyDef getGroundBodyDef() {
        return groundBodyDef;
    }

    public void setGroundBodyDef(BodyDef groundBodyDef) {
        this.groundBodyDef = groundBodyDef;
    }

    public ArrayList<Block> getAllBlock() {
        return allBlock;
    }

    public void setAllBlock(ArrayList<Block> allBlock) {
        this.allBlock = allBlock;
    }

    public ArrayList<Rock> getAllRock() {
        return allRock;
    }

    public void setAllRock(ArrayList<Rock> allRock) {
        this.allRock = allRock;
    }

    public ArrayList<Pig> getAllPig() {
        return allPig;
    }

    public void setAllPig(ArrayList<Pig> allPig) {
        this.allPig = allPig;
    }

    public Queue<BirdInterface> getAllBirds() {
        return allBirds;
    }

    public void setAllBirds(Queue<BirdInterface> allBirds) {
        this.allBirds = allBirds;
    }

    public UserManager.User getUser() {
        return user;
    }

    public void setUser(UserManager.User user) {
        this.user = user;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public float getPPM() {
        return PPM;
    }

    public float getWORLD_WIDTH_METERS() {
        return WORLD_WIDTH_METERS;
    }

    public float getWORLD_HEIGHT_METERS() {
        return WORLD_HEIGHT_METERS;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
