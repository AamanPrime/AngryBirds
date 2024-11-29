package com.sampleproject.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.screen.Level1;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BlueBird implements BirdInterface, Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID
    private int health = 100;
    private transient Stage stage;
    private transient Body bird;
    private transient BodyDef bodyDef;
    private transient World world;
    private transient Image birdImage;
    private Vector2 launchVector = new Vector2();
    private transient Viewport viewport;
    private boolean canDestory = false;
    public final float PPM = 32f;
    private transient ShapeRenderer shapeRenderer;
    private Vector2 catapultPosition = new Vector2(288 / PPM, 270 / PPM);
    private boolean isDragging;
    public float actionTime;
    public boolean inaction;
    private final float timeStep = 0.1f;
    private final int maxSteps = 50;
    private boolean usePower = false;
    private List<Vector2> trajectoryPoints;
    private Queue<BirdInterface> allBirds;
    private Vector3 initialPosition = new Vector3();
    private UserManager.User user;
    private Vector2 currentVelocity = new Vector2();
    public BlueBird() {

    }

    public BlueBird(Stage stage, World world, Viewport viewport, Queue<BirdInterface> allBirds, UserManager.User user) {
        this.viewport = viewport;
        this.stage = stage;
        this.world = world;
        this.shapeRenderer = new ShapeRenderer();
        this.trajectoryPoints = new ArrayList<>();
        this.allBirds = allBirds;
        this.user = user;
    }

    public boolean isInaction() {
        return inaction;
    }

    public void setInaction(boolean inaction) {
        this.inaction = inaction;
    }
    public Vector2 getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(Vector2 currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public Vector3 getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Vector3 initialPosition) {
        this.initialPosition = initialPosition;
    }

    public boolean isUsePower() {
        return usePower;
    }

    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }

    public Queue<BirdInterface> getAllBirds() {
        return allBirds;
    }

    public void setAllBirds(Queue<BirdInterface> allBirds) {
        this.allBirds = allBirds;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public float getPPM() {
        return PPM;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Vector2 getPosition() {
        return bird.getPosition();
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public Vector2 getCatapultPosition() {
        return catapultPosition;
    }

    public void setCatapultPosition(Vector2 catapultPosition) {
        this.catapultPosition = catapultPosition;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }

    public float getActionTime() {
        return actionTime;
    }

    public void setActionTime(float actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public void activateSuperPower() {

        if (usePower) {
            return;
        }
        usePower = true;
    }


    public boolean getInAction() {
        return inaction;
    }

    public void setInAction(boolean inaction) {
        this.inaction = inaction;
    }

    public float getTimeStep() {
        return timeStep;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public List<Vector2> getTrajectoryPoints() {
        return trajectoryPoints;
    }

    public void setTrajectoryPoints(List<Vector2> trajectoryPoints) {
        this.trajectoryPoints = trajectoryPoints;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Body getBird() {
        return bird;
    }

    public void setBird(Body bird) {
        this.bird = bird;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Image getBirdImage() {
        return birdImage;
    }

    public void setBirdImage(Image birdImage) {
        this.birdImage = birdImage;
    }

    public Vector2 getLaunchVector() {
        return launchVector;
    }

    public void setLaunchVector(Vector2 launchVector) {
        this.launchVector = launchVector;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public boolean isCanDestory() {
        return canDestory;
    }

    public void setCanDestory(boolean canDestory) {
        this.canDestory = canDestory;
    }

    public void setVelocity(float x, float y) {
        bird.setLinearVelocity(x, y);
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setPosition(float x, float y) {
        bird.setTransform(x,y,0);
    }

    public Vector2 getVelocity() {
        return bird.getLinearVelocity();
    }

    @Override
    public void getBirds(float x, float y, Queue<BirdInterface> allBirds) {
        x/=PPM;
        y/=PPM;
        System.out.println(world.getGravity());
        birdImage = new Image(new Texture("ui/bluebird.png"));
        birdImage.setPosition(x, y);
        birdImage.setSize(45/PPM,45/PPM);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x+(22.5f/PPM), y+(22.5f/PPM));
        bird = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(20/PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.friction = 1f;
        bird.createFixture(fixtureDef);
        shape.dispose();
        stage.addActor(birdImage);
        bird.setUserData(this);
        bird.setGravityScale(0);
        allBirds.add(this);
    }

    @Override
    public void Attack() {
        world.destroyBody(bird);
        birdImage.remove();
    }

    @Override
    public void launch() {
        birdImage.addListener(new InputListener() {
            private final float maxDragRadius = 100f / PPM; // Maximum drag radius from catapult center
            private Vector2 dragVector = new Vector2(); // To store the drag vector

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bird.setGravityScale(0); // Disable gravity while dragging
                return true; // Start dragging
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                isDragging = true;
                Vector2 touchPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(touchPosition);
                dragVector = touchPosition.cpy().sub(catapultPosition);

                if (dragVector.len() > maxDragRadius) {
                    dragVector.setLength(maxDragRadius); // Constrain to a circular drag area
                }

                // Set bird image and Box2D body to constrained drag position
                birdImage.setPosition(catapultPosition.x + dragVector.x - birdImage.getWidth() / 2,
                    catapultPosition.y + dragVector.y - birdImage.getHeight() / 2);
                bird.setTransform(catapultPosition.x + dragVector.x, catapultPosition.y + dragVector.y, 0);
                // Calculate and update the trajectory points
                calculateTrajectory(dragVector.cpy().scl(-360f / PPM));
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isDragging = false;
                inaction = true;
                bird.setAwake(true);
                bird.setGravityScale(1);

                Vector2 launchVector = dragVector.cpy().scl(-360f / PPM); // Adjust the scale of the launch vector to control the launch power
                System.out.println(launchVector);
                bird.applyLinearImpulse(launchVector.scl(1f), bird.getWorldCenter(), true); // Apply impulse
                trajectoryPoints.clear();
            }
        });
    }

    public void renderRope() {
        Vector2 catapultPoint = new Vector2(288/PPM,240/PPM);
        if (!isDragging) {
            return;
        }
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined); // Match the stage's projection
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1); // Red color for the rope
        shapeRenderer.line(catapultPoint, new Vector2(bird.getPosition().x,bird.getPosition().y+20/PPM)); // Draw line from catapult to bird
        shapeRenderer.line(new Vector2(catapultPoint.x-25/PPM,catapultPoint.y-30/PPM), new Vector2(bird.getPosition().x,bird.getPosition().y-23/PPM)); // Draw line from catapult to bird
        shapeRenderer.end();
    }

    public void updateImagePositionFromBody() {

        Vector2 bodyPosition = bird.getPosition();
        float imageX = bodyPosition.x  - birdImage.getWidth() / 2;
        float imageY = bodyPosition.y  - birdImage.getHeight() / 2;

        birdImage.setPosition(imageX, imageY);
        setInitialPosition(new Vector3(imageX,imageY,0));
    }

    private void calculateTrajectory(Vector2 initialVelocity) {
        trajectoryPoints.clear(); // Clear previous points
        Vector2 startPosition = bird.getPosition(); // Start from bird's current position
        Vector2 gravity = world.getGravity(); // Get world gravity

        for (int i = 0; i < maxSteps; i++) {
            float t = i * timeStep; // Calculate time
            float x = startPosition.x + initialVelocity.x * t; // x = x0 + vx * t
            float y = startPosition.y + initialVelocity.y * t + 0.75f * gravity.y * t * t; // y = y0 + vy * t + 0.5 * g * t^2
            trajectoryPoints.add(new Vector2(x, y));
            if (x > 1000 /PPM) {
                break;
            }
        }
    }

    public void renderTrajectory() {

        if (!isDragging) return;
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined); // Match the stage's projection
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(4, 0, 0, 1); // Green color for trajectory points

        for (Vector2 point : trajectoryPoints) {

            shapeRenderer.circle(point.x, point.y, 3 / PPM, 16); // Draw small circles for trajectory
        }

        shapeRenderer.end();
    }

}
