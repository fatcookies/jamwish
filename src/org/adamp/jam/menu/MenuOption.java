package org.adamp.jam.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Represents a menu option/item in main menu
 */
public class MenuOption {

    /**
     * The text displayed
     */
    private String text;

    /**
     * The x coordinate of the option
     */
    private int x;

    /**
     * The y coordinate of the option
     */
    private int y;

    /**
     * The state ID that this option leads to
     */
    private int ID;

    public static final int PADDING = 10;

    /**
     * Creates a new option
     *
     * @param text The text to be displayed
     * @param x    The x coordinate
     * @param y    The y coordinate
     * @param ID   The state ID this option leads to
     */
    public MenuOption(String text, int x, int y, int ID) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public void render(Graphics g, boolean selected) {
        int height = g.getFont().getHeight(text);
        int width = g.getFont().getWidth(text);

        g.setColor(Color.blue);
        if (selected) {
            g.drawRect(x, y, width + PADDING * 2, height + PADDING * 2);
        }
        g.setColor(Color.white);

        g.drawString(text, x + PADDING, y + PADDING);
    }

    public int getID() {
        return ID;
    }
}
