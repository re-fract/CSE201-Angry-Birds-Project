package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ParentPig {
    protected Texture texture;
    protected Vector2 position;
    protected Body body;
    protected World world;
    protected int health;
    protected int width;
    protected int height;

    public ParentPig(World world, String texture, int x, int y, int health, int width, int height) {
        this.texture = new Texture(texture);
        this.position = new Vector2(x, y);
        this.health = health;
        this.width = width;
        this.height = height;

        this.world = world;
        createBody();
    }

    public void createBody() {
        //Defining body and its attributes
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        body = world.createBody(bodyDef); //Create body in the world

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public boolean isAlive() {
        return health>0;
    }

    public void render(SpriteBatch batch) {
        if(isAlive()) {
            batch.draw(texture, position.x, position.y, width, height);
        }
    }

    public void dispose(){
        texture.dispose();
        world.destroyBody(body);
    }
}
