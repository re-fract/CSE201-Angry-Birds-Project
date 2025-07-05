package com.game.angrybirds.bird;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class BlackBird extends ParentBird implements Serializable {
    public BlackBird(World world, float x, float y) {
        super(world,"blackbird.png",x,y,3,1.2f);
    }
}
