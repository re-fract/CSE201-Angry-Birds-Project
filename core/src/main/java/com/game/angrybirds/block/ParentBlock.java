package com.game.angrybirds.block;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ParentBlock {
    protected Texture texture;
    protected Vector2 position;
    protected Body body;
    protected World world;
    protected int health;
    protected int width;
    protected int height;

    public ParentBlock(World world, String texture, int x, int y, int health, int width, int height) {
        this.world = world;
        this.texture = new Texture(texture);
        this.position = new Vector2(x,y);
        this.health = health;
        this.width = width;
        this.height = height;

        createBody();
    }

    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        body = world.createBody(bodyDef); //Create body in the world

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2f, height/2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        Vector2 position = body.getPosition();
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
    }
}
