package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class GlassBlock extends ParentBlock implements Serializable {
    public GlassBlock(World world, int x, int y,int health) {
        super(world, "glassblock.png", x, y, health, 100, 50,1);
    }
}
