package com.sampleproject.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;

public class TouchInputHandler implements InputProcessor {
    private Rectangle imageBounds;

    public TouchInputHandler(Rectangle imageBounds) {
        this.imageBounds = imageBounds;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        float flippedY = Gdx.graphics.getHeight() - screenY;


        if (imageBounds.contains(screenX, flippedY)) {

            //Login.processTextInput();

            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }


    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
}
