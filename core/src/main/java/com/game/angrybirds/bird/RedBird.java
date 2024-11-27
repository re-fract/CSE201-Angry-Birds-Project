package com.game.angrybirds.bird;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class RedBird extends ParentBird implements Serializable {
    public RedBird(World world, float x, float y) {
        super(world,"redbird.png",x,y,1);
    }
}
