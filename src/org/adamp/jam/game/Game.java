package org.adamp.jam.game;

import org.adamp.jam.menu.MainMenu;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by adam on 11/04/14.
 */
public class Game extends BasicGameState {

    public static final int ID = 4;

    private TiledMap map;
    ArrayList<Rectangle> collide;
    private Image doge;
    private StateBasedGame game;

    private int x, y = 0;
    private int dx = 10;
    private int dy = 10;
    private int camx;
    private int maxx, maxy;
    public static final int SCREEN_POS = 10;

    private boolean left;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        doge = new Image("res/worm.png");
        loadmap();


        maxx = map.getWidth() * map.getTileWidth();
        maxy = map.getHeight() * map.getTileHeight();

        x = (int) (gameContainer.getWidth() / SCREEN_POS);
        y = gameContainer.getHeight() / 2;
    }

    private void loadmap() throws SlickException {
        map = new TiledMap("res/level.tmx");
        collide = new ArrayList<Rectangle>();

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                int id = map.getTileId(i, j, 0);
                if (id == 1 || id == 6) {
                    collide.add(new Rectangle(i * 16, j * 16, 16, 16));
                }
            }
        }
    }


    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            game.enterState(MainMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    private void checkCollision() {
        Rectangle you = new Rectangle(x, y, doge.getWidth(), doge.getHeight());
        boolean collid = false;
        for (Rectangle them : collide) {
            if (you.intersects(them)) {
                dy = 0;
                dx = 0;
                collid = true;
                break;
            }
        }
        if (!collid) {
            dy = 5;
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        camx = x - gameContainer.getWidth() / SCREEN_POS;


        checkCollision();
        y += dy;
        x += dx;

        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_RIGHT)) {
            dx = 10;
        } else if (in.isKeyDown(Input.KEY_LEFT)) {
            dx = -10;
        } else {
            dx = 0;
        }
        if (in.isKeyDown(Input.KEY_UP)) {
            y -= 10;
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.translate(-camx, 0);
        map.render(0, 0);
        doge.draw(x, y);
    }
}
