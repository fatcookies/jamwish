package org.adamp.jam.menu;

import org.adamp.jam.Main;
import org.adamp.jam.Resources;
import org.adamp.jam.game.*;
import org.adamp.jam.game.Game;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


/**
 * Created by adam on 11/04/14.
 */
public class MainMenu extends BasicGameState {
    public static final int ID = 0;
    private StateBasedGame game;

    private Image doge;
    private org.newdawn.slick.Font font;
    private MenuOption[] options;
    private int selected;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        doge = new Image("res/doge.png");

        try {
            font = Resources.loadMenuFont(32);
        } catch (Exception e) {
            font = gameContainer.getDefaultFont();
        }

        int mid = Main.WIDTH / 2;
        options = new MenuOption[]{
                new MenuOption("Play", mid - font.getWidth("Play")/2, (int) (Main.HEIGHT * 0.2), Game.ID),
                new MenuOption("About", mid - font.getWidth("About")/2, (int) (Main.HEIGHT * 0.5), About.ID),
                new MenuOption("Exit", mid - font.getWidth("Exit")/2, (int) (Main.HEIGHT * 0.8), Quit.ID)};
        selected = 0;
    }


    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setAntiAlias(true);
        graphics.setBackground(Color.pink);
        graphics.setFont(font);
        graphics.drawString("Make me a Wish!", 10, 10);


        for (int i = 0; i < options.length; i++) {
            options[i].render(graphics, i == selected);
        }

        doge.rotate(1f);
        doge.draw((float) (Main.WIDTH * 0.1), (float) (Main.HEIGHT * 0.7));
        doge.draw((float) (Main.WIDTH * 0.8), (float) (Main.HEIGHT * 0.7));
        doge.draw((float) (Main.WIDTH * 0.1), (float) (Main.HEIGHT * 0.3));
        doge.draw((float) (Main.WIDTH * 0.8), (float) (Main.HEIGHT * 0.3));
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_UP) {
            selected = (selected - 1) % options.length;
            if (selected < 0) {
                selected = options.length - 1;
            }
        } else if (key == Input.KEY_DOWN) {
            selected = (selected + 1) % options.length;
        } else if (key == Input.KEY_ENTER) {
            game.enterState(options[selected].getID(), new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
