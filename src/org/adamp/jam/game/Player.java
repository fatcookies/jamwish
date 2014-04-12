package org.adamp.jam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 12/04/14.
 */
public class Player extends MovableObject {

    private boolean alive = true;
    protected boolean jumping = false;
    private int jumpdy = 0;

    protected Weapon weapon;

    public Player(Image doge, int x, int y, int maxX, int maxY) throws SlickException{
        super(doge, x, y, maxX, maxY);
        weapon = new Weapon(this);
    }

    protected void jump() {
        if (dy > -1 && dy < 1) {
            jumping = true;
            jumpdy = -7;
        }
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    private void handleJump() {
        if (jumping) {
            dy = jumpdy;
            jumpdy += 0.001;

            if (jumpdy >= 0) {
                jumping = false;
                dy = GRAVITY;
            }
        }

        if (dy > 0) {
            dy *= 1.03; // accelerate gravity
        }
    }

    public void shoot(float targetX, float targetY) {
        weapon.fire(targetX, targetY);
    }

    @Override
    public void render() {
        super.render();
        weapon.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i, ArrayList<Rectangle> collide,
                       List<Player> otherPlayers) throws SlickException {

        checkCollision(collide);

        handleJump();

        y += dy;
        x += dx;

        weapon.update(gameContainer, stateBasedGame, i, collide, otherPlayers);
    }
}
