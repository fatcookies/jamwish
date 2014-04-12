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
    private ArrayList<Rectangle> collide;
    private StateBasedGame game;

    private Player player;


    private int camx;
    private int maxx, maxy;
    public static final int SCREEN_POS = 10;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        player = new Player(new Image("res/worm.png"),
                (gameContainer.getWidth() / SCREEN_POS), gameContainer.getHeight() / 2);
        loadmap();


        maxx = map.getWidth() * map.getTileWidth();
        maxy = map.getHeight() * map.getTileHeight();

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



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        camx = player.x - gameContainer.getWidth() / SCREEN_POS;
        player.update(gameContainer,stateBasedGame,i,collide);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.translate(-camx, 0);
        map.render(0, 0);
        player.render();
    }
}
