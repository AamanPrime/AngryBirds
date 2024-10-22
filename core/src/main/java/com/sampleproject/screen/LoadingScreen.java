package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.GameSettings;

public class LoadingScreen implements Screen {
    private Texture loadingimg;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Texture loadingbar;
    private Texture progessbar;
    private float progress = 0.25f;
    private Texture loadingtext;
    private Main main;
    GameSettings settings;
    public LoadingScreen(Main main, GameSettings settings) {
        this.main = main;
        this.settings = settings;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920,1000,camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        loadingimg = new Texture("ui/loadingscreen.png");
        loadingbar = new Texture("ui/loadingbar.png");
        progessbar = new Texture("ui/progressbar.png");
        loadingtext = new Texture("ui/loadingtext.png");

    }

    @Override
    public void render(float v) {

            progress += 0.005;
            if (progress >= 1) {
                progress = 1;
            }
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();

            batch.draw(loadingimg,0,0,1920,1000);
            batch.draw(loadingtext, 860-45, 180);
            batch.draw(loadingbar,860-100,100,414,52);

            float loadingBarWidth = 405 * progress;
            batch.draw(progessbar,860-94,100,loadingBarWidth,45);

            batch.end();

            if (progress >= 1) {
                main.setScreen(new StartingPage(main));
            }

            System.out.println(loadingBarWidth);
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
