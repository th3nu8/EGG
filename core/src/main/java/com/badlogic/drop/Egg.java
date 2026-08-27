package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Egg {
    private float xVelocity = 0;
    private float yVelocity = 0;
    private float heightVelocity = 0;
    public float height = 0;
    private float jumpDelay = 0;
    private float diveDelay = 0;
    public boolean shieldEquipped = false;
    private boolean flipped = false;
    public boolean swordEquipped = false;
    public float iframes;
    public float health = 100;
    public float parryTime = 0;
    public float menuPress = 1;
    public float shieldDownTime = 0;

    Sprite shadowSprite;
    Sprite eggSprite;
    Sprite shieldSprite;
    Sprite swordSprite;
    Sprite axeSprite;
    Controller controller;
    Rectangle eggRectangle;
    Rectangle shieldRectangle;
    Rectangle swordRectangle;
    Rectangle axeRectangle;
    Color color;
    Constants.Class Class;
    BitmapFont font;
    TextureRegion health75Region = new TextureRegion(Constants.health75);
    TextureRegion health50Region = new TextureRegion(Constants.health50);
    TextureRegion health25Region = new TextureRegion(Constants.health25);
    TextureRegion deadRegion = new TextureRegion(Constants.dead);

    final Main game;
    EggGame eggGame;

    public Egg(Controller controller, Color color, Constants.Class Class, Main game) {
        shadowSprite = new Sprite(Constants.shadowTexture);
        eggSprite = new Sprite(Constants.eggTexture);
        eggSprite.setSize(0.9f, 0.9f);
        shadowSprite.setSize(0.5f, 0.5f);
        eggRectangle = new Rectangle(0, 0f, 0.35f, 0.5f);
        this.controller = controller;
        this.color = color;
        this.Class = Class;
        this.game = game;

        if (color.equals(Color.RED)) {
            eggSprite.setX(0);
            eggSprite.setY(0);
            eggSprite.setCenter(0,0);
        } else if (color.equals(Color.BLUE)) {
            eggSprite.setX(12);
            eggSprite.setY(6.5f);
            eggSprite.setCenter(12,6.5f);
        } else if (color.equals(Color.GREEN)) {
            eggSprite.setX(0);
            eggSprite.setY(6.5f);
            eggSprite.setCenter(0,6.5f);
        } else if (color.equals(Color.YELLOW)) {
            eggSprite.setX(12);
            eggSprite.setY(0);
            eggSprite.setCenter(12,0);
        }

        eggRectangle.setCenter(0, 0);
    }

    public void egg(SpriteBatch spriteBatch) {
        if (health > 0) {
            font = new BitmapFont();
            float delta = Gdx.graphics.getDeltaTime();
            eggSprite.setColor(color);
            movement(delta);
            attack();
            shadow(spriteBatch, delta);

            if (iframes > 0) {
                iframes -= delta;
            }
        }
        draw(spriteBatch);
    }

    public void shadow(SpriteBatch spriteBatch, float delta) {
        shadowSprite.setX(eggSprite.getX() + 0.22f + height / 10 * delta);
        shadowSprite.setY(eggSprite.getY() - ((height + heightVelocity) * delta) - 0.15f + 0.18f);
        shadowSprite.setSize(0.5f - height / 5 * delta, 0.5f - height / 5 * delta);
        shadowSprite.draw(spriteBatch);
    }

    public void movement(float delta) {
        float maxVelocity = 2.5f;
        if (Class == Constants.Class.SWORDSMAN) {
            maxVelocity = 1.5f;
        } else if (Class == Constants.Class.ARCHER) {
            maxVelocity = 2.5f;
        } else if (Class == Constants.Class.BERSERKER) {
            maxVelocity = 3.5f;
        }

        float controllerXAxis = controller.getAxis(controller.getMapping().axisLeftX);
        float controllerYAxis = controller.getAxis(controller.getMapping().axisLeftY);

        if (xVelocity < maxVelocity && xVelocity > -maxVelocity && (controllerXAxis < -0.1 || controllerXAxis > 0.1)) {
            xVelocity += maxVelocity * (Math.round(controller.getAxis(controller.getMapping().axisLeftX)));
        } else {
            if (xVelocity < 0) xVelocity += 0.25f;
            if (xVelocity > 0) xVelocity -= 0.25f;
        }
        if (yVelocity < maxVelocity && yVelocity > -maxVelocity && (controllerYAxis < -0.25 || controllerYAxis > 0.25)) {
            yVelocity -= maxVelocity * (Math.round(controller.getAxis(controller.getMapping().axisLeftY)));
        } else {
            if (yVelocity < 0) yVelocity += 0.25f;
            if (yVelocity > 0) yVelocity -= 0.25f;
        }

        if (controller.getButton(controller.getMapping().buttonA) && height == 0 && jumpDelay <= 0) {
            heightVelocity = 5;
            jumpDelay = 1.25f;
        }

        if (!(jumpDelay <= 0)) jumpDelay -= 1 * delta;
        jumpDelay = Math.round(jumpDelay * 100f) / 100f;

        height += heightVelocity;
        height = Math.round(height * 10.0f) / 10.0f;

        if (height > 0) heightVelocity -= Constants.gravity;
        else heightVelocity = 0;

        if (controller.getButton(controller.getMapping().buttonX) && diveDelay <= 0) {
            if (xVelocity > 0) {
                xVelocity = maxVelocity * 5;
                diveDelay = 1;
            } else if (xVelocity < 0) {
                xVelocity = -maxVelocity * 5;
                diveDelay = 1;
            }
        }

        if (diveDelay > 0) diveDelay -= 1 * delta;

        shield();

        eggSprite.translateX(xVelocity * delta);

        if (!(eggSprite.getY() >= 6.5 && controller.getAxis(controller.getMapping().axisLeftY) < 0))
            eggSprite.translateY(yVelocity * delta);
        eggSprite.translateY(heightVelocity * delta);

        eggSprite.setX(MathUtils.clamp(eggSprite.getX(), 0, 11));
        eggSprite.setY(MathUtils.clamp(eggSprite.getY(), 0, 8));

        eggRectangle.setCenter(eggSprite.getBoundingRectangle().getCenter(new Vector2()));
        switch (Class) {
            case SWORDSMAN : {
                swordRectangle.setCenter(swordSprite.getBoundingRectangle().getCenter(new Vector2()));
                shieldRectangle.setCenter(shieldSprite.getBoundingRectangle().getCenter(new Vector2()));
            }
            case BERSERKER: axeRectangle.setCenter(axeSprite.getBoundingRectangle().getCenter(new Vector2()));
        }
    }

    public void shield() {
        if (Class != Constants.Class.BERSERKER) {
            if (shieldDownTime <= 0) {
                if (controller.getButton(controller.getMapping().buttonL1)) {
                    xVelocity = Math.round(xVelocity / 4 * 10f) / 10f;
                    yVelocity = Math.round(yVelocity / 4 * 10f) / 10f;
                    xVelocity = Math.round(xVelocity * 4f) / 4f;
                    yVelocity = Math.round(yVelocity * 4f) / 4f;
                    shieldSprite.setAlpha(1.0f);
                    shieldEquipped = true;
                } else {
                    shieldSprite.setAlpha(0.0f);
                    shieldEquipped = false;
                }
            } else {
                shieldDownTime -= Gdx.graphics.getDeltaTime();
                shieldSprite.setAlpha(0.0f);
                shieldEquipped = false;
            }
            if (xVelocity > 0) {
                shieldSprite.setX(eggSprite.getX() + 0.5f);
            } else if (xVelocity < 0) {
                shieldSprite.setX(eggSprite.getX() + 0.1f);
            }
            shieldSprite.setY(eggSprite.getY() + 0.2f);
        }
    }

    public void attack() {
        if (Class == Constants.Class.SWORDSMAN) {
            if (controller.getButton(controller.getMapping().buttonR1) && !shieldEquipped && parryTime <= 0) {
                swordSprite.setAlpha(1.0f);
                swordEquipped = true;
            } else {
                swordSprite.setAlpha(0.0f);
                swordEquipped = false;
                parryTime -= Gdx.graphics.getDeltaTime();
            }
            if (xVelocity > 0) {
                swordSprite.setX(eggSprite.getX() + 0.5f);
                if (flipped) {
                    swordSprite.flip(true, false);
                }
                flipped = false;
            } else if (xVelocity < 0) {
                swordSprite.setX(eggSprite.getX() - 0.4f);
                if (!flipped) {
                    swordSprite.flip(true, false);
                    flipped = true;
                }
            }
            swordSprite.setY(eggSprite.getY() + 0.18f);
        } else if (Class == Constants.Class.BERSERKER) {
            axeSprite.setOrigin(0f, .2f);
            axeSprite.setRotation((float) Math.toDegrees(Math.atan2(-controller.getAxis(controller.getMapping().axisRightY), controller.getAxis(controller.getMapping().axisRightX))));
            axeSprite.setX(eggSprite.getX() + .45f);
            axeSprite.setY(eggSprite.getY() + .25f);

            if (parryTime <= 0) {
                axeSprite.setAlpha(1.0f);
            } else {
                parryTime -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    public void damage(float damage) {
        if (iframes <= 0) {
            health -= damage;
            iframes = 1;
            if (health <= 75) eggSprite.setRegion(health75Region);
            if (health <= 50) eggSprite.setRegion(health50Region);
            if (health <= 25) eggSprite.setRegion(health25Region);
            if (health <= 0) eggSprite.setRegion(deadRegion);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        eggSprite.draw(spriteBatch);
        switch (Class) {
            case SWORDSMAN: {
                swordSprite.draw(spriteBatch);
                shieldSprite.draw(spriteBatch);
            }
            case BERSERKER: axeSprite.draw(spriteBatch);
        }
        if (game.debugToggle) {
            game.font.setColor(Color.GREEN);
            game.font.draw(spriteBatch, health + "/100", eggSprite.getBoundingRectangle().getCenter(new Vector2()).x - eggSprite.getWidth() / 2, eggSprite.getY() + eggSprite.getHeight());
        }
    }

    public void debug(ShapeRenderer renderer, Viewport viewport) {
        renderer.setColor(Color.GREEN);
        renderer.rect(eggRectangle.x, eggRectangle.y, eggRectangle.width, eggRectangle.height);
        renderer.setColor(Color.RED);
        switch (Class) {
            case SWORDSMAN: {
                renderer.rect(swordRectangle.x, swordRectangle.y, swordRectangle.width, swordRectangle.height);
                renderer.setColor(Color.BLUE);
                renderer.rect(shieldRectangle.x, shieldRectangle.y, shieldRectangle.width, shieldRectangle.height);
            }
            case BERSERKER: renderer.rect(axeRectangle.x, axeRectangle.y, axeRectangle.width, axeRectangle.height);
        }
    }

    public void updateClass() {
        switch (Class) {
            case SWORDSMAN: {
                swordSprite = new Sprite(Constants.swordTexture);
                swordSprite.setSize(0.8f, 0.5f);
                swordRectangle = new Rectangle(0.0f, 0.0f, 0.8f, 0.2f);
                shieldSprite = new Sprite(Constants.shieldTexture);
                shieldSprite.setSize(0.35f, 0.35f);
                shieldRectangle = new Rectangle(0.0f, 0.0f, 0.35f, 0.35f);
            }
            case ARCHER: {

            }
            case BERSERKER: {
                axeSprite = new Sprite(Constants.axe);
                axeSprite.setSize(0.5f, 0.35f);
                axeRectangle = new Rectangle(0.0f, 0.0f, 0.35f, 0.35f);
                shieldSprite = new Sprite(Constants.shieldTexture);
                shieldSprite.setSize(0.35f, 0.35f);
                shieldRectangle = new Rectangle(0.0f, 0.0f, 0.35f, 0.35f);
            }
        }
    }
}
