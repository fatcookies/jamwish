package org.adamp.jam;

import org.adamp.jam.menu.About;
import org.adamp.jam.menu.MainMenu;
import org.adamp.jam.menu.Quit;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by adam on 11/04/14.
 */
public class Main extends StateBasedGame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private Animation pac;
    private double x, y = 0.0;

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer game = new AppGameContainer(new Main("Make a Wish"));
            game.setMaximumLogicUpdateInterval(60);
            game.setDisplayMode(WIDTH, HEIGHT, false);
            game.setTargetFrameRate(60);
            game.setAlwaysRender(true);
            game.setVSync(true);
            game.setShowFPS(false);
            game.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenu());
        addState(new Quit());
        addState(new About());

        enterState(MainMenu.ID);
    }

}