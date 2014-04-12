package org.adamp.jam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by adam on 12/04/14.
 */
public abstract class MovableObject {

    public static final int GRAVITY = 2;
    public int x, y = 0;

    private Image sprite;
    protected float dx = 0;
    protected float dy = GRAVITY;
    protected int speed = 3;


    public MovableObject(Image sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public abstract void update(GameContainer gameContainer, StateBasedGame stateBasedGame,
                                int i, ArrayList<Rectangle> collide) throws SlickException;

    public void render() {
        sprite.draw(x, y);
    }

    protected void checkCollision(ArrayList<Rectangle> collide) {
        Rectangle you = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        boolean vertical = false;
        boolean horizontal = false;
        for (Rectangle them : collide) {
            if (you.intersects(them)) {


                if ((them.getY() < (y + 32))) {
                    dy = 0;
                    vertical = true;
                }

                if (x + sprite.getWidth() > them.getX() && y + (sprite.getHeight() / 2) > them.getY()) {
                    dx = 0;
                    horizontal = true;
                }

                if (vertical && horizontal) {
                    break;
                }
            }
        }
        if (!vertical && dy == 0) {
            dy = GRAVITY;
        }
    }
}
