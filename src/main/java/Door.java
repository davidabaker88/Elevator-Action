package main.java;

import java.io.Serializable;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Door  implements Serializable{
    private boolean isRed;
    private transient AnimatedTexture texture;

    public Door(boolean isRed) {
        texture = new AnimatedTexture("doorClosed");
        this.isRed = isRed;
    }

}
