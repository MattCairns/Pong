package com.matthewcairns.pong;

/**
 * Created by Matthew Cairns on 28/04/2014.
 * All rights reserved.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    final Pong game;

    private String title = "PONG";
    private float titleWidth;
    private float titleHeight;


    OrthographicCamera camera;

    public MainMenuScreen(final Pong gam) {
        game = gam;

        titleWidth = game.bigFont.getBounds(title).width;
        titleHeight = game.bigFont.getBounds(title).height;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render (float delta) {
        //TODO: Revamp main menu.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.bigFont.draw(game.batch, "PONG", 400-titleWidth/2, 400);
        game.smallFont.draw(game.batch, "PLAY", 325, 250);
//        game.smallFont.draw(game.batch, "- 1 PLAYER", 250, 300);
//        game.smallFont.draw(game.batch, "- 2 PLAYER", 250, 250);
//        game.smallFont.draw(game.batch, "- CREDITS", 250, 200);

        game.batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new MainGame(game, true));
            dispose();
        }
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