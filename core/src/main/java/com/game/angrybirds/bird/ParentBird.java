package com.game.angrybirds.bird;
public abstract class ParentBird {
    public int Health_point_Dec;
    public int speed;
    public Texture texture;
    public float l;
    public float m;
    public int width;
    public int height;
    public parent_bird(int Health_point_Dec, int speed,Texture texture,float l,float m,int width,int height) {
        this.Health_point_Dec = Health_point_Dec;
        this.speed = speed;
        this.texture = texture;
        this.l = l;
        this.m = m;
        this.width = width;
        this.height = height;
    }
    public void render(SpriteBatch batch) {
        Vector2 position = new Vector2(l,m);
        batch.draw(texture,position.x,position.y,width,height);
    }
}
