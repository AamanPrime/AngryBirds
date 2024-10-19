package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;


public class StartingPage implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private Music backgroundMusic;
    private static Main main;

    public StartingPage(Main main) {
        StartingPage.main = main;
    }

    @Override

    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1000,camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Image loginbutton  = new Image(new Texture("ui/loginbutton.png"));
        Image signupbutton  = new Image(new Texture("ui/signupbutton.png"));
        Image playbutton  = new Image(new Texture("ui/playbutton.png"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/song.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        backgroundMusic.setVolume(0.5f);
        loginbutton.setPosition(50,80);
        signupbutton.setPosition(1575,80);
        playbutton.setPosition(800,80);
        playbutton.setScale(0.5f);
        loginbutton.setScale(1f);
        signupbutton.setScale(1f);

        loginbutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                loginbutton.setScale(1.01f);
                loginbutton.setPosition(loginbutton.getX()-1, loginbutton.getY());


            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                loginbutton.setScale(1f);
                loginbutton.setPosition(loginbutton.getX()+1, loginbutton.getY());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.stop();
                main.setScreen(new Login(main));
                return false;
            }
        });


        signupbutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                signupbutton.setScale(1.01f);
                signupbutton.setPosition(signupbutton.getX(),signupbutton.getY());


            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

                signupbutton.setScale(1f);
                signupbutton.setPosition(signupbutton.getX(),signupbutton.getY());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.stop();
                main.setScreen(new SignUp(main));
                return false;
            }
        });

        playbutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playbutton.setScale(0.51f);
                playbutton.setPosition(playbutton.getX()+1,playbutton.getY());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playbutton.setScale(0.5f);
                playbutton.setPosition(playbutton.getX()-1,playbutton.getY());
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundMusic.stop();
                main.setScreen(new HomeScreen(main));
                return true;
            }
        });

        background = new Texture("angrybirds/splash.png");


        stage.addActor(loginbutton);
        stage.addActor(signupbutton);
        stage.addActor(playbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0,1920,1000);
        batch.end();

        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {


    }
}
