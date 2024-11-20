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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sampleproject.Main;
import com.sampleproject.model.UserManager;

public class HomeScreen implements Screen {
    private Texture background;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture continueImage;
    private Main main;
    private UserManager userManager = new UserManager();
    private UserManager.User user = userManager.getUsers("default");
    private Label name;
    private BitmapFont font;
    public HomeScreen(Main main,UserManager.User user) {
        this.main = main;
        this.user = user;
        if (user == null) {
            userManager.addUser(new UserManager.User("default","default"));
            user = userManager.getUsers("default");
        }

    }
    public HomeScreen(Main main) {
        this.main = main;
        user = userManager.getUsers("default");
        if (user == null) {
            userManager.addUser(new UserManager.User("default","default"));
        }
        user = userManager.getUsers("default");
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920, 1000);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        background = new Texture("ui/homescreen.png");
        Image continueImage = new Image(new Texture("ui/continue.png"));
        Image selectlevel = new Image(new Texture("ui/selectlevel.png"));
        Image setting = new Image(new Texture("ui/settings.png"));
        Image close = new Image(new Texture("ui/close.png"));
        Image login = new Image(new Texture("ui/loginbutton.png"));
        Image signup = new Image(new Texture("ui/signupbutton.png"));
        Image signout = new Image(new Texture("ui/signout.png"));
        Image storeicon = new Image(new Texture("ui/storeicon.png"));
        name = new Label(user.getUsername(), labelStyle);
        name.setColor(Color.WHITE);
        name.setPosition(90,900);
        setting.setPosition(50,30);
        setting.setScale(0.5f);
        continueImage.setScale(1f);
        continueImage.setPosition(765,500);
        selectlevel.setPosition(700,350);
        selectlevel.setScale(1f);
        close.setPosition(1800,30);
        close.setScale(0.5f);
        login.setPosition(1670,900);
        login.setScale(0.5f);
        signup.setPosition(1670,825);
        signup.setScale(0.5f);
        signout.setPosition(1670,900);
        signout.setScale(0.5f);
        storeicon.setScale(0.5f);
        storeicon.setPosition(50,130);


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
        HomeScreen homeScreen = this;
        login.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                login.addAction(Actions.sequence(
                    Actions.scaleTo(0.55f,0.55f,0.2f)
                ));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                login.addAction(Actions.sequence(
                    Actions.scaleTo(0.5f,0.5f,0.2f)
                ));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Login(main,homeScreen));
                return true;
            }
        });
        signup.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                signup.addAction(Actions.sequence(
                    Actions.scaleTo(0.55f,0.55f,0.2f)
                ));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                signup.addAction(Actions.sequence(
                    Actions.scaleTo(0.5f,0.5f,0.2f)
                ));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new SignUp(main,homeScreen));
                return true;
            }
        });
        signout.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                signout.addAction(Actions.sequence(
                    Actions.scaleTo(0.55f,0.55f,0.2f)
                ));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                signout.addAction(Actions.sequence(
                    Actions.scaleTo(0.5f,0.5f,0.2f)
                ));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new HomeScreen(main));
                return true;
            }
        });
        continueImage.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                continueImage.addAction(Actions.sequence(
                    Actions.scaleTo(1.01f,1.01f,0.2f)
                ));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                continueImage.addAction(Actions.sequence(
                    Actions.scaleTo(1f,1f,0.2f)
                ));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,homeScreen));
                return true;
            }
        });
        selectlevel.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                selectlevel.addAction(Actions.sequence(
                    Actions.scaleTo(1.01f,1.01f,0.2f)
                ));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                selectlevel.addAction(Actions.sequence(
                    Actions.scaleTo(1f,1f,0.2f)
                ));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,homeScreen));
                return true;
            }
        });
        storeicon.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                storeicon.addAction(Actions.sequence(Actions.scaleTo(0.52f,0.52f,0.2f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                storeicon.addAction(Actions.sequence(Actions.scaleTo(0.5f,0.5f,0.2f)));
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y, int pointer, int button) {
                main.setScreen(new Store(homeScreen,user,main));
                return true;
            }
        });

        name.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,float x, float y, int pointer, int button) {
                main.setScreen(new User(main,user));
                return true;
            }
        });

        stage.addActor(continueImage);
        stage.addActor(selectlevel);
        stage.addActor(setting);
        stage.addActor(close);
        stage.addActor(login);
        stage.addActor(signup);
        stage.addActor(signout);
        stage.addActor(name);
        stage.addActor(storeicon);
        if (user == null || user.getUsername().equalsIgnoreCase("default")) {
            signout.setVisible(false);
        }
        else {
            login.setVisible(false);
            signup.setVisible(false);
        }

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
