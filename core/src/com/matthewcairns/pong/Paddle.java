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

    public Paddle() {
        paddleImage = new Texture(Gdx.files.internal("paddle.png"));
        paddle = new Rectangle();
        paddle.x = 0;
        paddle.y = 240;
        paddle.width = 20;
        paddle.height = 70;
    }

    private void isLeft() {
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            paddle.y = paddle.y + PADDLE_SPEED;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            paddle.y = paddle.y - PADDLE_SPEED;
    }

    private void isRight() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            paddle.y = paddle.y + PADDLE_SPEED;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            paddle.y = paddle.y - PADDLE_SPEED;
    }

    public void ai(Rectangle ball) {

        if(paddle.y > ball.y) {
            paddle.y = paddle.y - 6;
            System.out.println("down");
        }

        if(paddle.y < ball.y) {
            paddle.y = paddle.y + 6;
            System.out.println("up");
        }
    }

    public void movePlayer(String rightOrLeft, Rectangle ball) {
        if(rightOrLeft.equals("left"))
            isLeft();
        if(rightOrLeft.equals("right"))
            isRight();

        if(rightOrLeft.equals("ai"))
            ai(ball);

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
