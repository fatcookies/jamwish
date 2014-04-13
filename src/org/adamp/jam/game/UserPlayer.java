package org.adamp.jam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 12/04/14.
 */
public class UserPlayer extends Player {


    public float crossHairX;
    public float crossHairY;

    private StateBasedGame game;

    public UserPlayer(Image doge, int x, int y, int maxX, int maxY) throws SlickException{
        super(doge, x, y, maxX, maxY);
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i, ArrayList<Rectangle> collide,
    List<Player> players) throws SlickException {
        game = stateBasedGame;

        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_D)) {
            dx = speed;
        } else if (in.isKeyDown(Input.KEY_A)) {
            dx = -speed;
        } else {
            dx = 0;
        }
        if (in.isKeyDown(Input.KEY_W) && !jumping) {
            jump();
        }

        super.update(gameContainer,stateBasedGame,i,collide,players);
        weapon.setRotation(crossHairX, crossHairY);
    }
}
