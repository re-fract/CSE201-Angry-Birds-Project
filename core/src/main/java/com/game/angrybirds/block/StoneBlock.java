package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class StoneBlock extends ParentBlock{

    public StoneBlock(World world, String texture, int x, int y, int health, int width, int height) {
        super(world, "stoneblock.png", x, y, health, 100, 50);
    }
}
