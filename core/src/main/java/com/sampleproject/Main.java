package com.sampleproject;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sampleproject.screen.Level1;
import com.sampleproject.screen.Login;

public class Main extends Game {

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new Level1());
    }

}
