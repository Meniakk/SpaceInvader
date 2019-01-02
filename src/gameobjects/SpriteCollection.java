package gameobjects;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import interfaces.Sprite;

/**
 * A class of gameobjects.SpriteCollection.
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * The gameobjects.SpriteCollection constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds a sprite to the list.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove's a sprite to the list.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        //this.spriteList.remove(s);
        this.spriteList.removeAll(Collections.singleton(s));
    }

    /**
     * Call timePassed() on all sprites.
     * @param dt The time passed.
     */
    public void notifyAllTimePassed(double dt) {
        /* perform the iteration on a copy of the list. */
        List<Sprite> copyList = new ArrayList<>(this.spriteList);
        for (Sprite sprite: copyList) {
            sprite.timePassed(dt);
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: this.spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns the list of sprites.
     * @return the list of sprites.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }
}
