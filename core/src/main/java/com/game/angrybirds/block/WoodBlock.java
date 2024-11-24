package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

public class WoodBlock extends ParentBlock{
    public WoodBlock(World world,int x, int y) {
        super(world, "woodblock.png", x, y, 70, 100, 50);
    }
}
