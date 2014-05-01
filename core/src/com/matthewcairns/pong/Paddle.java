package com.matthewcairns.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Matthew Cairns on 01/05/2014.
 * All rights reserved.
 */

public class Paddle {

    private int PADDLE_SPEED = 10;

    private Rectangle paddle;
    private Texture paddleImage;

    private String leftOrRight;

    public Paddle(int x, int y, String lr) {
        paddleImage = new Texture(Gdx.files.internal("paddle.png"));
        paddle = new Rectangle();
        paddle.x = x;
        paddle.y = y;
        paddle.width = 20;
        paddle.height = 70;

        leftOrRight = lr;
    }

    public void movePlayer() {
        if(leftOrRight.equals("left")) {
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                paddle.y = paddle.y + PADDLE_SPEED;
            if (Gdx.input.isKeyPressed(Input.Keys.S))
                paddle.y = paddle.y - PADDLE_SPEED;
        }

        if(leftOrRight.equals("right")) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP))
                paddle.y = paddle.y + PADDLE_SPEED;
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                paddle.y = paddle.y - PADDLE_SPEED;
        }

        if(paddle.y > 480-80) { paddle.y = 480-80; }
        if(paddle.y < 10) { paddle.y = 10; }
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public Texture getPaddleImage() {
        return paddleImage;
    }
}
