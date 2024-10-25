package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class WoodBlock extends ParentBlock{

    public WoodBlock(World world, String texture, int x, int y, int health, int width, int height) {
        super(world, "woodblock.png", x, y, health, 100, 50);
    }
}
