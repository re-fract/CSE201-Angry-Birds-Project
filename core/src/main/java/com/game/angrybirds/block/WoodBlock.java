package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class WoodBlock extends ParentBlock implements Serializable {
    public WoodBlock(World world, float x, float y) {
        super(world, "woodblock.png", x, y, 2, 100, 50,2);
    }

    public WoodBlock(World world, float x, float y, int health) {
        super(world, "woodblock.png", x, y, health, 100, 50,2);
    }
}
