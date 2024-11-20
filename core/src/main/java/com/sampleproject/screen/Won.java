package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;

public class Won implements Screen {
    private Main main;
    private Image background;
    int level;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Image dashboard;
    private BitmapFont font;
    private int score;
    private Image levels;
    private Image restart;
    private Image forward;
    private Image bird;
    private Image star;
    public Won(Main main,int level,int score) {
        this.main = main;
        this.level = level;
        this.score = score;
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
        dashboard = new Image(new Texture("ui/won.png"));
        restart = new Image(new Texture("ui/restart.png"));

        star = new Image(new Texture("ui/stars.png"));
        star.setPosition(790,650);
        star.setScale(0.65f);
        star.setVisible(false);

        Image star2 = new Image(new Texture("ui/stars.png"));
        star2.setScale(0.65f);
        star2.setPosition(890,680);
        star2.setVisible(false);

        Image star3 = new Image(new Texture("ui/stars.png"));
        star3.setScale(0.65f);
        star3.setPosition(988,650);
        star3.setVisible(false);

        if (score >= 100 && score < 300) {
            star.setVisible(true);
        }
        else if (score >= 300 && score < 500) {
            star.setVisible(true);
            star2.setVisible(true);
        }
        else if (score >= 500) {
            star.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
        }



        restart.setScale(0.5f);
        restart.setPosition(1080,140);

        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);
        levels.setPosition(730,140);

        forward = new Image(new Texture("ui/forward.png"));

        forward.setScale(0.3f);
        forward.setPosition(890,250);

        bird = new Image(new Texture("ui/redbirddashboard.png"));
        bird.setPosition(1080,250);
        bird.setScale(0.5f);
        background.setPosition(0, 0);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        font = generator.generateFont(parameter);
        dashboard.setPosition(40, 0);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(255,255,255,1);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 50;
        BitmapFont font2 = generator.generateFont(parameter2);
        Label.LabelStyle labelStyle2 = new Label.LabelStyle();
        labelStyle2.font = font2;
        labelStyle2.fontColor = new Color(255,255,255,1);


        Label label = new Label("Level " + level, labelStyle);
        Label scoreLabel = new Label(" " + score,labelStyle2);
        label.setPosition(840, 810);
        scoreLabel.setPosition(900,425);

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
                main.setScreen(new Levels(main));
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
                    main.setScreen(new Level1(main));
                }
                else if (level == 2) {
                    main.setScreen(new Level2(main));
                }
                else {
                    main.setScreen(new Level3(main));
                }

                return false;
            }
        });

        forward.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                forward.addAction(Actions.sequence(Actions.scaleTo(0.35f, 0.35f, 1.1f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                forward.addAction(Actions.sequence(Actions.scaleTo(0.3f, 0.3f, 1.1f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (level == 1) {
                    main.setScreen(new Level2(main));
                }
                else if (level == 2) {
                    main.setScreen(new Level3(main));
                }
                else {
                    main.setScreen(new HomeScreen(main));
                }

                return false;
            }
        });




        stage.addActor(background);

        stage.addActor(dashboard);
        stage.addActor(scoreLabel);
        stage.addActor(label);
        stage.addActor(levels);
        stage.addActor(restart);
        stage.addActor(forward);
        stage.addActor(bird);
        stage.addActor(star);
        stage.addActor(star2);
        stage.addActor(star3);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
