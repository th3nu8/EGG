package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Arrow {
    Sprite arrowSprite;
    Rectangle arrowRectangle;
    boolean dir;
    float xVelocity;
    float height;
    float gravity;
    float yVelocity;

    public Arrow(boolean direction, float xPos, float yPos, float yV) {
        arrowSprite = new Sprite(Constants.arrow);
        arrowSprite.setSize(.35f, .15f);
        arrowSprite.setY(yPos + .35f);
        arrowSprite.setX(xPos + .35f);
        arrowRectangle = new Rectangle();
        arrowRectangle.setSize(.25f, .05f);

        dir = direction;
        xVelocity = 10.0f;
        height = .15f;
        gravity = 0.1f;
        yVelocity = yV;
    }

    public boolean loop(Main game) {
        if (!dir) {
            arrowSprite.setX(arrowSprite.getX() + xVelocity * Gdx.graphics.getDeltaTime());
            arrowSprite.setY(arrowSprite.getY() + yVelocity/3 * Gdx.graphics.getDeltaTime());
            height -= gravity * Gdx.graphics.getDeltaTime();
        } else {
            arrowSprite.flip(true, false);
            arrowSprite.setX(arrowSprite.getX() - xVelocity * Gdx.graphics.getDeltaTime());
            arrowSprite.setY(arrowSprite.getY() + yVelocity/3 * Gdx.graphics.getDeltaTime());
            height -= gravity * Gdx.graphics.getDeltaTime();
        }
        arrowSprite.draw(game.spriteBatch);
        System.out.println(height);
        arrowRectangle.setCenter(arrowSprite.getBoundingRectangle().x + .17f, arrowSprite.getBoundingRectangle().y + .075f);

        if (height <= 0) return true; else return false;
    }
}
