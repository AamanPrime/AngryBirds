package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.util.*;
import com.badlogic.gdx.InputProcessor;


public class Login implements Screen {
    private SpriteBatch batch;
    private Texture image;
    private TextureRegion headImage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private TextureRegion inputImage;
    private TextureAtlas atlas;
    private Stage stage;
    private static TextField textField;
    private Texture button;
    private Rectangle imageBounds;
    private TouchInputHandler touchInputProcessor;
    private static Main main;

    public Login(Main main) {
        Login.main = main;
    }

    @Override

    public void show() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 560, camera);
        batch = new SpriteBatch();
        image = new Texture("angrybirds/background.png");
        headImage = new TextureRegion(new Texture("ui/angr_birds.png"), 0, 76, 260, 43);
        inputImage = new TextureRegion(new Texture("ui/Block.png"), 88, 2, 85, 44);
        font = new BitmapFont(Gdx.files.internal("font/b.fnt"));
        font.setColor(0, 0, 0, 1);
        font.getData().setScale(0.85f);

        button = new Texture("ui/okButton.png");
        imageBounds = new Rectangle(520,200,60,60);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(inputImage);
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;

        textField = new TextField("", textFieldStyle);
        textField.setMessageText("Username");
        textField.setSize(250, 44);
        textField.setPosition(540 - 125, 280 - 22);
        textField.getStyle().background.setLeftWidth(20f);
        textField.getStyle().background.setRightWidth(20f);
        textField.setAlignment(Align.center);

        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                if (key == '\r' || key == '\n') {
                    processTextInput(textField.getText());
                }
            }
        });

        touchInputProcessor = new TouchInputHandler(imageBounds);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(touchInputProcessor);
        inputMultiplexer.addProcessor(stage);

        stage.addActor(textField);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(image, 0, 0);
        batch.draw(headImage, 540 - 200, 350, 400, 44);
        font.draw(batch, "LogIn/SignUp", 540 - 125 + 125 - 100, 382);
        batch.draw(button, 540 - 20, 200, 60, 60);
        batch.end();


        stage.act(delta);
        stage.draw();


    }
    static void processTextInput(String userInput) {

        if (!userInput.isEmpty()) {
            System.out.println("User Input: " + userInput);
            main.setScreen(new HomeScreen());

        }
    }
    public static void processTextInput() {
        String userInput = textField.getText();
        if (!userInput.isEmpty()) {
            System.out.println("User Input: " + userInput);
        }
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
        button.dispose();
        Gdx.input.setInputProcessor(null);

    }
}
