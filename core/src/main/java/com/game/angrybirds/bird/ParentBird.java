package com.game.angrybirds.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public abstract class ParentBird implements Serializable {
    public int Health_point_Dec;
    protected World world;
    protected Texture texture;
    protected Sprite sprite;
    protected Body body;
    private final float SCALE = 10f;
    public int flag;

    public ParentBird(World world,int Health_point_Dec,String texture,float x,float y,int flag) {
        this.world = world;
        this.Health_point_Dec = Health_point_Dec;
        this.texture = new Texture(texture);
        sprite = new Sprite(this.texture);

        createBody(x,y);
        this.flag=flag;
    }

    public void createBody(float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x/SCALE, y/SCALE);

        body = world.createBody(bodyDef);
        float ballRadius = 2.5f;
        Sprite sprite = getSprite();
        sprite.setSize(2*ballRadius,2*ballRadius);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);

        CircleShape shape = new CircleShape();
        shape.setRadius(ballRadius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.6f;
        body.setUserData(this);

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void dispose(){
        texture.dispose();
        world.destroyBody(body);
    }
}
