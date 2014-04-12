package org.adamp.jam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by adam on 12/04/14.
 */
public class Player extends MovableObject {

    private boolean jumping = false;
    private boolean up = false;
    private int jumpdy = 0;

    public Player(Image doge, int x, int y) {
        super(doge, x, y);
    }

    private void jump() {
        if (dy > -1 && dy < 1) {
            jumping = true;
            up = true;
            jumpdy = -5;
        }
    }

    private void handleJump() {
        if (jumping) {
            dy = jumpdy;
            jumpdy += 0.001;

            if(jumpdy >= 0) {
                jumping = false;
                dy = GRAVITY;
            }
        }

        if(dy > 0) {
            dy *= 1.02; // accelerate
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i, ArrayList<Rectangle> collide) throws SlickException {
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_RIGHT)) {
            dx = speed;
        } else if (in.isKeyDown(Input.KEY_LEFT)) {
            dx = -speed;
        } else {
            dx = 0;
        }
        if (in.isKeyDown(Input.KEY_UP) && !jumping) {
            jump();
        }

        checkCollision(collide);
        handleJump();

        y += dy;
        x += dx;
    }
}
