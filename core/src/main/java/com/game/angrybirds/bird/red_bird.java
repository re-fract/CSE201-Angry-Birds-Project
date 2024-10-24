package com.game.angrybirds.bird;

import com.badlogic.gdx.physics.box2d.*;

public class red_bird {
    private Body body;

    public red_bird(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y); //initial position

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(30f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 3f;
        fixtureDef.restitution = 6f;  // Bounce

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody() {
        return body;
    }


}

