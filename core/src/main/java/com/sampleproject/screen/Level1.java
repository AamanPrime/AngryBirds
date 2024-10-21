package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.*;

import java.util.logging.SocketHandler;

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
    private ShapeRenderer shapeRenderer;
    private Vector2 ellipseCenter;    // Ellipse center (fixed in this case)
    private float width, height;      // Ellipse width and height
    private boolean isDragging = false; // Flag to track if the mouse is dragging
    Box2DDebugRenderer debugRenderer;
    Pig pig1;
    Pig pig2;
    Block block1;
    Block block2;
    Block block3;
    Block block4;
    private boolean stats;
    Image pause;
    Image play;
    Image restart;
    Image levels;
    Image musicon;
    Image musicoff;
    Image soundon;
    Image soundoff;
    Image menu;

    RedBird redBird1;
    YellowBird yellowBird;
    BlueBird blueBird;
    BlackBird blackBird;
    private Main main;
    private Music backgroundMusic;
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
        ground = new Texture("angrybirds/ground.png");
        slingshot = new Texture("angrybirds/slingshot.png");
        slingpart = new Texture("angrybirds/slingpart.png");
        background = new Texture("angrybirds/background.png");
        pause = new Image(new Texture("ui/pause.png"));
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f);play.setPosition(1090, 600);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f);restart.setPosition(720,600);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);levels.setPosition(900,600);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f);musicon.setPosition(800,500);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f);musicoff.setPosition(800,500);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f);soundon.setPosition(1000,500);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f);soundoff.setPosition(1000,500);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        pause.setPosition(20, 900);
        pause.setScale(0.5f);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();


        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.play();
                musicoff.setVisible(false);

                musicon.setVisible(true);
                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                backgroundMusic.stop();
                musicon.setVisible(false);
                musicoff.setVisible(true);

                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                soundoff.setVisible(false);

                soundon.setVisible(true);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                soundon.setVisible(false);

                soundoff.setVisible(true);
                return true;
            }
        });



        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.play();
                play.setVisible(false);
                soundoff.setVisible(false);
                soundon.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
                menu.setVisible(false);
                restart.setVisible(false);
                levels.setVisible(false);

                return true;
            }
        });
        pause.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.stop();
                play.setVisible(true);

                soundon.setVisible(!false);

                musicon.setVisible(!false);
                menu.setVisible(!false);
                restart.setVisible(!false);
                levels.setVisible(!false);

                return true;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level1(main));
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main));
                return false;
            }
        });


        shapeRenderer = new ShapeRenderer();
        ellipseCenter = new Vector2(250,190);
        width = 100;
        height = 1;





        pause.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                showMenu();

                return true;
            }
        });

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1920, 100); // Width of 50, height of 1
        groundBody.createFixture(groundShape, 0.0f); // Static bodies don't need density
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundShape;
        groundFixtureDef.isSensor = false; // Ensure it's not a sensor
        groundFixtureDef.friction = 0.5f; // Adjust friction as necessary
        groundFixtureDef.restitution = 0f; // Bounciness, set to 0 for no bounce
        groundBody.createFixture(groundFixtureDef);
        groundShape.dispose();
        debugRenderer = new Box2DDebugRenderer();


        block1 = new Block(stage,2,world);
        block1.addBlock(1000,105,800,25);
        block2 = new Block(stage,1,world);
        block2.addBlock(1100,124,25,200);
        block3 = new Block(stage,1,world);
        block3.addBlock(1640,124,25,200);
        block4 = new Block(stage,0,world);
        block4.addBlock(1100,323,1640-1100+20,25);
        block2.addDamage();
        block1.addDamage();
        block3.addDamage();
        block4.addDamage();
        pig1 = new Pig(stage,world);
        pig1.addPig(1140,350);
        pig1.addDamage();
        pig2 = new Pig(stage,world);
        pig2.addPig(1540,350);
        pig2.addDamage();



        menu.setPosition(600,400);
        menu.setScale(0.5f);
        stage.addActor(menu);
        stage.addActor(pause);
        stage.addActor(play);
        stage.addActor(restart);
        stage.addActor(levels);
        stage.addActor(musicon);
        stage.addActor(soundon);
        stage.addActor(soundoff);
        stage.addActor(musicoff);


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

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);

        Gdx.input.setInputProcessor(inputMultiplexer);

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

        debugRenderer.render(world, camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        drawHalfEllipse(348,270,width,height,90,180);

        shapeRenderer.end();

        batch.begin();
        pig1.updateImagePositionFromBody();
        pig2.updateImagePositionFromBody();
        block1.updateImagePositionFromBody();
        block2.updateImagePositionFromBody();
        block3.updateImagePositionFromBody();
        block4.updateImagePositionFromBody();
        batch.end();

        stage.act();
        stage.draw();


    }

    private void showMenu() {


    }

    @Override
    public void resize(int i, int i1) {

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

        // Loop through angles from startAngle to startAngle + sweepAngle
        for (float angle = startAngle; angle <= startAngle + sweepAngle; angle += angleStep) {
            float rad = (float) Math.toRadians(angle);  // Convert angle to radians
            float x = cx + (width / 2f) * (float) Math.cos(rad);  // Calculate x-coordinate (keeps x-axis movement)
            float y = cy + (height / 2f) * (float) Math.sin(rad); // Calculate y-coordinate (keeps y-axis unchanged)


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
