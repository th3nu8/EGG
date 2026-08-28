package com.badlogic.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.net.ssl.SSLContextSpi;

public class Menu implements Screen {
    final Main game;
    Controller controller;
    Controller firstPlayer;
    Sprite logo;
    Sprite topHat = new Sprite(Constants.topHat);
    Sprite viking = new Sprite(Constants.viking);
    Sprite glasses = new Sprite(Constants.glasses);
    Sprite mustache = new Sprite(Constants.mustache);
    Sprite ballCap = new Sprite(Constants.ballCap);

    public int classSelect = 1;

    public Menu(Main game) {
        this.game = game;
        firstPlayer = Constants.controllers.get(0);
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

        if (game.players[0].menuOption == 3) {
            game.font.setColor(Color.GREEN);
            game.font.draw(game.spriteBatch, "PLAY", 5.3f, 4.5f);
        } else {
            game.font.setColor(Color.WHITE);
            game.font.draw(game.spriteBatch, "PLAY", 5.3f, 4.5f);
        }

        game.font.getData().setScale(0.02f);
        if (game.players.length > 0) {
            game.font.setColor(Color.RED);
            game.font.draw(game.spriteBatch, "Player 1", 0.5f, 1.5f);
            game.font.draw(game.spriteBatch, game.players[0].controller.getName(), 0.5f, 0.5f);

            if (game.players[0].menuOption == 1) {
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, "<", 0.7f, 2.7f);
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, ">", 2.7f, 2.7f);
            } else if (game.players[0].menuOption == 2) {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 0.7f, 2.7f);
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, ">", 2.7f, 2.7f);
            } else {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 0.7f, 2.7f);
                game.font.draw(game.spriteBatch, ">", 2.7f, 2.7f);
            }

            Sprite egg = new Sprite(Constants.eggTexture);
            egg.setX(0.3f);
            egg.setY(1f);
            egg.setSize(3f, 3f);
            egg.setColor(Color.RED);
            egg.draw(game.spriteBatch);

            switch (game.players[0].costume) {
                case 1: {
                    drawCostume(game.spriteBatch, topHat, 0, 1);
                } break;
                case 2: {
                    drawCostume(game.spriteBatch, viking, 0, 2);
                } break;
                case 3: {
                    drawCostume(game.spriteBatch, glasses, 0, 3);
                } break;
                case 4: {
                    drawCostume(game.spriteBatch, mustache, 0, 4);
                } break;
                case 5: {
                    drawCostume(game.spriteBatch, ballCap, 0, 5);
                }
            }
        }
        if (game.players.length > 1) {
            game.font.setColor(Color.BLUE);
            game.font.draw(game.spriteBatch, "Player 2", 3.5f, 1.5f);
            game.font.draw(game.spriteBatch, game.players[1].controller.getName(), 3.5f, 0.5f);

            if (game.players[1].menuOption == 1) {
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, "<", 3.7f, 2.7f);
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, ">", 5.7f, 2.7f);
            } else if (game.players[1].menuOption == 2) {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 3.7f, 2.7f);
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, ">", 5.7f, 2.7f);
            } else {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 3.7f, 2.7f);
                game.font.draw(game.spriteBatch, ">", 5.7f, 2.7f);
            }

            Sprite egg = new Sprite(Constants.eggTexture);
            egg.setX(3.3f);
            egg.setY(1f);
            egg.setSize(3f, 3f);
            egg.setColor(Color.BLUE);
            egg.draw(game.spriteBatch);

            switch (game.players[1].costume) {
                case 1: {
                    drawCostume(game.spriteBatch, topHat, 1, 1);
                } break;
                case 2: {
                    drawCostume(game.spriteBatch, viking, 1, 2);
                } break;
                case 3: {
                    drawCostume(game.spriteBatch, glasses, 1, 3);
                } break;
                case 4: {
                    drawCostume(game.spriteBatch, mustache, 1, 4);
                } break;
                case 5: {
                    drawCostume(game.spriteBatch, ballCap, 1, 5);
                }
            }
        }
        if (game.players.length > 2) {
            game.font.setColor(Color.GREEN);
            game.font.draw(game.spriteBatch, "Player 3", 6.5f, 1.5f);
            game.font.draw(game.spriteBatch, game.players[2].controller.getName(), 6.56f, 0.5f);

            if (game.players[2].menuOption == 1) {
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, "<", 6.7f, 2.7f);
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, ">", 8.7f, 2.7f);
            } else if (game.players[2].menuOption == 2) {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 6.7f, 2.7f);
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, ">", 8.7f, 2.7f);
            } else {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 6.7f, 2.7f);
                game.font.draw(game.spriteBatch, ">", 8.7f, 2.7f);
            }

            Sprite egg = new Sprite(Constants.eggTexture);
            egg.setX(6.3f);
            egg.setY(1f);
            egg.setSize(3f, 3f);
            egg.setColor(Color.GREEN);
            egg.draw(game.spriteBatch);

            switch (game.players[2].costume) {
                case 1: {
                    drawCostume(game.spriteBatch, topHat, 2, 1);
                } break;
                case 2: {
                    drawCostume(game.spriteBatch, viking, 2, 2);
                } break;
                case 3: {
                    drawCostume(game.spriteBatch, glasses, 2, 3);
                } break;
                case 4: {
                    drawCostume(game.spriteBatch, mustache, 2, 4);
                } break;
                case 5: {
                    drawCostume(game.spriteBatch, ballCap, 0, 5);
                }
            }
        }
        if (game.players.length > 3) {
            game.font.setColor(Color.YELLOW);
            game.font.draw(game.spriteBatch, "Player 4", 9.5f, 1.5f);
            game.font.draw(game.spriteBatch, game.players[3].controller.getName(), 9.5f, 0.5f);

            if (game.players[3].menuOption == 1) {
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, "<", 9.7f, 2.7f);
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, ">", 11.7f, 2.7f);
            } else if (game.players[3].menuOption == 2) {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 9.7f, 2.7f);
                game.font.setColor(Color.GREEN);
                game.font.draw(game.spriteBatch, ">", 11.7f, 2.7f);
            } else {
                game.font.setColor(Color.WHITE);
                game.font.draw(game.spriteBatch, "<", 9.7f, 2.7f);
                game.font.draw(game.spriteBatch, ">", 11.7f, 2.7f);
            }

            Sprite egg = new Sprite(Constants.eggTexture);
            egg.setX(9.3f);
            egg.setY(1f);
            egg.setSize(3f, 3f);
            egg.setColor(Color.YELLOW);
            egg.draw(game.spriteBatch);

            switch (game.players[3].costume) {
                case 1: {
                    drawCostume(game.spriteBatch, topHat, 3, 1);
                } break;
                case 2: {
                    drawCostume(game.spriteBatch, viking, 3, 2);
                } break;
                case 3: {
                    drawCostume(game.spriteBatch, glasses, 3, 3);
                } break;
                case 4: {
                    drawCostume(game.spriteBatch, mustache, 3, 4);
                } break;
                case 5: {
                    drawCostume(game.spriteBatch, ballCap, 3, 5);
                }
            }
        }

        game.font.setColor(Color.WHITE);

        for (int i = 0; i < game.players.length; i++) {
            if (game.players[i].menuOption == 0) {
                game.font.setColor(Color.GREEN);
            } else {
                game.font.setColor(Color.WHITE);
            }
            if (game.players[i].Class == Constants.Class.SWORDSMAN) game.font.draw(game.spriteBatch, "Class: Swordsman", 0.5f + (3 * i), 1f);
            else if (game.players[i].Class == Constants.Class.ARCHER) game.font.draw(game.spriteBatch, "Class: Archer", 0.5f + (3 * i), 1f);
            else if (game.players[i].Class == Constants.Class.BERSERKER) game.font.draw(game.spriteBatch, "Class: Berserker", 0.5f + (3 * i), 1f);
        }

        game.spriteBatch.end();
        if (game.players[0].menuOption == 3 && firstPlayer.getButton(firstPlayer.getMapping().buttonA)) {
            for (int i = 0; i < game.players.length; i++) {
                game.players[i].updateClass();
            }
            game.setScreen(new EggGame(game));
            dispose();
        }

        for (int i = 0; i < game.players.length; i++) {
            controller = Constants.controllers.get(i);

            if (game.players[i].menuPress <= -5) {

                if (game.players[i].menuOption == 0 && controller.getButton(controller.getMapping().buttonDpadRight)) {
                    game.players[i].menuPress = 1;
                    switch (game.players[i].Class) {
                        case SWORDSMAN: game.players[i].Class = Constants.Class.ARCHER; break;
                        case ARCHER: game.players[i].Class = Constants.Class.BERSERKER; break;
                        case BERSERKER: game.players[i].Class = Constants.Class.SWORDSMAN; break;
                    }
                }

                if (game.players[i].menuOption == 0 && controller.getButton(controller.getMapping().buttonDpadLeft)) {
                    game.players[i].menuPress = 1;
                    switch (game.players[i].Class) {
                        case SWORDSMAN: game.players[i].Class = Constants.Class.BERSERKER; break;
                        case ARCHER: game.players[i].Class = Constants.Class.SWORDSMAN; break;
                        case BERSERKER: game.players[i].Class = Constants.Class.ARCHER; break;
                    }
                }

                if (game.players[i].menuOption == 2 && controller.getButton(controller.getMapping().buttonA) && game.players[i].costume < 5) {
                    game.players[i].costume += 1;
                }

                if (game.players[i].menuOption == 1 && controller.getButton(controller.getMapping().buttonA) && game.players[i].costume > 0) {
                    game.players[i].costume -= 1;
                }

                switch (game.players[i].menuOption) {
                    case 0: {
                        if (controller.getButton(controller.getMapping().buttonDpadUp)) game.players[i].menuOption = 1;
                        game.players[i].menuPress = 1;
                        break;
                    }
                    case 1: {
                        if (i == 0 && controller.getButton(controller.getMapping().buttonDpadUp)) game.players[i].menuOption = 3;
                        if (controller.getButton(controller.getMapping().buttonDpadRight)) game.players[i].menuOption = 2;
                        if (controller.getButton(controller.getMapping().buttonDpadDown)) game.players[i].menuOption = 0;
                        game.players[i].menuPress = 1;
                        break;
                    }
                    case 2: {
                        if (i == 0 && controller.getButton(controller.getMapping().buttonDpadUp)) game.players[i].menuOption = 3;
                        if (controller.getButton(controller.getMapping().buttonDpadLeft)) game.players[i].menuOption = 1;
                        if (controller.getButton(controller.getMapping().buttonDpadDown)) game.players[i].menuOption = 0;
                        game.players[i].menuPress = 1;
                        break;
                    }
                    case 3: {
                        if (controller.getButton(controller.getMapping().buttonDpadDown)) game.players[i].menuOption = 1;
                        game.players[i].menuPress = 1;
                        break;
                    }
                }
                System.out.println(game.players[0].costume);
                //System.out.println(game.players[i].Class.toString());

                //System.out.println(game.players[0].menuPress < -10);
                //System.out.println(game.players[0].Class);
            }
            game.players[i].menuPress -= 0.1f;
            if (game.players[i].menuPress <= -10) game.players[i].menuPress = -10;
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

    public void drawCostume(SpriteBatch spriteBatch, Sprite sprite, int i, int costume) {
        switch (costume) {
            case 1: {
                sprite.setX(1.3f + i*3);
                sprite.setY(3.2f);
                sprite.setSize(1f, 1f);
                sprite.draw(spriteBatch);
            } break;
            case 2: {
                sprite.setX(.8f + i * 3);
                sprite.setY(2.5f);
                sprite.setSize(2f, 2f);
                sprite.draw(spriteBatch);
            } break;
            case 3: {
                sprite.setX(1f + i * 3);
                sprite.setY(2f);
                sprite.setSize(1.5f, 1.5f);
                sprite.draw(spriteBatch);
            } break;
            case 4: {
                sprite.setX(1.3f + i * 3);
                sprite.setY(2.3f);
                sprite.setSize(1f, .7f);
                sprite.draw(spriteBatch);
            } break;
            case 5: {
                sprite.setX(1.15f + i * 3);
                sprite.setY(2.6f);
                sprite.setSize(1.3f, 1.3f);
                sprite.draw(spriteBatch);
            } break;
        }
    }
}
