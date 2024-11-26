package com.game.angrybirds.block;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class StoneBlock extends ParentBlock implements Serializable {

    public StoneBlock(World world, int x, int y,int health) {
        super(world, "stoneblock.png", x, y, health, 100, 50,3);
    }
}
