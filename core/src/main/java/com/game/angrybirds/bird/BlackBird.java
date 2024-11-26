package com.game.angrybirds.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class BlackBird extends ParentBird implements Serializable {
    private Body body;
    private CircleShape shape;
    public BlackBird(World world, float x, float y) {
        super(world,300,"blackbird.png",x,y,3);
    }
}
