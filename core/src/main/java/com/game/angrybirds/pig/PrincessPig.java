package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class PrincessPig extends ParentPig{

    public PrincessPig(World world, int x, int y, int health, float radius) {
        super(world,"pig_princess.png", x, y, health, radius);
    }
}
