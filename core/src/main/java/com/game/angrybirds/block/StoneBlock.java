package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class StoneBlock extends ParentBlock{

    public StoneBlock(World world, int x, int y) {
        super(world, "stoneblock.png", x, y, 4, 100, 50);
    }
}
