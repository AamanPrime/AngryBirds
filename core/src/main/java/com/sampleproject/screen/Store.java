package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.UserManager;

public class Store implements Screen {
    private Image storeImage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;

    private HomeScreen homeScreen;
    private UserManager.User user;
    private Main main;
    public Store(HomeScreen homeScreen, UserManager.User user, Main main) {
        this.homeScreen = homeScreen;
        this.user = user;
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1000,camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        storeImage = new Image(new Texture("ui/storebg.png"));
        storeImage.setSize(1920,1000);
        storeImage.setPosition(0,0);

        Image stonepricebutton = new Image(new Texture("ui/stonepricebutton.png"));
        stonepricebutton.setSize(180,90);
        Image woodepricebutton = new Image(new Texture("ui/woodpricebutton.png"));
        woodepricebutton.setSize(180,90);
        Image goldenpricebutton = new Image(new Texture("ui/goldenpricebutton.png"));
        goldenpricebutton.setSize(180,90);

        Image back = new Image(new Texture("ui/back.png"));
        back.setSize(80,80);
        back.setPosition(50,50);


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(255,255,255,1);

        Label mycoin = new Label("0", labelStyle);
        mycoin.setPosition(80,920);
        stonepricebutton.setPosition(370+10,180);
        woodepricebutton.setPosition(750+10,180);
        goldenpricebutton.setPosition(1125,180);

        stonepricebutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                stonepricebutton.addAction(Actions.sequence(Actions.scaleTo(1.1f,1.1f,0.2f)));

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                stonepricebutton.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
        });
        woodepricebutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                woodepricebutton.addAction(Actions.sequence(Actions.scaleTo(1.1f,1.1f,0.2f)));

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                woodepricebutton.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
        });
        goldenpricebutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                goldenpricebutton.addAction(Actions.sequence(Actions.scaleTo(1.1f,1.1f,0.2f)));

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                goldenpricebutton.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
        });
        back.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                back.addAction(Actions.sequence(Actions.scaleTo(1.1f,1.1f,0.2f)));

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                back.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(homeScreen);
                return false;
            }
        });


        stage.addActor(storeImage);
        stage.addActor(mycoin);
        stage.addActor(stonepricebutton);
        stage.addActor(woodepricebutton);
        stage.addActor(goldenpricebutton);
        stage.addActor(back);

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
