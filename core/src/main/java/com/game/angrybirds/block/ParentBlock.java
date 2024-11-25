package com.game.angrybirds.block;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ParentBlock {
    protected Texture texture;
    protected Body body;
    protected World world;
    protected int health;
    protected int width;
    protected Sprite sprite;
    protected int height;
    protected boolean hasFallen = false;
    protected float initialYPos;
    protected boolean markedForDestruction;

    private final float SCALE = 10f;
    private final float fallThreshold = 1f;

    public ParentBlock(World world, String texture, float x, float y, int health, int width, int height) {
        this.world = world;
        this.texture = new Texture(texture);
        sprite = new Sprite(this.texture);
        this.health = health;
        this.width = width;
        this.height = height;
        this.initialYPos = y/SCALE;
        createBody(x,y);
    }

    public void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/SCALE, y/SCALE);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2f/SCALE, height/2f/SCALE);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.setUserData(this);

        sprite.setSize(width/SCALE, height/SCALE);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void checkFall() {
        float currentYPos = body.getPosition().y;
        if (!hasFallen && body.getPosition().y < currentYPos - fallThreshold) {
            takeDamage(1);
            initialYPos = currentYPos;
            hasFallen = true;
            System.out.println("Block has fallen more than " + fallThreshold + " meters and took 1 damage. Health left: " + health);
        }
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getHealth(){
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            markedForDestruction = true;
        }
    }

    public boolean isDestroyed() {
        return health<=0;
    }

    public boolean isMarkedForDestruction() {
        return markedForDestruction;
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
    }

    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
