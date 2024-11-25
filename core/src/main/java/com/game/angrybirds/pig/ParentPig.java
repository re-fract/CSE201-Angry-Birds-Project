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
    protected float radius;
    protected boolean hasFallen = false;
    protected float initialYPos;
    protected boolean markForDestruction = false;

    private final float fallThreshold = 1f;
    private final float SCALE = 10f;

    public ParentPig(World world, String texture, int x, int y, int health, float radius) {
        this.world = world;
        this.texture = new Texture(texture);
        sprite = new Sprite(this.texture);
        this.health = health;
        this.radius = radius;
        this.initialYPos = y/SCALE;
        createBody(x,y);
    }

    public void createBody(int x, int y) {
        //Defining body and its attributes
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/SCALE, y/SCALE);

        body = world.createBody(bodyDef); //Create body in the world

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
        if (currentYPos < initialYPos - fallThreshold) {
            takeDamage(1);
            initialYPos = currentYPos;
//            hasFallen = true;
            System.out.println("Pig has fallen more than " + fallThreshold + " meters and took 1 damage. Health left: " + health);
        }
    }

    public boolean isDestroyed() {
        return health<=0;
    }

    public void markForDestruction() {
        markForDestruction = true;
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

    public Body getBody(){
        return body;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
