package org.adamp.jam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 12/04/14.
 */
public class NPCPlayer extends Player {

    private Player target;

    public NPCPlayer(Image doge, int x, int y, int maxX, int maxY, Player target) throws SlickException{
        super(doge, x, y, maxX, maxY);
        this.target = target;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame,
                       int i, ArrayList<Rectangle> collide,
                       List<Player> otherPlayers) throws SlickException {
        jump();

        super.update(gameContainer, stateBasedGame, i, collide, otherPlayers);
    }
}
