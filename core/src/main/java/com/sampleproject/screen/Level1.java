package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.model.Block;

import java.util.logging.SocketHandler;

public class Level1 implements Screen, InputProcessor {


    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture ground;
    private Texture slingshot;
    private Texture slingpart;
    private Texture background;
    private Texture blocks;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private Vector2 ellipseCenter;    // Ellipse center (fixed in this case)
    private float width, height;      // Ellipse width and height
    private boolean isDragging = false; // Flag to track if the mouse is dragging

    @Override
    public void show() {
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1000, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        ground = new Texture("angrybirds/ground.png");
        slingshot = new Texture("angrybirds/slingshot.png");
        slingpart = new Texture("angrybirds/slingpart.png");
        background = new Texture("angrybirds/background.png");
        blocks = new Texture("ui/Block.png");
        shapeRenderer = new ShapeRenderer();
        ellipseCenter = new Vector2(250,190);
        width = 150;
        height = 1;


        Block block1 = new Block(stage,0);
        block1.addBlock(1000,105,800,20);
        Block block2 = new Block(stage,1);
        block2.addBlock(1100,124,20,200);
        Block block3 = new Block(stage,1);
        block3.addBlock(1640,124,20,200);
        Block block4 = new Block(stage,0);
        block4.addBlock(1100,323,1640-1100+20,20);
        block2.addDamage();
        block1.addDamage();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0,1920,1200);
        batch.draw(ground, 0, 0,1920,136);
        batch.draw(slingshot, 258,100,100,200);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        drawHalfEllipse(348,270,width,height,90,180);
        shapeRenderer.end();

        batch.begin();
        batch.draw(slingpart, 258,170,100,140);
//        batch.draw(new TextureRegion(blocks,45,90,260,22), 500,200);
//        batch.draw(new TextureRegion(blocks,45,90,200,22), 718,100);
//        batch.draw(new TextureRegion(blocks,0,240,43,25), 550,170,22,12,43,25,3,1,270);
//        batch.draw(new TextureRegion(blocks,0,240,43,25), 830,170,22,12,43,25,3,1,270);
//        batch.draw(new TextureRegion(blocks,0,240,43,25), 730,150,22,12,43,25,2,1,270);
//        batch.draw(new TextureRegion(blocks,0,240,43,25), 630,150,22,12,43,25,2,1,270);
//        batch.draw(new TextureRegion(blocks,48,90,120,22), 644,200);
//        batch.draw(new TextureRegion(blocks,45,90,260,22), 625,240,130,11,260,22,5.5f,1,0);
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
        batch.dispose();
        ground.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        width = 75;
        height = 1;
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Math.abs((screenX - 155)) <= 25) {
            isDragging = true;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

            if (isDragging) {
                float deltaX = Math.abs(screenX - ellipseCenter.x);
                float deltaY = Math.abs(screenY - ellipseCenter.y);
                width = deltaX + 30;
                height = 20;

                System.out.println("Dragged: " + deltaX + " " + deltaY);
            }



        return true;
    }
    private void drawHalfEllipse(float cx, float cy, float width, float height, float startAngle, float sweepAngle) {
        float angleStep = sweepAngle / 50;  // Divide the sweep angle by the number of segments

        // Loop through angles from startAngle to startAngle + sweepAngle
        for (float angle = startAngle; angle <= startAngle + sweepAngle; angle += angleStep) {
            float rad = (float) Math.toRadians(angle);  // Convert angle to radians
            float x = cx + (width / 2f) * (float) Math.cos(rad);  // Calculate x-coordinate (keeps x-axis movement)
            float y = cy + (height / 2f) * (float) Math.sin(rad); // Calculate y-coordinate (keeps y-axis unchanged)


            if (angle > startAngle) {
                float prevRad = (float) Math.toRadians(angle - angleStep);
                float prevX = cx + (width / 2f) * (float) Math.cos(prevRad);
                float prevY = cy + (height / 2f) * (float) Math.sin(prevRad);

                shapeRenderer.line(prevX, prevY, x, y);


            }
        }
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
