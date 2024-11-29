package com.sampleproject;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sampleproject.model.UserManager;
import com.sampleproject.screen.*;

import java.io.Serializable;

public class Main extends Game implements Serializable {
    UserManager userManager = new UserManager();

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new StartingPage(this));
    }

}
