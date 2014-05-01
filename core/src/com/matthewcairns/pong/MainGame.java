package com.matthewcairns.pong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

// TODO: Put the ball into seperate class files.
// TODO: Change font to pong font.
public class MainGame implements Screen {
    final Pong game;

    SpriteBatch batch;
    Texture ballImage;
    Rectangle ball;

    Paddle paddleLeft;
    Paddle paddleRight;

    int scoreLeft;
    int scoreRight;

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

        if(ball.x > 800) {
            scoreLeft = scoreLeft + 1;
            ball.x = 400;
            ball.y = 240;
        }

        if(ball.x < 0) {
            scoreRight = scoreRight + 1;
            ball.x = 400;
            ball.y = 240;
        }

        if(ball.overlaps(paddleLeft.getPaddle())) {
            ballxSpeed = MathUtils.random(5, 10);
            ballySpeed = MathUtils.random(5, 10);
        }

        if(ball.overlaps(paddleRight.getPaddle())) {
            ballxSpeed = MathUtils.random(5, 10) * -1;
            ballySpeed = MathUtils.random(5, 10) * -1;
        }
    }


    public MainGame(final Pong gam) {
        this.game = gam;

        ballxSpeed = 5;
        ballySpeed = 5;

        scoreLeft = 0;
        scoreRight = 0;

        paddleLeft = new Paddle(10, 240, "left");
        paddleRight = new Paddle(770, 240, "right");

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
        batch.draw(paddleLeft.getPaddleImage(), paddleLeft.getPaddle().x, paddleLeft.getPaddle().y);
        batch.draw(paddleRight.getPaddleImage(), paddleRight.getPaddle().x, paddleRight.getPaddle().y);
        batch.draw(ballImage, ball.x, ball.y);
        batch.end();

        game.batch.begin();
        game.font.draw(game.batch, String.valueOf(scoreLeft), 100, 400);
        game.font.draw(game.batch, String.valueOf(scoreRight), 700, 400);
        game.batch.end();

        netLines();

        paddleLeft.movePlayer();
        paddleRight.movePlayer();

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
