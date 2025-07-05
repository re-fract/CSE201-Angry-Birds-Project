package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class StoneBlock extends ParentBlock implements Serializable {
    public StoneBlock(World world, float x, float y, int health) {
        super(world, "stoneblock.png", x, y, health, 100, 50,3);
    }

    public StoneBlock(World world, float x, float y) {
        super(world, "stoneblock.png", x, y, 4, 100, 50,3);
    }
}
