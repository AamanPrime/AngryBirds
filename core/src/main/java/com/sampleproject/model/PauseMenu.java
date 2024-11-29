package com.sampleproject.model;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sampleproject.Main;
import com.sampleproject.screen.Level1;
import com.sampleproject.screen.Level2;
import com.sampleproject.screen.Level3;
import com.sampleproject.screen.Levels;

import java.io.Serializable;

public class PauseMenu implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID
    transient Image play;
    transient Image restart;
    transient Image levels;
    transient Image musicon;
    transient Image musicoff;
    transient Image soundon;
    transient Image soundoff;
    transient Image menu;
    private Main main;
    private transient Stage stage;
    private transient Stage stage2;
    private Level3 level3;
    private Level1 level1;
    private Level2 level2;
    private final float PPM = 32f;
    private UserManager userManager;
    private UserManager.User user;
    private Image saveAndExit;
    public PauseMenu() {

    }

    public PauseMenu(Stage stage, Main main, Level3 level3, Stage stage2, UserManager.User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level3 = level3;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f/PPM);play.setPosition(1090/PPM, 600/PPM);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f/PPM);restart.setPosition(720/PPM,600/PPM);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f/PPM);levels.setPosition(900/PPM,600/PPM);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f/PPM);musicon.setPosition(800/PPM,500/PPM);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f/PPM);musicoff.setPosition(800/PPM,500/PPM);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f/PPM);soundon.setPosition(1000/PPM,500/PPM);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f/PPM);soundoff.setPosition(1000/PPM,500/PPM);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600/PPM,400/PPM);
        menu.setScale(0.5f/PPM);
        saveAndExit = new Image(new Texture("ui/saveIcon.png"));
        saveAndExit.setScale(1.5f/PPM);
        saveAndExit.setPosition(900/PPM,440/PPM);
        saveAndExit.setVisible(false);

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level3(main,user));
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                return false;
            }
        });
        saveAndExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                level3.saveGame(user.getUsername() + "_level3.ser");
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level3.inputMultiplexer.removeProcessor(stage2);
                level3.inputMultiplexer.addProcessor(stage);
                level3.addthis();

                level3.pause.setVisible(true);
                play.setVisible(false);
                soundoff.setVisible(false);
                soundon.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
                menu.setVisible(false);
                restart.setVisible(false);
                levels.setVisible(false);
                return true;
            }
        });
        stage2.addActor(menu);
        stage2.addActor(play);
        stage2.addActor(restart);
        stage2.addActor(levels);
        stage2.addActor(musicon);
        stage2.addActor(musicoff);
        stage2.addActor(soundon);
        stage2.addActor(soundoff);
        stage2.addActor(saveAndExit);



    }


    public PauseMenu(Stage stage, Main main, Level1 level1, Stage stage2, UserManager.User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level1 = level1;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f/PPM);play.setPosition(1090/PPM, 600/PPM);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f/PPM);restart.setPosition(720/PPM,600/PPM);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f/PPM);levels.setPosition(900/PPM,600/PPM);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f/PPM);musicon.setPosition(800/PPM,500/PPM);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f/PPM);musicoff.setPosition(800/PPM,500/PPM);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f/PPM);soundon.setPosition(1000/PPM,500/PPM);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f/PPM);soundoff.setPosition(1000/PPM,500/PPM);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600/PPM,400/PPM);
        menu.setScale(0.5f/PPM);
        saveAndExit = new Image(new Texture("ui/saveIcon.png"));
        saveAndExit.setScale(1.5f/PPM);
        saveAndExit.setPosition(900/PPM,440/PPM);
        saveAndExit.setVisible(false);



        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                main.setScreen(new Level1(main,user));

                System.out.println("something went wrong");
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                return false;
            }
        });

        saveAndExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level1.saveGame(user.getUsername() + "_level1.ser");
                main.setScreen(new Levels(main,user));
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level1.inputMultiplexer.removeProcessor(stage2);
                level1.inputMultiplexer.addProcessor(stage);
                level1.addthis();

                level1.pause.setVisible(true);
                play.setVisible(false);
                soundoff.setVisible(false);
                soundon.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
                menu.setVisible(false);
                restart.setVisible(false);
                levels.setVisible(false);
                return true;
            }
        });
        stage2.addActor(menu);
        stage2.addActor(play);
        stage2.addActor(restart);
        stage2.addActor(levels);
        stage2.addActor(musicon);
        stage2.addActor(musicoff);
        stage2.addActor(soundon);
        stage2.addActor(soundoff);
        stage2.addActor(saveAndExit);
    }

    public PauseMenu(Stage stage, Main main, Level2 level2, Stage stage2, UserManager.User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level2 = level2;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f/PPM);play.setPosition(1090/PPM, 600/PPM);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f/PPM);restart.setPosition(720/PPM,600/PPM);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f/PPM);levels.setPosition(900/PPM,600/PPM);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f/PPM);musicon.setPosition(800/PPM,500/PPM);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f/PPM);musicoff.setPosition(800/PPM,500/PPM);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f/PPM);soundon.setPosition(1000/PPM,500/PPM);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f/PPM);soundoff.setPosition(1000/PPM,500/PPM);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600/PPM,400/PPM);
        menu.setScale(0.5f/PPM);
        saveAndExit = new Image(new Texture("ui/saveIcon.png"));
        saveAndExit.setScale(1.5f/PPM);
        saveAndExit.setPosition(900/PPM,440/PPM);
        saveAndExit.setVisible(false);



        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());

                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("music",user.getUsername());
                level2.backgroundMusic.stop();
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                userManager.setSetting("sound",user.getUsername());
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                main.setScreen(new Level2(main,user));

                System.out.println("something went wrong");
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                return false;
            }
        });

        saveAndExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main,user));
                level2.saveGame(user.getUsername() + "_level2.ser");
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level2.inputMultiplexer.removeProcessor(stage2);
                level2.inputMultiplexer.addProcessor(stage);
                level2.addthis();
                level2.pause.setVisible(true);
                play.setVisible(false);
                soundoff.setVisible(false);
                soundon.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
                menu.setVisible(false);
                restart.setVisible(false);
                levels.setVisible(false);
                return true;
            }
        });
        stage2.addActor(menu);
        stage2.addActor(play);
        stage2.addActor(restart);
        stage2.addActor(levels);
        stage2.addActor(musicon);
        stage2.addActor(musicoff);
        stage2.addActor(soundon);
        stage2.addActor(soundoff);
        stage2.addActor(saveAndExit);



    }

    public void show() {
        play.setVisible(true);
        restart.setVisible(true);
        levels.setVisible(true);
        saveAndExit.setVisible(true);
        if (userManager.getSettings("sound",user.getUsername())) {
            soundon.setVisible(true);
            soundoff.setVisible(false);

        }
        else {
            soundon.setVisible(false);
            soundoff.setVisible(true);

        }
        if (userManager.getSettings("music",user.getUsername())) {
            musicon.setVisible(true);
            musicoff.setVisible(false);

        }
        else {
            musicon.setVisible(false);
            musicoff.setVisible(true);

        }
        menu.setVisible(true);

    }


}
