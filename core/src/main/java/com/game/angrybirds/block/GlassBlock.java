package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class GlassBlock extends ParentBlock {
    public GlassBlock(World world, String texture, int x, int y, int health, int width, int height) {
        super(world, "glassblock.png", x, y, health, 100, 50);
    }
}
