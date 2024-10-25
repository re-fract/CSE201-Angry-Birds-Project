package com.game.angrybirds.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

abstract class ParentBird {
    protected int Health_point_Dec;
    protected int speed;
    protected Texture texture;
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    public ParentBird(int Health_point_Dec, int speed,String texture,float x,float y,int width,int height) {
        this.Health_point_Dec = Health_point_Dec;
        this.speed = speed;
        this.texture = new Texture(texture);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void render(SpriteBatch batch) {
        Vector2 position = new Vector2(x, y);
        batch.draw(texture,position.x,position.y,width,height);
    }
}
