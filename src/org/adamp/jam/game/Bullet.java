package org.adamp.jam.game;

import org.adamp.jam.Resources;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by adam on 12/04/14.
 */
public class Bullet extends MovableObject {

    private MovableObject owner;

    public Bullet(MovableObject owner, MovableObject source, float initX, float initY, float targetX, float targetY) {
        super(Resources.BULLET_IMAGE, source.x, source.y);
        this.owner = owner;
        this.x = source.x;
        this.y = source.y;
        this.dx = 0.1f;

        calculateProjectory(initX, initY, targetX, targetY);
    }

    private void calculateProjectory(float initX, float initY, float targetX, float targetY) {
        float tempDx = targetX + initX;
        float tempDy = targetY + initY;

        dx = tempDx > 0 ? 14 : -14;
        dy = 14 * (tempDy / tempDx);
        dy = tempDx > 0 ? dy : -dy;

    }

    public MovableObject getOwner() {
        return owner;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i,
                       ArrayList<Rectangle> collide, List<Player> players) throws SlickException {
        checkCollision(collide);
        x += dx;
        y += dy;

        Rectangle bounds = getBounds();


        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if (bounds.intersects(p.getBounds()) && p != owner) {
                x = -1;
                dx = 0;
                p.kill(this);
            }
        }



    }
}
