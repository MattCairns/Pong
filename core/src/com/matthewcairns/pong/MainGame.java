package com.matthewcairns.pong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class MainGame implements Screen {
    final Pong game;

    SpriteBatch batch;
    Texture paddleImage;
    Texture ballImage;
    Rectangle paddleLeft;
    Rectangle ball;

    int ballxSpeed;
    int ballySpeed;

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

    public void ball() {
        ballImage = new Texture(Gdx.files.internal("ball.png"));
        ball = new Rectangle();
        ball.x = 400;
        ball.y = 240;
        ball.width = 20;
        ball.height = 20;
    }

    public void ballMove() {
        ball.x = ball.x + ballxSpeed;
        ball.y = ball.y + ballySpeed;

        if(ball.y > 480 - ball.width) {
            ballySpeed *= -1;
        }

        if(ball.y < 0) {
            ballySpeed *= -1;
        }

        if(ball.x > 800 - ball.width) {
            ballxSpeed *= -1;
        }

        if(ball.x < 0) {
            ballxSpeed *= -1;
        }

        if(ball.overlaps(paddleLeft)) {
            ballxSpeed = MathUtils.random(5, 10);
            ballySpeed = MathUtils.random(5, 10);
        }
    }

    public void player() {
        paddleImage = new Texture(Gdx.files.internal("paddle.png"));
        paddleLeft = new Rectangle();
        paddleLeft.x = 10;
        paddleLeft.y = 240;
        paddleLeft.width = 20;
        paddleLeft.height = 70;
        System.out.println(paddleLeft);

    }

    public void movePlayer() {
        if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
            paddleLeft.y = paddleLeft.y + 10;
        if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
            paddleLeft.y = paddleLeft.y - 10;

        if(paddleLeft.y > camera.viewportHeight-80) { paddleLeft.y = camera.viewportHeight - 80; }
        if(paddleLeft.y < 10) { paddleLeft.y = 10; }
    }

    public MainGame(final Pong gam) {
        this.game = gam;

        ballxSpeed = 5;
        ballySpeed = 5;

        player();
        ball();

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
        batch.draw(paddleImage, paddleLeft.x, paddleLeft.y);
        batch.draw(ballImage, ball.x, ball.y);
        batch.end();

        netLines();

        movePlayer();
        ballMove();
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
