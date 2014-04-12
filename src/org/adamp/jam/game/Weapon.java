package org.adamp.jam.game;

import org.adamp.jam.Main;
import org.adamp.jam.Resources;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by adam on 12/04/14.
 */
public class Weapon extends MovableObject {

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private MovableObject owner;
    private GameContainer game;
    private Image normalGun;
    private Image reversedGun;

    public Weapon(MovableObject owner) throws SlickException{
        super(new Image("res/gun.png")
                , owner.x, owner.y, owner.maxX, owner.maxY);
        normalGun = sprite;
        reversedGun = sprite.getFlippedCopy(false,true);
        this.owner = owner;
    }

    public void fire(float x, float y) {
        float initx = this.x / Main.WIDTH - this.x;
        float inity = this.y / Main.HEIGHT - this.y;

        bullets.add(new Bullet(owner, this, initx, inity, x, y));
    }

    public void setRotation(float x, float y) {

        float a = x - Main.WIDTH / 2;
        float b = y - Main.HEIGHT / 2;

        float rot = (float) Math.toDegrees(Math.atan2(b, a));
        sprite.setRotation(rot);

        if(rot < -90 || rot > 90) {
            sprite = reversedGun;
        } else {
            sprite = normalGun;
        }
    }

    @Override
    public void render() {
        super.render();
        for (Bullet b : bullets) {
            b.render();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i, ArrayList<Rectangle> collide,
                       List<Player> players) throws SlickException {
        game = gameContainer;
        x = owner.x;
        y = owner.y + owner.getHeight() / 2;

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.update(gameContainer, stateBasedGame, i, collide, players);

            if (b.x < 0 || b.y < 0 || b.x > maxX || b.y > maxY) {
                it.remove();
            }
        }
    }
}
