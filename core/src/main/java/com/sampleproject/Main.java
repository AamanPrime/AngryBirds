package com.sampleproject;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sampleproject.screen.*;

public class Main extends Game {
    public boolean musicStatus = false;
    public boolean soundStatus = false;
    @Override
    public void create() {

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        setScreen(new Won(this,1,0));
        setScreen(new StartingPage(this));
    }

}
