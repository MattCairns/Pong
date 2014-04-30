package com.matthewcairns.pong;

/**
 * Created by Matthew on 28/04/2014.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    final Pong game;

    OrthographicCamera camera;

    public MainMenuScreen(final Pong gam) {
        game = gam;

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
        game.font.draw(game.batch, "PONG", 400, 400);
        game.font.draw(game.batch, "- 1 PLAYER", 400, 300);
        game.font.draw(game.batch, "- 2 PLAYER", 400, 250);
        game.font.draw(game.batch, "- CREDITS", 400, 200);

        game.batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new MainGame(game));
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