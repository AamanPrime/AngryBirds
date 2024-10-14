package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level1 implements Screen {


    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture ground;
    private Texture slingshot;
    private Texture slingpart;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 560, camera);
        batch = new SpriteBatch();
        ground = new Texture("angrybirds/ground.png");
        slingshot = new Texture("angrybirds/slingshot.png");
        slingpart = new Texture("angrybirds/slingpart.png");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(ground, 0, 0,1080,136);
        batch.draw(slingshot, 108,100);
        batch.draw(slingpart, 108,140);
        batch.end();
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
        batch.dispose();
        ground.dispose();
    }
}
