package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.UserManager;

import java.awt.*;

public class User implements Screen {

    private Image bg;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    private Main main;
    private UserManager.User user;
    public User(Main main,UserManager.User user) {
        this.main = main;
        this.user = user;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1000,camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        bg = new Image(new Texture("ui/userbg.png"));
        bg.setSize(1920,1000);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(255,255,255,1);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        TextureRegion inputTemplate = new TextureRegion(new Texture("ui/inputTemplate.png"),720,82);
        textFieldStyle.background = new TextureRegionDrawable(inputTemplate);
        TextField newusername = new TextField("",textFieldStyle);
        newusername.setMessageText("New Username");
        newusername.setSize(720,82);
        newusername.setPosition(600,1000-540);


        Image submit = new Image(new Texture("ui/submitbutton.png"));
        submit.setPosition(800,300);
        submit.setSize(300,80);

        Image close = new Image(new Texture("ui/close.png"));
        close.setPosition(1350,700);
        close.setScale(0.5f);
        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new HomeScreen(main,user));
                return true;
            }
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
        });


        Label score = new Label("0000", labelStyle);
        score.setPosition(600,1000-340);

        stage.addActor(bg);
        stage.addActor(score);
        stage.addActor(newusername);
        stage.addActor(submit);
        stage.addActor(close);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
