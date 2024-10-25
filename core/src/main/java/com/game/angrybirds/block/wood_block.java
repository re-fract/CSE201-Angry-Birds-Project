package com.game.angrybirds.block;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class wood_block {
    private Body body;
    private PolygonShape shape;
    private int Health_points;
    public wood_block(World world, float x, float y) {
        Health_points=300;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(0, 0);
        vertices[1] = new Vector2(1, 0);
        vertices[2] = new Vector2(1, 1);
        vertices[3] = new Vector2(0, 1);
        shape.set(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 3f;
        body.createFixture(fixtureDef);
    }
}
