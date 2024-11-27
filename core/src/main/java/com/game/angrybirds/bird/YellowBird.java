package com.game.angrybirds.bird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class YellowBird extends ParentBird implements Serializable {
    public YellowBird(World world, float x, float y) {
        super(world,"yellowbird.png",x,y,2);
    }
}
