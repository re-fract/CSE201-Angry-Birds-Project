package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class WoodBlock extends ParentBlock implements Serializable {
    public WoodBlock(World world,int x, int y,int health) {
        super(world, "woodblock.png", x, y, health, 100, 50,2);
    }
}
