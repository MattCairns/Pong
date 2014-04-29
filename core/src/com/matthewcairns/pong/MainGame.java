package com.matthewcairns.pong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainGame implements Screen {
    final Pong game;

    SpriteBatch batch;
    Texture paddleImage;
    Rectangle paddle;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

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

    public void movePlayer() {
        if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
            paddle.y = paddle.y + 5;
        if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
            paddle.y = paddle.y - 5;

        if(paddle.y > 480-70) { paddle.y = 480 - 70; }
        if(paddle.y < 0) { paddle.y = 0; }
    }

    public MainGame(final Pong gam) {
        this.game = gam;
        paddleImage = new Texture(Gdx.files.internal("paddle.png"));
        paddle = new Rectangle();
        paddle.x = 10;
        paddle.y = 240;
        paddle.width = 20;
        paddle.height = 70;


        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        shapeRenderer = new ShapeRenderer();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(paddleImage, paddle.x, paddle.y);
        batch.end();

        netLines();


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
