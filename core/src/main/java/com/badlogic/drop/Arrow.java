package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Arrow {
    Sprite arrowSprite;
    Rectangle arrowRectangle;
    boolean dir;
    float velocity;
    float height;
    float gravity;

    public Arrow(boolean direction, float xPos, float yPos) {
        arrowSprite = new Sprite(Constants.arrow);
        arrowSprite.setSize(.35f, .15f);
        arrowSprite.setY(yPos + .35f);
        arrowSprite.setX(xPos + .35f);
        arrowRectangle = new Rectangle();
        arrowRectangle.setSize(.25f, .05f);

        dir = direction;
        velocity = 5.0f;
        height = .25f;
        gravity = 0.1f;
    }

    public boolean loop(Main game) {
        if (!dir) {
            arrowSprite.setX(arrowSprite.getX() + velocity * Gdx.graphics.getDeltaTime());
            height -= gravity * Gdx.graphics.getDeltaTime();
        } else {
            arrowSprite.flip(true, false);
            arrowSprite.setX(arrowSprite.getX() - velocity * Gdx.graphics.getDeltaTime());
            height -= gravity * Gdx.graphics.getDeltaTime();
        }
        arrowSprite.draw(game.spriteBatch);
        System.out.println(height);
        arrowRectangle.setCenter(arrowSprite.getBoundingRectangle().x + .17f, arrowSprite.getBoundingRectangle().y + .075f);

        if (height <= 0) return true; else return false;
    }
}
