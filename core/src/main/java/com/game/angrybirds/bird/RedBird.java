package com.game.angrybirds.bird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class RedBird extends ParentBird implements Serializable {
    public RedBird(World world, float x, float y) {
        super(world,100,"redbird.png",x,y,1);
    }
}
