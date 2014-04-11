package org.adamp.jam;

import org.newdawn.slick.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

/**
 * Created by adam on 11/04/14.
 */
public class Resources {

    private static Font menuFont;

    static {
        loadMenuFont();
    }

    private static void loadMenuFont() {
        InputStream in = ResourceLoader.getResourceAsStream("res/menufont.ttf");
        try {
            menuFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            menuFont = new JLabel().getFont();
        }
    }

    public static TrueTypeFont loadMenuFont(int size) {
        Font awtFont2 = menuFont.deriveFont((float) size); // set font size
        return new TrueTypeFont(awtFont2, false);
    }
}
