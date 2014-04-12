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
public abstract class MovableObject {

    public static final int GRAVITY = 2;
    public float x, y = 0;


    protected Image sprite;
    protected float dx = 0;
    protected float dy = GRAVITY;
    protected int speed = 3;
    protected int maxX, maxY;

    public MovableObject(Image sprite, float x, float y) {
        this(sprite, x, y, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public MovableObject(Image sprite, float x, float y, int maxX, int maxY) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.maxX = maxX - sprite.getWidth();
        this.maxY = maxY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
    }


    public abstract void update(GameContainer gameContainer, StateBasedGame stateBasedGame,
                                int i, ArrayList<Rectangle> collide, List<Player> players) throws SlickException;

    public void render() {
        sprite.draw(x, y);
    }

    protected void checkCollision(ArrayList<Rectangle> collide) {
        Rectangle you = new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
        boolean vertical = false;
        boolean horizontal = false;
        for (Rectangle them : collide) {
            if (you.intersects(them)) {


                if ((them.getY() < (y + sprite.getHeight()))) {
                    dy = 0;
                    vertical = true;
                }

                if ((x + sprite.getWidth() > them.getX() && y + (sprite.getHeight() / 2) > them.getY())) {
                    dx = 0;
                    horizontal = true;
                }

                if (vertical && horizontal) {
                    break;
                }
            }
        }

        if (x > maxX) {
            dx = 0;
            x -= 10;
        }
        if (x < 0) {
            dx = 0;
            x += 10;
        }

        if (!vertical && dy == 0) {
            dy = GRAVITY;
        }
    }
}
