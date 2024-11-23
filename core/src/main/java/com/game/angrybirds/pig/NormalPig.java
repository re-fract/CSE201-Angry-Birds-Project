package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class NormalPig extends ParentPig{
    public NormalPig(World world,int x, int y) {
        super(world,"pig.png", x, y, 50);
    }
}
