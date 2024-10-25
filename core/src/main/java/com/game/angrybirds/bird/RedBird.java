package com.game.angrybirds.bird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
public class RedBird extends ParentBird{
    private Body body;
    CircleShape shape;
    public RedBird(World world, float x, float y) {
        super(world,100,50,"redbird.png",x,y,70,70);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        shape = new CircleShape();
        shape.setRadius(49.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 3f;
        fixtureDef.restitution = 6f;
        body.createFixture(fixtureDef);
    }

    @Override
    public void dispose(){
        super.dispose();
        world.destroyBody(body);
    }
    public Body getBody() {
        return  body;
    }

}

