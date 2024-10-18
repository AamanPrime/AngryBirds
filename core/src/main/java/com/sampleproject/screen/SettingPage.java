package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.GameSettings;

public class SettingPage implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private Main main;
    public SettingPage(Main main) {
        this.main = main;
    }
    private boolean musicStatus = false;
    private  boolean soundsStatus = false;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920, 1000, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        background = new Texture("ui/SettingPage.png");
        Image close = new Image(new Texture("ui/close.png"));
        Image soundon = new Image(new Texture("ui/soundon.png"));
        Image soundoff = new Image(new Texture("ui/soundoff.png"));
        Image musicoff = new Image(new Texture("ui/musicoff.png"));
        Image musicon = new Image(new Texture("ui/musicon.png"));
        soundon.setVisible(false);
        musicon.setVisible(false);
        soundoff.setScale(0.7f);
        musicoff.setScale(0.7f);
        musicon.setScale(0.7f);
        soundon.setScale(0.7f);
        if (musicStatus) {
            musicon.setPosition(1245,520);
        }
        else {
            musicoff.setPosition(1245,520);
        }
        if (soundsStatus) {
            soundon.setPosition(1245,375);
        }
        else {
            soundoff.setPosition(1245,375);
        }

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                musicStatus = true;
                musicoff.setVisible(false);
                musicon.setPosition(1245,520);
                musicon.setVisible(true);
                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                musicStatus = false;

                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundsStatus = true;
                soundoff.setVisible(false);
                soundon.setPosition(1245,375);
                soundon.setVisible(true);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundsStatus = false;
                soundon.setVisible(false);

                soundoff.setVisible(true);
                return true;
            }
        });

        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new HomeScreen(main));
                return true;
            }
        });



        close.setPosition(1450,780);
        stage.addActor(close);
        stage.addActor(soundon);
        stage.addActor(soundoff);
        stage.addActor(musicon);
        stage.addActor(musicoff);
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
}
