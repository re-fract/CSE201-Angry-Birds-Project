package com.game.angrybirds.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class ParentPig implements Serializable {
    protected Texture texture;
    protected Sprite sprite;
    protected Body body;
    protected World world;
    protected int health;
    protected float radius;
    protected boolean hasFallen = false;
    protected float initialYPos;
    protected boolean markForDestruction = false;
    protected int flag;

    private final float fallThreshold = 2f;
    private final float SCALE = 10f;


    public ParentPig(World world, String texture, float x, float y, int health, float radius, int flag) {
        this.world = world;
        this.texture = new Texture(texture);
        sprite = new Sprite(this.texture);
        this.health = health;
        this.radius = radius;
        this.initialYPos = y/SCALE;
        createBody(x,y);
        this.flag = flag;
    }

    public void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/SCALE, y/SCALE);

        body = world.createBody(bodyDef);

//        float ballRadius = 2.5f;
        sprite.setSize(2*radius,2*radius);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f;
        body.setUserData(this);

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public int getHealth(){
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            markForDestruction = true;
        }
    }

    public void checkFall() {
        float currentYPos = body.getPosition().y;
        if (!hasFallen && currentYPos < initialYPos - fallThreshold) {
            takeDamage(1);
            initialYPos = currentYPos;
            hasFallen = true;
            System.out.println("Pig has fallen more than " + fallThreshold + " meters and took 1 damage. Health left: " + health);
        }
    }

    public boolean isDestroyed() {
        return health<=0;
    }

    public boolean isMarkedForDestruction() {
        return markForDestruction;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void dispose(){
        texture.dispose();
        world.destroyBody(body);
    }

    public float getRadius(){
        return radius;
    }

    public int getFlag(){
        return flag;
    }

    public Body getBody(){
        return body;
    }

    public Sprite getSprite(){
        return sprite;
    }
}

