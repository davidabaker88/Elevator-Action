package main.java;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Door {
    private boolean isRed;
    AnimatedTexture texture;

    public Door(boolean isRed) {
        texture = new AnimatedTexture("doorClosed");
        this.isRed = isRed;
    }

}
