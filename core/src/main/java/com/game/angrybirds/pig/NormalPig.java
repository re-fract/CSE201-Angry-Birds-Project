package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class NormalPig extends ParentPig implements Serializable {

    public NormalPig(World world,int x, int y, int health, float radius) {
        super(world,"pig.png", x, y, health, radius,3);

    }
}
