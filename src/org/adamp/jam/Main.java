package org.adamp.jam;

import org.adamp.jam.game.Game;
import org.adamp.jam.menu.About;
import org.adamp.jam.menu.MainMenu;
import org.adamp.jam.menu.Quit;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by adam on 11/04/14.
 */
public class Main extends StateBasedGame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;


    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer game = new AppGameContainer(new Main("Doge Killer"));
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
        addState(new Game());

        enterState(MainMenu.ID);
    }

}
