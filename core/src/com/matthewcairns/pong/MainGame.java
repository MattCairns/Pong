package com.matthewcairns.pong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class MainGame implements Screen {
    final Pong game;

    SpriteBatch batch;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    int paddleLefty;
    int paddleRighty;

    public void netLines() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        int y = 480;
        for(int i=1; i<40; i++) {
            shapeRenderer.rect(400-5, y, 5, 10);
            y = y - 15;
        }
        shapeRenderer.end();
    }

    public void paddle(int x, int y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(x, y, 20, 70);
        shapeRenderer.end();
    }

    public void movePlayer() {
        if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
            paddleLefty = paddleLefty + 5;
        if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
            paddleLefty = paddleLefty - 5;

        if(paddleLefty > 480) { paddleLefty = 480 - 80; }
        if(paddleLefty < 0) { paddleLefty = 10; }
    }

    public MainGame(final Pong gam) {
        this.game = gam;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        paddleLefty = 240;
        paddleRighty = 250;
        shapeRenderer = new ShapeRenderer();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        netLines();
        paddle(10, paddleLefty);
        paddle(770,paddleRighty);

        movePlayer();
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
