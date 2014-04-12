package org.adamp.jam;

import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.*;
import java.awt.Font;
import java.io.InputStream;

/**
 * Created by adam on 11/04/14.
 */
public class Resources {

    private static Font MENU_FONT;
    public static Image BULLET_IMAGE;


    static {
        loadMenuFont();
        loadBulletImage();
    }

    private static void loadMenuFont() {
        InputStream in = ResourceLoader.getResourceAsStream("res/menufont.ttf");
        try {
            MENU_FONT = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            MENU_FONT = new JLabel().getFont();
        }
    }

    private static void loadBulletImage() {
        try {
            BULLET_IMAGE = new Image(8,8);
            BULLET_IMAGE = new Image("res/bullet.png");
        } catch (Exception e) {
        }
    }


    public static TrueTypeFont loadMenuFont(int size) {
        Font awtFont2 = MENU_FONT.deriveFont((float) size); // set font size
        return new TrueTypeFont(awtFont2, false);
    }
}
