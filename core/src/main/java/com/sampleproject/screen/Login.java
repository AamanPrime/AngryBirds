package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.UserManager;

public class Login implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private BitmapFont font;
    private TextField username;
    private TextField password;
    private Label message;
    private String messageText = "";
    private Main main;
    private HomeScreen homeScreen;
    public Login(Main main,HomeScreen homeScreen, String message) {
        this.main = main;
        this.homeScreen = homeScreen;
        this.messageText = message;
    }
    public Login(Main main,HomeScreen homeScreen) {
        this.main = main;
        this.homeScreen = homeScreen;

    }
    public Login(Main main) {
        this.main = main;


    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1000,camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        background = new Texture("ui/loginPage.png");
        TextureRegion inputTemplate = new TextureRegion(new Texture("ui/inputTemplate.png"),720,82);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/f.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        font.setColor(0, 0, 0, 1);
        font.getData().setScale(0.85f);
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        textFieldStyle.background = new TextureRegionDrawable(inputTemplate);
        username = new TextField("", textFieldStyle);
        username.setMessageText("Enter Username");
        username.setPosition(615,449);
        username.setSize(720,82);
        username.getStyle().background.setLeftWidth(20f);
        username.getStyle().background.setRightWidth(20f);

        password = new TextField("", textFieldStyle);
        password.getStyle().background.setLeftWidth(20f);
        password.getStyle().background.setRightWidth(20f);
        password.setMessageText("Enter Password");
        password.setPosition(615,355);
        password.setSize(720,82);

        message = new Label(messageText, labelStyle);
        message.setPosition(725,535);
        Image loginButton = new Image(new Texture("ui/submitbutton.png"));
        loginButton.setPosition(748,200);
        loginButton.setSize(460,106);

        Image backbutton = new Image(new Texture("ui/back.png"));
        backbutton.setPosition(50,50);
        backbutton.setSize(100,100);

        stage.addActor(username);
        stage.addActor(password);
        stage.addActor(loginButton);
        stage.addActor(backbutton);
        stage.addActor(message);
        loginButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                loginButton.addAction(Actions.sequence(Actions.scaleTo(1.01f,1.01f,0.2f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                loginButton.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new HomeScreen(main));
                String usernameText = username.getText();
                String passwordText = password.getText();
                UserManager userManager = new UserManager();
                UserManager.User user = userManager.getUsers(usernameText);
                if (user != null && user.getPassword().equalsIgnoreCase(passwordText)) {

                    main.setScreen(new HomeScreen(main,user));

                }
                else {
                    main.setScreen(new Login(main,homeScreen,"Error username or password"));
                }
                return false;
            }
        });

        backbutton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backbutton.addAction(Actions.sequence(Actions.scaleTo(1.01f,1.01f,0.2f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backbutton.addAction(Actions.sequence(Actions.scaleTo(1f,1f,0.2f)));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (homeScreen == null) {
                    main.setScreen(new StartingPage(main));
                }
                else {
                    main.setScreen(new  HomeScreen(main));
                }
                return false;
            }
        });


        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(background, 0, 0,1920,1000);

        batch.end();
//        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
//        stage.screenToStageCoordinates(touchPos);
//        System.out.println("Touch position: x = " + touchPos.x + ", y = " + touchPos.y);

        stage.act(v);
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
