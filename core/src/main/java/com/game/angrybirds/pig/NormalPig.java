package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;

public class NormalPig extends ParentPig{
    public NormalPig(int x, int y) {
        super(new Texture("pig.png"), x, y, 50, 70, 70);
    }
}
