package com.sampleproject.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampleproject.Main;
import com.sampleproject.model.LevelIcon;
import com.sampleproject.model.UserManager;

public class Levels implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private Main main;
    private HomeScreen homeScreen;
    private Music backgroundMusic;
    private UserManager.User user;
    private Image level1;
    private Image level2;
    private Image level3;
    private UserManager userManager;
    private Image loadButton1;
    private Image loadButton2;
    private Image loadButton3;
    public Levels(Main main,UserManager.User user) {
        this.main = main;
        this.userManager = new UserManager();
        this.user = this.userManager.getUsers(user.getUsername());
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewport = new StretchViewport(1920,1000,camera);
        stage = new Stage(viewport, batch);
        background = new Texture("ui/levelshomescreen.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/song.mp3"));
        Image backicon = new Image(new Texture("ui/back.png"));
        backicon.setScale(0.5f);
        backicon.rotateBy(-5);
        backicon.setPosition(50, 50);

        if (user.isMusicStatus()) {
            backgroundMusic.play();
        }

        loadButton1 = new Image(new Texture("ui/loadButton.png"));
        loadButton1.setScale(0.25f);
        loadButton2 = new Image(new Texture("ui/loadButton.png"));
        loadButton2.setScale(0.25f);
        loadButton3 = new Image(new Texture("ui/loadButton.png"));
        loadButton3.setScale(0.25f);

        backicon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (homeScreen != null) {
                    main.setScreen(homeScreen);
                }
                else {
                    main.setScreen(new HomeScreen(main,user));
                }
               return false;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backicon.addAction(Actions.sequence(
                    Actions.scaleTo(0.52f,0.52f,0.2f)

                ));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backicon.addAction(Actions.sequence(
                    Actions.scaleTo(0.50f,0.50f,0.2f)
                ));
            }

        });
        level1 = new LevelIcon(stage).addLevel(320,400,0,1);
        loadButton1.setPosition(360,320);
        level2 = new LevelIcon(stage).addLevel(820,400,0,2);
        loadButton2.setPosition(820+40,320);
        level3 = new LevelIcon(stage).addLevel(1320,400,0,3);
        loadButton3.setPosition(1320+40,320);
        if (user.getLevel1score() >= 100 && user.getLevel1score() < 300) {
            level1 = new LevelIcon(stage).addLevel(320,400,1,1);
        }
        else if (user.getLevel1score() >= 300 && user.getLevel1score() < 500) {
            level1 = new LevelIcon(stage).addLevel(320,400,2,1);
        }
        else if (user.getLevel1score() >= 500) {
            level1 = new LevelIcon(stage).addLevel(320,400,3,1);
        }

        if (user.getLevel2score() >= 100 && user.getLevel2score() < 300) {
            level2 = new LevelIcon(stage).addLevel(820,400,1,2);

        }
        else if (user.getLevel2score() >= 300 && user.getLevel2score() < 500) {
            level2 = new LevelIcon(stage).addLevel(820,400,2,2);
        }
        else if (user.getLevel2score() >= 500) {
            level2 = new LevelIcon(stage).addLevel(820,400,3,2);
        }

        if (user.getLevel3score() >= 100 && user.getLevel3score() < 300) {
            level3 = new LevelIcon(stage).addLevel(1320,400,1,3);
        }
        else if (user.getLevel3score() >= 300 && user.getLevel3score() < 500) {
            level3 = new LevelIcon(stage).addLevel(1320,400,2,3);
        }
        else if (user.getLevel3score() >= 500){
            level3 = new LevelIcon(stage).addLevel(1320,400,3,3);
        }




        level1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                main.setScreen(new Level1(main,user));
                return true;
            }
        });
        level2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level2(main,user));
                return true;
            }
        });
        level3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(new Level3(main,user));
                return true;
            }
        });

        loadButton1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Level1 temp = Level1.loadGame(user.getUsername() + "_level1.ser");
                if (temp != null) {
                    main.setScreen(new Level1(main,user,temp.getAllBlock(),temp.getAllRock(),temp.getAllPig(), temp.getAllBirds(),temp.getAllGlass()));
                }
                else {
                    System.out.println("Game Not Found");
                }
                return true;
            }
        });

        loadButton2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Level2 temp = Level2.loadGame(user.getUsername() + "_level2.ser");
                if (temp != null) {
                    main.setScreen(new Level2(main,user,temp.getAllBlock(),temp.getAllRock(),temp.getAllPig(), temp.getAllBirds()));
                }
                else {
                    System.out.println("Game Not Found");
                }
                return true;
            }
        });

        loadButton3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Level3 temp = Level3.loadGame(user.getUsername() + "_level3.ser");
                if (temp != null) {
                    main.setScreen(new Level3(main,user,temp.getAllBlock(),temp.getAllRock(),temp.getAllPig(), temp.getAllBirds()));
                }
                else {
                    System.out.println("Game Not Found");
                }
                return true;
            }
        });

        stage.addActor(backicon);
        stage.addActor(loadButton1);
        stage.addActor(loadButton2);
        stage.addActor(loadButton3);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        batch.begin();
        batch.draw(background, 0, 0,1920,1000);
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

    }
}
