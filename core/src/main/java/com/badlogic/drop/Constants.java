package com.badlogic.drop;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

import static com.badlogic.gdx.controllers.Controllers.getControllers;


public class Constants {
    public static final float gravity = 0.1f;

    public static final Texture shadowTexture = new Texture("shadow.png");
    public static final Texture eggTexture = new Texture("egg.png");
    public static final Texture shieldTexture = new Texture("shield.png");
    public static final Texture swordTexture = new Texture("sword.png");
    public static final Texture health75 = new Texture("75%.png");
    public static final Texture health50 = new Texture("50%.png");
    public static final Texture health25 = new Texture("25%.png");
    public static final Texture dead = new Texture("dead.png");
    public static final Texture sand = new Texture("sand.jpg");
    public static final Texture logo = new Texture("logo.png");
    public static final Texture axe = new Texture("axe.png");
    public static final Texture bow = new Texture("bow.png");
    public static final Texture arrow = new Texture("arrow.png");

    public static Array<Controller> controllers = Controllers.getControllers();

    public static Color[] color = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public enum Class {
        SWORDSMAN,
        ARCHER,
        BERSERKER
    }
}
