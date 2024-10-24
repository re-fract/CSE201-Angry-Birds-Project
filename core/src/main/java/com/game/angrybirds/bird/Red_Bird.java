package com.game.angrybirds.bird;

import com.badlogic.gdx.physics.box2d.*;

public class Red_Bird {
    private Body body;
    private int health;
    public Red_Bird(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y); //initial position
        health=20;

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

