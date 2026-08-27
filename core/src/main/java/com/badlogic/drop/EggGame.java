package com.badlogic.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;

public class EggGame implements Screen {
    final Main game;

    public EggGame(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.shapeRenderer.setProjectionMatrix(game.viewport.getCamera().combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(Constants.sand, 0, 0, 16, 8);
        Egg[] yLevels = Arrays.copyOf(game.players, game.players.length);
        Arrays.sort(yLevels, (egg1, egg2) -> Float.compare(egg2.eggSprite.getY() - egg2.height, egg1.eggSprite.getY() - egg1.height));
        //Arrays.sort(yLevels, (egg1, egg2)-> Float.compare(egg1.height, egg2.height));

        for (int i = 0; i < game.players.length; i++) {
            yLevels[i].egg(game.spriteBatch);
        }
        game.spriteBatch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        if (game.players[0].controller.getButton(game.players[0].controller.getMapping().buttonDpadUp)) {
            for (int i = 0; i < game.players.length; i++) {
                game.debugToggle = true;
            }
        }
        if (game.players[0].controller.getButton(game.players[0].controller.getMapping().buttonDpadDown)) {
            for (int i = 0; i < game.players.length; i++) {
                game.debugToggle = false;
            }
        }

        if (game.debugToggle) {
            for (int i = 0; i < game.players.length; i++) yLevels[i].debug(game.shapeRenderer, game.viewport);
        }
        game.shapeRenderer.end();
        hitCheck();
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

    public void hitCheck() {
        for (int i = 0; i < game.players.length; i++) {
            for (int j = 0; j < game.players.length; j++) {
                if (i == j) continue;

                switch (game.players[i].Class) {
                    case SWORDSMAN: {
                        if (game.players[i].swordEquipped && game.players[i].swordRectangle.overlaps(game.players[j].eggRectangle) && !(game.players[j].shieldEquipped && (game.players[j].shieldRectangle.overlaps(game.players[i].swordRectangle))) && Math.abs(game.players[i].eggSprite.getY() - game.players[j].eggSprite.getY()) < 0.25) {
                            game.players[j].damage(15.0f);
                        }

                        if (game.players[i].swordRectangle.overlaps(game.players[j].shieldRectangle) && game.players[j].shieldEquipped && game.players[i].swordEquipped) {
                            game.players[i].parryTime = 2;
                        }
                    }
                    case BERSERKER: {
                        if (game.players[i].axeRectangle.overlaps(game.players[j].eggRectangle) && !(game.players[j].shieldEquipped && (game.players[j].shieldRectangle.overlaps(game.players[i].axeRectangle))) && Math.abs(game.players[i].eggSprite.getY() - game.players[j].eggSprite.getY()) < 0.25) {
                            game.players[j].damage(20.0f);
                        }

                        if (game.players[i].axeRectangle.overlaps(game.players[j].shieldRectangle) && game.players[j].shieldEquipped && game.players[i].parryTime <= 0) {
                            game.players[i].parryTime = 2;
                            game.players[j].shieldDownTime = 3;
                        }
                    }
                }
            }
        }
    }
}
