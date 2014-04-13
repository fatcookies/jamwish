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
import java.util.Iterator;
import java.util.List;

/**
 * Created by adam on 11/04/14.
 */
public class Game extends BasicGameState {

    public static final int ID = 4;

    private TiledMap map;
    private ArrayList<Rectangle> collide;
    private StateBasedGame game;
    private HUD hud;

    private UserPlayer player;
    private Image crossHair;


    private float camx;
    private float camy;
    private int crossX;
    private int crossY;
    public static final int SCREEN_POS = 2;

    private List<Player> killers;
    private int killerTime = 0;

    @Override
    public int getID() {
        return ID;
    }


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        loadMap();
        crossHair = new Image("res/crosshair.png");
        killers = new ArrayList<Player>();
        hud = new HUD();

        reset(gameContainer);

    }

    private void loadMap() throws SlickException {
        map = new TiledMap("res/level.tmx");
        collide = new ArrayList<Rectangle>();

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                int id = map.getTileId(i, j, 1);
                if (id == 1 || id == 6) {
                    collide.add(new Rectangle(i * 16, j * 16, 16, 16));
                }
            }
        }
    }


    public float getMapWidth() {
        return map.getWidth() * map.getTileWidth();
    }

    public float getMapHeight() {
        return map.getHeight() * map.getTileHeight();
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            game.enterState(MainMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        crossX = newx - crossHair.getWidth() / 2;
        crossY = newy - crossHair.getHeight() / 2;
        player.crossHairX = crossX;
        player.crossHairY = crossY;
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        player.shoot(x + camx, y);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        camx = player.x - gameContainer.getWidth() / SCREEN_POS;
        camy = player.y - gameContainer.getHeight();

        addEnemy(i);


        if (!player.isAlive()) {
            reset(gameContainer);
            stateBasedGame.enterState(GameOver.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }


        Iterator<Player> it = killers.iterator();
        while (it.hasNext()) {
            Player player = it.next();
            if (!player.isAlive()) {

                if (this.player.bulletBelongs(player.getBulletKilled())) { // only increase count if player kills
                    hud.addKill();
                }
                it.remove();
            } else {
                player.update(gameContainer, stateBasedGame, i, collide, killers);
            }
        }


    }

    private void reset(GameContainer gameContainer) throws SlickException {
        hud.reset();
        player = new UserPlayer(new Image("res/worm.png"),
                (gameContainer.getWidth() / SCREEN_POS), gameContainer.getHeight() / 2,
                map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileWidth());
        killers.clear();
        killers.add(player);

        for (int i = 0; i < 30; i++) {
            addEnemy(5001);
        }
    }

    private void addEnemy(int delta) throws SlickException {
        killerTime += delta;
        if (killerTime > 5000) {
            Player p = new NPCPlayer(new Image("res/worm.png"),
                    (int) (Math.random() * map.getWidth() * map.getTileWidth()),
                    (int) (Math.random() * map.getHeight() * map.getTileHeight()),
                    map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight()
                    , player);
            killers.add(p);
            killerTime = 0;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.translate(-camx, 0);
        map.render(0, 0);

        for (Player player : killers) {
            player.render();

        }



        graphics.resetTransform();
        crossHair.draw(crossX, crossY);
        hud.render(graphics, gc.getWidth(), gc.getHeight());
    }
}
