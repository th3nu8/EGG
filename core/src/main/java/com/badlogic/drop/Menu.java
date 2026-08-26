package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class Menu implements Screen {
    final Main game;
    Controller controller;
    Sprite logo;

    public Menu(Main game) {
        this.game = game;
        controller = Constants.controllers.get(0);
        logo = new Sprite(Constants.logo);
        logo.setSize(3f, 3f);
        logo.setCenterX(6f);
        logo.setY(5f);
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.spriteBatch.begin();

        logo.draw(game.spriteBatch);
        game.font.getData().setScale(0.04f);
        game.font.setColor(Color.GREEN);
        game.font.draw(game.spriteBatch, "PLAY", 5.3f, 3f);
        game.font.getData().setScale(0.02f);
        if (game.players.length > 0) {
            game.font.setColor(Color.RED);
            game.font.draw(game.spriteBatch, "Player 1", 0.5f, 1f);
            game.font.draw(game.spriteBatch, game.players[0].controller.getName(), 0.5f, 0.5f);
        }
        if (game.players.length > 1) {
            game.font.setColor(Color.BLUE);
            game.font.draw(game.spriteBatch, "Player 2", 3.5f, 1f);
            game.font.draw(game.spriteBatch, game.players[1].controller.getName(), 3.5f, 0.5f);
        }
        if (game.players.length > 2) {
            game.font.setColor(Color.GREEN);
            game.font.draw(game.spriteBatch, "Player 3", 6.5f, 1f);
            game.font.draw(game.spriteBatch, game.players[2].controller.getName(), 6.56f, 0.5f);
        }
        if (game.players.length > 3) {
            game.font.setColor(Color.YELLOW);
            game.font.draw(game.spriteBatch, "Player 4", 9.5f, 1f);
            game.font.draw(game.spriteBatch, game.players[3].controller.getName(), 9.5f, 0.5f);

        }

        game.spriteBatch.end();

        if (controller.getButton(controller.getMapping().buttonA)) {
            game.setScreen(new EggGame(game));
            dispose();
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
