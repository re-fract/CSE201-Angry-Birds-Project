package com.game.angrybirds.bird;
import com.badlogic.gdx.physics.box2d.*;
public class BlackBird extends ParentBird{
    private Body body;
    private CircleShape shape;
    public BlackBird(World world, float x, float y) {
        super(300,50);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        shape = new CircleShape();
        shape.setRadius(60f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 3f;
        fixtureDef.restitution = 6f;
        body.createFixture(fixtureDef);

    }
}
