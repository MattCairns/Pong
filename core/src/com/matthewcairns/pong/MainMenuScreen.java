package com.matthewcairns.pong;

/**
 * Created by Matthew Cairns on 28/04/2014.
 * All rights reserved.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen implements Screen {
    final Pong game;

    private String title = "PONG";
    private float titleWidth;

    Stage stage;
    TextButton titleButton;
    TextButton onePlayer;
    TextButton twoPlayer;
    TextButton aiVSai;

    TextButton.TextButtonStyle textButtonStyle;
    TextButton.TextButtonStyle titleButtonStyle;

    OrthographicCamera camera;

    public MainMenuScreen(final Pong gam) {
        game = gam;

        titleWidth = game.bigFont.getBounds(title).width;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        textButtonStyle = new TextButton.TextButtonStyle();
        titleButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = gam.smallFont;
        titleButtonStyle.font = gam.bigFont;

        titleButton = new TextButton("PONG", titleButtonStyle);
        titleButton.setPosition(275, 350);
        onePlayer = new TextButton("PLAYER VS COMPUTER", textButtonStyle);
        onePlayer.setPosition(100, 300);
        twoPlayer = new TextButton("PLAYER VS PLAYER", textButtonStyle);
        twoPlayer.setPosition(100, 250);
        aiVSai = new TextButton("COMPUTER VS COMPUTER", textButtonStyle);
        aiVSai.setPosition(100, 200);

        stage.addActor(titleButton);
        stage.addActor(onePlayer);
        stage.addActor(twoPlayer);
        stage.addActor(aiVSai);

        onePlayer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gam.setScreen(new MainGame(game, "ai", "right"));
            }
        });

        twoPlayer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gam.setScreen(new MainGame(game, "left", "right"));
            }
        });

        aiVSai.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gam.setScreen(new MainGame(game, "ai", "ai"));
            }
        });

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }


    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.draw();


        game.batch.end();

    }

    @Override
    public void resume() {}
    @Override
    public void pause() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void hide() {}
    @Override
    public void show() {}
    @Override
    public void dispose() {}
}