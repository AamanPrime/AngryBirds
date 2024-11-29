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

public class SettingPage implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private Main main;
    private BitmapFont font;
    private UserManager.User user;
    private UserManager userManager = new UserManager();
    public SettingPage(Main main, UserManager.User user) {
        this.main = main;
        this.user = user;
        this.user = userManager.getUsers(user.getUsername());
    }
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
        musicoff.setVisible(false);
        soundon.setVisible(false);
        soundoff.setScale(0.5f);
        musicoff.setScale(0.5f);
        musicon.setScale(0.5f);
        soundon.setScale(0.5f);
        close.setScale(0.5f);
        musicoff.setPosition(1110,545);
        musicon.setPosition(1110,545);
        soundon.setPosition(1110,434);
        soundoff.setPosition(1110,434);
        close.setPosition(1350,700);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(255,255,255,1);

        Label settinglabel = new Label("Settings", labelStyle);
        settinglabel.setPosition(820,1000-350);

        Label soundlabel = new Label("Sound", labelStyle);
        soundlabel.setPosition(700,1000-460);
        Label musiclabel = new Label("Music", labelStyle);
        musiclabel.setPosition(700,1000-570);
        Label difficultylabel = new Label("Difficulty", labelStyle);
        difficultylabel.setPosition(700,1000-675);
        Label difficulty = new Label("HARD", labelStyle);
        Label easy = new Label("EASY", labelStyle);
        difficulty.setPosition(1080,1000-680);
        difficulty.setFontScale(0.5f);
        easy.setPosition(1080,1000-680);
        easy.setFontScale(0.5f);
        easy.setVisible(false);

        difficulty.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("difficulty", user.getUsername());
                difficulty.setVisible(false);
                easy.setVisible(true);
                return true;
            }
        });
        easy.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("difficulty", user.getUsername());
                easy.setVisible(false);
                difficulty.setVisible(true);
                return true;
            }
        });

        if (user.isMusicStatus()) {
            musicon.setVisible(true);
            musicoff.setVisible(false);
        }
        else {
            musicoff.setVisible(true);
            musicon.setVisible(false);
        }

        if (user.isSoundStatus()) {
            soundon.setVisible(true);
            soundoff.setVisible(false);
        }
        else {
            soundoff.setVisible(true);
            soundon.setVisible(false);
        }

        if (user.isDifficulty()) {
            difficulty.setVisible(true);
            easy.setVisible(false);
        }
        else {
            difficulty.setVisible(false);
            easy.setVisible(true);
        }

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(true);
                musicoff.setVisible(false);
                return true;
            }
        });

        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });

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

        stage.addActor(close);
        stage.addActor(soundon);
        stage.addActor(soundoff);
        stage.addActor(musicon);
        stage.addActor(musicoff);

        stage.addActor(settinglabel);
        stage.addActor(musiclabel);
        stage.addActor(soundlabel);
        stage.addActor(difficultylabel);
        stage.addActor(difficulty);
        stage.addActor(easy);
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
