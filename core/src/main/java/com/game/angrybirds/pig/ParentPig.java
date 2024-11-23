package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ParentPig {
    protected Texture texture;
    protected Sprite sprite;
    protected Body body;
    protected World world;
    protected int health;
    private final float SCALE = 10f;

    public ParentPig(World world, String texture, int x, int y, int health) {
        this.world = world;
        this.texture = new Texture(texture);
        sprite = new Sprite(this.texture);
        this.health = health;

        createBody(x,y);
    }

    public void createBody(int x, int y) {
        //Defining body and its attributes
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/SCALE, y/SCALE);

        body = world.createBody(bodyDef); //Create body in the world

        float ballRadius = 2.5f;
        sprite.setSize(2*ballRadius,2*ballRadius);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);

        CircleShape shape = new CircleShape();
        shape.setRadius(ballRadius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public boolean isAlive() {
        return health>0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            destroy();
        }
    }

    public void destroy() {
        world.destroyBody(body);
        body = null;
    }

    public void dispose(){
        texture.dispose();
        world.destroyBody(body);
    }

    public Body getBody(){
        return body;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
