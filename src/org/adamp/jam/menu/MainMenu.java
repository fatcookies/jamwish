package org.adamp.jam.menu;

import org.adamp.jam.Main;
import org.adamp.jam.Resources;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by adam on 11/04/14.
 */
public class MainMenu extends BasicGameState {
    public static final int ID = 0;

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
        doge = new Image("res/doge.png");

        try {
            font = Resources.loadMenuFont(32);
        } catch (Exception e) {
            font = gameContainer.getDefaultFont();
        }

        int mid = Main.WIDTH / 2;
        options = new MenuOption[]{
                new MenuOption("Play", mid, (int) (Main.HEIGHT * 0.2), ID),
                new MenuOption("About", mid, (int) (Main.HEIGHT * 0.5), About.ID),
                new MenuOption("Exit", mid, (int) (Main.HEIGHT * 0.8), Quit.ID)};
        selected = 0;
    }


    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setAntiAlias(true);
        graphics.setBackground(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
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
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input in = gameContainer.getInput();
        if (in.isKeyPressed(Input.KEY_UP)) {
            selected = (selected - 1) % options.length;
            if (selected < 0) {
                selected = options.length - 1;
            }
        } else if (in.isKeyPressed(Input.KEY_DOWN)) {
            selected = (selected + 1) % options.length;
        } else if (in.isKeyPressed(Input.KEY_ENTER)) {
            System.out.println(options[selected].getID());
            stateBasedGame.enterState(options[selected].getID());
        }
    }
}
