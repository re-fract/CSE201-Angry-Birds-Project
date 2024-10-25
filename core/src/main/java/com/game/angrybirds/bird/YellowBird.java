package com.game.angrybirds.bird;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class YellowBird extends ParentBird{
    private Body body;
    private PolygonShape shape;
    public YellowBird(World world, float x, float y) {
        super( 200,100);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(0, 0);
        vertices[1] = new Vector2(1, 0);
        vertices[2] = new Vector2(0.5f, 1);
        shape.set(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 3f;
        fixtureDef.restitution = 6f;
        body.createFixture(fixtureDef);
    }
}
