package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.LevelIcon;

public class Levels implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private Main main;
    private HomeScreen homeScreen;
    private Music backgroundMusic;
    public Levels(Main main, HomeScreen homeScreen) {
        this.main = main;
        this.homeScreen = homeScreen;
    }

    public Levels(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewport = new StretchViewport(1920,1000,camera);
        stage = new Stage(viewport, batch);
        background = new Texture("ui/levelshomescreen.png");
        Image backicon = new Image(new Texture("ui/back.png"));
        backicon.setScale(0.5f);
        backicon.rotateBy(-5);
        backicon.setPosition(50, 50);

        backicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (homeScreen != null) {
                    main.setScreen(homeScreen);
                }
                else {
                    main.setScreen(new HomeScreen(main));
                }
               return false;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backicon.addAction(Actions.sequence(
                    Actions.scaleTo(0.52f,0.52f,0.2f)

                ));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backicon.addAction(Actions.sequence(
                    Actions.scaleTo(0.50f,0.50f,0.2f)
                ));
            }

        });


        Image level1 = new LevelIcon(stage).addLevel(320,400,0,1);
        Image level2 = new LevelIcon(stage).addLevel(820,400,0,2);
        Image level3 = new LevelIcon(stage).addLevel(1320,400,0,3);


        level1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                main.setScreen(new Level1(main));
                return true;
            }
        });
        level2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level2(main));
                return true;
            }
        });
        level3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level3(main));
                return true;
            }
        });

        stage.addActor(backicon);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float v) {
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
