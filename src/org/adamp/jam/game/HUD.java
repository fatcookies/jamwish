package org.adamp.jam.game;

import org.adamp.jam.Resources;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 * Created by adam on 13/04/14.
 */
public class HUD {

    public static final TrueTypeFont font = Resources.loadMenuFont(40);
    private int kills;



    public void render(Graphics graphics, int width, int height) {
        graphics.setFont(font);
        graphics.drawString(Integer.toString(kills), width/10, height/10);
    }

    public void reset() {
        kills = 0;
    }

    public void addKill() {
        kills++;
    }
}
