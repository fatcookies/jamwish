package org.adamp.jam.menu;

import org.adamp.jam.Main;
import org.adamp.jam.Resources;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by adam on 11/04/14.
 */
public class About extends BasicGameState {

    public static final int ID = 3;
    private StateBasedGame game;
    private Font font;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        font = Resources.loadMenuFont(50);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.black);
        graphics.setFont(font);

        graphics.setColor(Color.white);
        drawCentre(graphics, "Created by fc", (int) (Main.HEIGHT * 0.1));
        drawCentre(graphics, "copyright 2014", (int) (Main.HEIGHT * 0.3));
        drawCentre(graphics, "Press any key to return", (int) (Main.HEIGHT * 0.5));
        drawCentre(graphics, "wololo", (int) (Main.HEIGHT * 0.85));
    }

    private void drawCentre(Graphics g, String text, int y) {
        int width = font.getWidth(text);
        g.drawString(text,(Main.WIDTH/2) - (width/2),y);
    }

    @Override
    public void keyPressed(int key, char c) {
        game.enterState(MainMenu.ID, new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }
}
