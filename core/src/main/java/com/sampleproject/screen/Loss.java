package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.UserManager;

public class Loss implements Screen {

    private Image background;
    private Image loss;
    private Image levels;
    private Image restart;
    private Main main;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    int level;
    private UserManager.User user;
    public Loss(Main main, int level, UserManager.User user) {
        this.main = main;
        this.user = user;
        this.level = level;
    }

    @Override
    public void show() {
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1000, camera);
        batch = new SpriteBatch();
        if (level == 1) {
            background = new Image(new Texture("ui/level1bg.jpeg"));
            background.setSize(1920, 1200);
        }
        if (level == 2) {
            background = new Image(new Texture("ui/level2bg.png"));
            background.setSize(1920, 1200);
        }
        else if (level == 3) {
            background = new Image(new Texture("ui/level3bg.png"));
            background.setSize(1920,1200);
        }
        background.setPosition(0, 0);

        loss = new Image(new Texture("ui/loss.png"));
        loss.setPosition(685, 100);
        loss.setSize(550,850);
        restart = new Image(new Texture("ui/restart.png"));
        restart.setScale(0.5f);
        restart.setPosition(1080,140);

        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);
        levels.setPosition(760,140);

        levels.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levels.addAction(Actions.sequence(Actions.scaleTo(0.6f, 0.6f, 1.1f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levels.addAction(Actions.sequence(Actions.scaleTo(0.5f, 0.5f, 1.1f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                return false;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                restart.addAction(Actions.sequence(Actions.scaleTo(0.6f, 0.6f, 1.1f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                restart.addAction(Actions.sequence(Actions.scaleTo(0.5f, 0.5f, 1.1f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (level == 1) {
                    main.setScreen(new Level1(main,user));
                }
                else if (level == 2) {
                    main.setScreen(new Level2(main,user));
                }
                else {
                    main.setScreen(new Level3(main,user));
                }

                return false;
            }
        });




        stage.addActor(background);
        stage.addActor(loss);
        stage.addActor(restart);
        stage.addActor(levels);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float v) {
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
