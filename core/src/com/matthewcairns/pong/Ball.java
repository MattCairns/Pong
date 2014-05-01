package com.matthewcairns.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Matthew Cairns on 01/05/2014.
 * All rights reserved.
 */
public class Ball {
    int xSpeed = 10;
    int ySpeed = 10;

    private Rectangle ball;
    private Texture ballImage;

    int scoreLeft;
    int scoreRight;

    public Ball(int x, int y) {
        ballImage = new Texture(Gdx.files.internal("ball.png"));
        ball = new Rectangle();
        ball.x = x;
        ball.y = y;
        ball.width = 20;
        ball.height = 20;

        scoreLeft = 0;
        scoreRight = 0;
    }

    public void move(Rectangle left, Rectangle right) {
        ball.x = ball.x + xSpeed;
        ball.y = ball.y + ySpeed;

        if(ball.y > 480 - ball.width) {
            ySpeed *= -1;
        }

        if(ball.y < 0) {
            ySpeed *= -1;
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

        if(ball.overlaps(left)) {
            xSpeed = MathUtils.random(5, 10);
            ySpeed = MathUtils.random(5, 10);
        }

        if(ball.overlaps(right)) {
            xSpeed = MathUtils.random(5, 10) * -1;
            ySpeed = MathUtils.random(5, 10) * -1;
        }
    }

    public Rectangle getBall() {
        return ball;
    }
    public Texture getBallImage() {
        return ballImage;
    }

    public int getScoreLeft() {
        return scoreLeft;
    }

    public int getScoreRight() {
        return scoreRight;
    }
}
