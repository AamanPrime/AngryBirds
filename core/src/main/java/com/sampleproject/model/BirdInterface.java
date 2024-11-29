package com.sampleproject.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Queue;

public interface BirdInterface {
    int getHealth();

    void setHealth(int health);

    void setPosition(float x, float y);

    void getBirds(float x, float y, Queue<BirdInterface> allBirds);

    void Attack();

    void launch();

    void setVelocity(float x, float y);

    void updateImagePositionFromBody();

    boolean isCanDestory();

    void setCanDestory(boolean canDestory);

    boolean getInAction();

    void setInAction(boolean inAction);

    float getActionTime();

    void setActionTime(float actionTime);

    void activateSuperPower();

    boolean isUsePower();

    void setUsePower(boolean usePower);

    Vector2 getPosition();

    Vector2 getVelocity();

    public Body getBird();
    void renderRope();
    void renderTrajectory();

}
