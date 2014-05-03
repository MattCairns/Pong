package com.matthewcairns.pong;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Matthew Cairns on 28/04/2014.
 * All rights reserved.
 */
public class MainGame implements Screen {
    final Pong game;

    SpriteBatch batch;

    Paddle paddleLeft;
    Paddle paddleRight;

    String l;
    String r;

    Ball ball;

    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    public void netLines() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        int y = 480;
        for(int i=1; i<40; i++) {
            shapeRenderer.rect(400-5, y, 5, 10);
            y = y - 15;
        }
        shapeRenderer.end();
    }

    public MainGame(final Pong gam, String left, String right) {
        this.game = gam;
        l = left;
        r = right;

        paddleLeft = new Paddle();
        paddleRight = new Paddle();

        paddleLeft.getPaddle().x = 10;
        paddleRight.getPaddle().x = 770;

        ball = new Ball(400, 240);

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
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(paddleLeft.getPaddleImage(), paddleLeft.getPaddle().x, paddleLeft.getPaddle().y);
        game.batch.draw(paddleRight.getPaddleImage(), paddleRight.getPaddle().x, paddleRight.getPaddle().y);
        game.batch.draw(ball.getBallImage(), ball.getBall().x, ball.getBall().y);
        game.bigFont.draw(game.batch, String.valueOf(ball.getScoreLeft()), 300, 450);
        game.bigFont.draw(game.batch, String.valueOf(ball.getScoreRight()), 450, 450);
        game.batch.end();

        netLines();

        paddleLeft.movePlayer(l, ball.getBall());
        paddleRight.movePlayer(r, ball.getBall());

        ball.move(paddleLeft.getPaddle(), paddleRight.getPaddle());

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            game.setScreen(new MainMenuScreen(game));
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
