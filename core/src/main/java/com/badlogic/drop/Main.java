package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;
import java.util.Arrays;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    SpriteBatch spriteBatch;
    FitViewport viewport;
    FPSLogger fpsLogger;
    Egg[] players;
    ShapeRenderer shapeRenderer;
    boolean menu = true;
    public BitmapFont font;
    public boolean debugToggle = false;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(12, 8);
        viewport.apply();
        players = new Egg[Constants.controllers.size];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Egg(Constants.controllers.get(i), Constants.color[i], Constants.Class.SWORDSMAN, this);
        }
        fpsLogger = new FPSLogger();
        font = new BitmapFont();

        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        setScreen(new Menu(this));
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

//    @Override
//    public void render() {
//        super.render();
//    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
