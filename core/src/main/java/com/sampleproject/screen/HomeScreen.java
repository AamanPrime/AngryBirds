package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sampleproject.Main;
import java.awt.*;

public class HomeScreen implements Screen {
    private Texture background;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture continueImage;
    private Main main;
    public HomeScreen(Main main) {
        this.main = main;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920, 1000);

        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        background = new Texture("ui/homescreen.png");
        Image continueImage = new Image(new Texture("ui/continue.png"));
        Image selectlevel = new Image(new Texture("ui/selectlevel.png"));
        Image setting = new Image(new Texture("ui/settings.png"));
        Image close = new Image(new Texture("ui/close.png"));
        Image login = new Image(new Texture("ui/loginbutton.png"));
        Image signup = new Image(new Texture("ui/signupbutton.png"));
        setting.setPosition(50,30);
        setting.setScale(0.5f);
        continueImage.setScale(1f);
        continueImage.setPosition(750,500);
        selectlevel.setPosition(700,350);
        selectlevel.setScale(1f);
        close.setPosition(1800,30);
        close.setScale(0.5f);
        login.setPosition(1670,900);
        login.setScale(0.5f);
        signup.setPosition(1670,825);
        signup.setScale(0.5f);
        setting.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setting.addAction(Actions.sequence(
                    Actions.scaleTo(0.55f,0.55f,0.2f)
                ));


            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                setting.addAction(Actions.sequence(
                    Actions.scaleTo(0.5f,0.5f,0.2f)
                ));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new SettingPage(main));
                return true;
            }

        });

        close.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                close.addAction(Actions.sequence(
                    Actions.scaleTo(0.55f,0.55f,0.2f)
                ));

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                close.addAction(Actions.sequence(
                    Actions.scaleTo(0.5f,0.5f,0.2f)
                ));

            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });


        stage.addActor(continueImage);
        stage.addActor(selectlevel);
        stage.addActor(setting);
        stage.addActor(close);
        stage.addActor(login);
        stage.addActor(signup);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, 1920, 1000);
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
