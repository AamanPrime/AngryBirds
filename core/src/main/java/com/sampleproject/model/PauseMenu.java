package com.sampleproject.model;

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

public class PauseMenu {

    Image play;
    Image restart;
    Image levels;
    Image musicon;
    Image musicoff;
    Image soundon;
    Image soundoff;
    Image menu;
    private Main main;
    private Stage stage;
    private Stage stage2;
    private Level3 level3;
    private Level1 level1;
    private Level2 level2;
    public PauseMenu(Stage stage, Main main, Level3 level3, Stage stage2) {
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level3 = level3;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f);play.setPosition(1090, 600);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f);restart.setPosition(720,600);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);levels.setPosition(900,600);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f);musicon.setPosition(800,500);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f);musicoff.setPosition(800,500);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f);soundon.setPosition(1000,500);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f);soundoff.setPosition(1000,500);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600,400);
        menu.setScale(0.5f);

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = true;
                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = false;
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = true;
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = false;
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });



        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level3(main));
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main));
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level3.inputMultiplexer.removeProcessor(stage2);
                level3.inputMultiplexer.addProcessor(stage);
                level3.addthis();
                level3.backgroundMusic.play();
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



    }

    public PauseMenu(Stage stage, Main main, Level1 level1, Stage stage2) {
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level1 = level1;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f);play.setPosition(1090, 600);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f);restart.setPosition(720,600);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);levels.setPosition(900,600);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f);musicon.setPosition(800,500);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f);musicoff.setPosition(800,500);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f);soundon.setPosition(1000,500);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f);soundoff.setPosition(1000,500);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600,400);
        menu.setScale(0.5f);

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = true;
                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = false;
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = true;
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = false;
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });

        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (level1 != null) {
                    main.setScreen(new Level1(main));
                }
                else if (level2 != null) {
                    main.setScreen(new Level2(main));
                }
                else if (level3 != null) {
                    main.setScreen(new Level3(main));
                }
                System.out.println("something went wrong");
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main));
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level1.inputMultiplexer.removeProcessor(stage2);
                level1.inputMultiplexer.addProcessor(stage);
                level1.addthis();
                level1.backgroundMusic.play();
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



    }
    public PauseMenu(Stage stage, Main main, Level2 level2, Stage stage2) {
        this.main = main;
        this.stage = stage;
        this.stage2 = stage2;
        this.level2 = level2;
        play = new Image(new Texture("ui/play.png"));play.setScale(0.56f);play.setPosition(1090, 600);play.setVisible(false);
        restart = new Image(new Texture("ui/restart.png"));restart.setScale(0.5f);restart.setPosition(720,600);restart.setVisible(false);
        levels = new Image(new Texture("ui/menu.png"));levels.setScale(0.5f);levels.setPosition(900,600);levels.setVisible(false);
        musicon = new Image(new Texture("ui/musicon.png"));musicon.setScale(0.6f);musicon.setPosition(800,500);musicon.setVisible(false);
        musicoff = new Image(new Texture("ui/musicoff.png"));musicoff.setScale(0.6f);musicoff.setPosition(800,500);musicoff.setVisible(false);
        soundon = new Image(new Texture("ui/soundon.png"));soundon.setScale(0.6f);soundon.setPosition(1000,500);soundon.setVisible(false);
        soundoff = new Image(new Texture("ui/soundoff.png"));soundoff.setScale(0.6f);soundoff.setPosition(1000,500);soundoff.setVisible(false);
        menu = new Image(new Texture("ui/pauseMenu.png"));menu.setVisible(false);
        menu.setPosition(600,400);
        menu.setScale(0.5f);

        musicoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = true;
                musicon.setVisible(true);
                musicoff.setVisible(false);

                return true;
            }
        });
        musicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.musicStatus = false;
                musicon.setVisible(false);
                musicoff.setVisible(true);
                return true;
            }
        });

        soundoff.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = true;
                soundon.setVisible(true);
                soundoff.setVisible(false);
                return true;
            }
        });

        soundon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.soundStatus = false;
                soundon.setVisible(false);
                soundoff.setVisible(true);
                return true;
            }
        });



        restart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (level1 != null) {
                    main.setScreen(new Level1(main));
                }
                else if (level2 != null) {
                    main.setScreen(new Level2(main));
                }
                else if (level3 != null) {
                    main.setScreen(new Level3(main));
                }
                System.out.println("something went wrong");
                return false;
            }
        });

        levels.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Levels(main));
                return false;
            }
        });

        play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level2.inputMultiplexer.removeProcessor(stage2);
                level2.inputMultiplexer.addProcessor(stage);
                level2.addthis();
                level2.backgroundMusic.play();
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



    }




    public void show() {
        play.setVisible(true);
        restart.setVisible(true);
        levels.setVisible(true);
        if (main.soundStatus) {
            soundon.setVisible(true);
            soundoff.setVisible(false);

        }
        else {
            soundon.setVisible(false);
            soundoff.setVisible(true);

        }
        if (main.musicStatus) {
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
