package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class GlassBlock extends ParentBlock {
    public GlassBlock(World world, int x, int y) {
        super(world, "glassblock.png", x, y, 1, 100, 50);
    }
}
