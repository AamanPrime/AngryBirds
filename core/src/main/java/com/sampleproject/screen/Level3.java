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
    private RedBird redBird1;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private Pig Pig3;
    private Pig Pig4;
    private Pig Pig5;
    private Pig Pig6;
    private Pig Pig7;
    private Pig pig8;
    private Main main;

    public Music backgroundMusic;
    public Level3(Main main) {
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
        block1 = new Block(stage,2,world);
        block1.addBlock(700,105,1200,25);
        block1.addDamage();

        block2 = new Block(stage,1,world);
        block2.addBlock(1402,128,40,101+52+25);
        block2.addDamage();
        block3 = new Block(stage,1,world);
        block3.addBlock(1740,128,40,101+52+25);
        block3.addDamage();

        rock5 = new Rock(stage,1,world);
        rock5.addRock(1502,132,25,97);
        rock5.addDamage();
        rock6 = new Rock(stage,1,world);
        rock6.addRock(1640,132,25,97);
        rock6.addDamage();
        rock7 = new Rock(stage,1,world);
        rock7.addRock(1502,132+97,1640-1502+25,25);
        rock7.addDamage();

        block7 = new Block(stage,0,world);
        block7.addBlock(1402,125+28+152,1540-1202+40,28);
        block7.addDamage();
        rock8 = new Rock(stage,1,world);
        rock8.addRock(1402,125+28+152+25,100,97);
        rock8.addDamage();
        rock9 = new Rock(stage,1,world);
        rock9.addRock(1740-75,125+28+152+25,115,97);
        rock9.addDamage();
        block10 = new Block(stage,1,world);
        block10.addBlock(1402+100,125+28+152+25,25,97);
        block10.addDamage();
        block11 = new Block(stage,1,world);
        block11.addBlock(1740-100,125+28+152+25,25,97);
        block11.addDamage();
        block12 = new Block(stage,0,world);
        block12.addBlock(1402,70+28+152+25+152,1540-1202+40,28);
        block12.addDamage();
        Pig2 = new Pig(stage,world,"king");
        Pig2.addPig(1550,225+28+50+25);
        Pig2.addDamage();
        Pig3 = new Pig(stage,world,"normal");
        Pig3.addPig(1550,132);
        Pig3.addDamage();

//---------------------------------------------------------------------------------------

        block13 = new Block(stage,1,world);
        block13.addBlock(802,128,40,101+52+25);
        block13.addDamage();
        block14 = new Block(stage,1,world);
        block14.addBlock(1140,128,40,101+52+25);
        block14.addDamage();

        rock1 = new Rock(stage,1,world);
        rock1.addRock(852,132,25,60);
        rock1.addDamage();
        rock2 = new Rock(stage,1,world);
        rock2.addRock(942,132,25,60);
        rock2.addDamage();
        rock3 = new Rock(stage,1,world);
        rock3.addRock(852,132+60,1640-1550+25,25);
        rock3.addDamage();
        Pig4 = new Pig(stage,world,"helmet");
        Pig4.addPig(880,130);
        Pig4.addDamage();

        rock11 = new Rock(stage,1,world);
        rock11.addRock(992,132,25,60);
        rock11.addDamage();
        rock12 = new Rock(stage,1,world);
        rock12.addRock(1082,132,25,60);
        rock12.addDamage();
        rock13 = new Rock(stage,1,world);
        rock13.addRock(992,132+60,1640-1550+25,25);
        rock13.addDamage();
        Pig5 = new Pig(stage,world,"helmet");
        Pig5.addPig(1025,130);
        Pig5.addDamage();

        block15 = new Block(stage,0,world);
        block15.addBlock(802,125+28+152,1540-1202+40,28);
        block15.addDamage();
        rock4 = new Rock(stage,1,world);
        rock4.addRock(802,125+28+152+25,100,97);
        rock4.addDamage();
        rock10 = new Rock(stage,1,world);
        rock10.addRock(1140-75,125+28+152+25,115,97);
        rock10.addDamage();
        block16 = new Block(stage,1,world);
        block16.addBlock(802+100,125+28+152+25,25,97);
        block16.addDamage();
        block17 = new Block(stage,1,world);
        block17.addBlock(1140-100,125+28+152+25,25,97);
        block17.addDamage();
        block18 = new Block(stage,0,world);
        block18.addBlock(802,70+28+152+25+152,1540-1202+40,28);
        block18.addDamage();


        Pig1 = new Pig(stage,world,"king");
        Pig1.addPig(950,255+28+50);
        Pig1.addDamage();

        //***************************

        block19 = new Block(stage,1,world);
        block19.addBlock(1102,70+28+152+25+152+28,40,101+52+25);
        block19.addDamage();
        block20 = new Block(stage,1,world);
        block20.addBlock(1440,70+28+152+25+152+28,40,101+52+25);
        block20.addDamage();

        rock14 = new Rock(stage,0,world);
        rock14.addRock(802+1540-1202+40,70+28+152+25+152,220,25);
        rock14.addDamage();
        block21 = new Block(stage,0,world);
        block21.addBlock(802+300,70+28+152+25+152+28+28+152,1540-1202+40,28);
        block21.addDamage();
        rock20 = new Rock(stage,1,world);
        rock20.addRock(802+300,70+28+152+25+152+28+28+152+25,100,97);
        rock20.addDamage();
        rock21 = new Rock(stage,1,world);
        rock21.addRock(1140-75+300,70+28+152+25+152+28+28+152+25,115,97);
        rock21.addDamage();
        block22 = new Block(stage,1,world);
        block22.addBlock(802+100+300,70+28+152+25+152+28+28+152+25,25,97);
        block22.addDamage();
        block23 = new Block(stage,1,world);
        block23.addBlock(1140-100+300,70+28+152+25+152+28+28+152+25,25,97);
        block23.addDamage();
        block24 = new Block(stage,0,world);
        block24.addBlock(802+300,70+28+152+25+152+28+152+152,1540-1202+40,28);
        block24.addDamage();


        TNT tnt1 = new TNT(stage,1025+210,70+28+152+25+152+28,world);
        tnt1.addTNT();
        Pig6 = new Pig(stage,world,"king");
        Pig6.addPig(1025+220,tnt1.getY()+210);
        Pig6.addDamage();
        Pig7 = new Pig(stage,world,"normal");
        Pig7.addPig(tnt1.getX()+20, tnt1.getY()+100);
        Pig7.addDamage();
        pig8 = new Pig(stage,world,"king");
        pig8.addPig(tnt1.getX(),128);
        pig8.addDamage();


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


        batch.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        drawHalfEllipse(318,270,width,height,90,180);

        shapeRenderer.end();

        batch.begin();
        Pig1.updateImagePositionFromBody();
        Pig2.updateImagePositionFromBody();
        Pig3.updateImagePositionFromBody();
        Pig4.updateImagePositionFromBody();
        Pig5.updateImagePositionFromBody();
        Pig6.updateImagePositionFromBody();
        Pig7.updateImagePositionFromBody();
        pig8.updateImagePositionFromBody();
//        block1.updateImagePositionFromBody();
//        block2.updateImagePositionFromBody();
//        block3.updateImagePositionFromBody();
//        block7.updateImagePositionFromBody();
//        block10.updateImagePositionFromBody();
//        block11.updateImagePositionFromBody();
//        block12.updateImagePositionFromBody();
//        block13.updateImagePositionFromBody();
//        block14.updateImagePositionFromBody();
//        block15.updateImagePositionFromBody();
//        block16.updateImagePositionFromBody();
//        block17.updateImagePositionFromBody();
//        block18.updateImagePositionFromBody();
//        block19.updateImagePositionFromBody();
//        block20.updateImagePositionFromBody();
//        block21.updateImagePositionFromBody();
//        block22.updateImagePositionFromBody();
//        block23.updateImagePositionFromBody();
//        block24.updateImagePositionFromBody();
//        rock1.updateImagePositionFromBody();
//        rock2.updateImagePositionFromBody();
//        rock3.updateImagePositionFromBody();
//        rock4.updateImagePositionFromBody();
//        rock5.updateImagePositionFromBody();
//        rock6.updateImagePositionFromBody();
//        rock7.updateImagePositionFromBody();
//        rock8.updateImagePositionFromBody();
//        rock9.updateImagePositionFromBody();
//        rock10.updateImagePositionFromBody();
//        rock11.updateImagePositionFromBody();
//        rock12.updateImagePositionFromBody();
//        rock13.updateImagePositionFromBody();
//        rock14.updateImagePositionFromBody();
//        rock20.updateImagePositionFromBody();
//        rock21.updateImagePositionFromBody();

        batch.end();

        stage.act();
        stage.draw();

        stage2.act();
        stage2.draw();

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
