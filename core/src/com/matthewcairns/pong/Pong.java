package com.matthewcairns.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pong extends Game {
    public SpriteBatch batch;
    public BitmapFont smallFont;
    public BitmapFont bigFont;

    public void create() {
        batch = new SpriteBatch();

        smallFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);
        bigFont = new BitmapFont(Gdx.files.internal("fonts/largeFont.fnt"), false);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        smallFont.dispose();
        bigFont.dispose();
    }
}