package com.matthewcairns.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Matthew Cairns on 03/05/2014.
 * All rights reserved.
 */
public class Credits implements Screen {
    final Pong game;

    String text0 = "CREATED BY";
    String text1 = "MATTHEW CAIRNS";
    String text2 = "ORIGINALLY CREATED BY";
    String text3 = "ATARI INC.";
    OrthographicCamera camera;

    public Credits(final Pong gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.smallFont.draw(game.batch, text0, 400-game.smallFont.getBounds(text0).width/2, 400);
        game.smallFont.draw(game.batch, text1, 400-game.smallFont.getBounds(text1).width/2, 350);
        game.smallFont.draw(game.batch, text2, 400-game.smallFont.getBounds(text2).width/2, 150);
        game.smallFont.draw(game.batch, text3, 400-game.smallFont.getBounds(text3).width/2, 100);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
    }
}
