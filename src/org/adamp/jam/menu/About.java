package org.adamp.jam.menu;

import org.adamp.jam.Main;
import org.adamp.jam.Resources;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by adam on 11/04/14.
 */
public class About extends BasicGameState {

    public static final int ID = 3;
    private Font font;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        font = Resources.loadMenuFont(50);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.black);
        graphics.setFont(font);

        graphics.setColor(Color.white);
        drawCentre(graphics, "Created by fc", (int) (Main.HEIGHT * 0.1));
        drawCentre(graphics, "© 2014", (int) (Main.HEIGHT * 0.3));
        drawCentre(graphics, "Press ESC to return", (int) (Main.HEIGHT * 0.5));
    }

    private void drawCentre(Graphics g, String text, int y) {
        int width = font.getWidth(text);
        g.drawString(text,(Main.WIDTH/2) - (width/2),y);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(MainMenu.ID);
        }
    }
}
