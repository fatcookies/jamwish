package org.adamp.jam.game;

import org.adamp.jam.Main;
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
    private int fireTime;
    float distance = (float) ((Math.random() * 4) + 2);

    public NPCPlayer(Image doge, int x, int y, int maxX, int maxY, Player target) throws SlickException {
        super(doge, x, y, maxX, maxY);
        this.target = target;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame,
                       int i, ArrayList<Rectangle> collide,
                       List<Player> otherPlayers) throws SlickException {
        fire(i);
        super.update(gameContainer, stateBasedGame, i, collide, otherPlayers);
        aaaaaamoveToTarget();
        dx = speed;
    }

    private void moveToTarget() {
        double r = Math.random();

        if (r > 0.2) {
            if (target.x + (distance * getWidth()) >= x) {
                dx = speed;
            } else if (target.x + (distance * getWidth()) < x) {
                dx = -speed;
            } else {
                dx = 0;
            }
        } else {
            dx = 0;
        }

        if(r < 0.05) {
            jump();
        }
    }

    public void fire(int delta) {
        fireTime += delta;
        if (fireTime > 1000) {
            if (Math.abs(target.x - x) < Main.WIDTH) {
                float tx = target.x;
                float ty = target.y + target.getHeight() / 2;
                if (Math.random() < 0.2) {
                    ty += target.getHeight();
                }

                weapon.fire(tx, ty);
            }
            fireTime = 0;
        }

    }
}
