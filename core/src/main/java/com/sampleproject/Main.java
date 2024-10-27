package com.sampleproject;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sampleproject.Exception.GameNotFoundException;
import com.sampleproject.screen.*;

public class Main extends Game {
    public boolean musicStatus = false;
    public boolean soundStatus = false;
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        try {
            setScreen(new LoadingScreen(this));
        }
        catch (GameNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

}
