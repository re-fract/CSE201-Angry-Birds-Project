package com.game.angrybirds.bird;
public abstract class ParentBird {
    public int Health_point_Dec;
    public int speed;
    public Texture texture;
    public float l;
    public float m;
    public int width;
    public int height;
    public ParentBird(int Health_point_Dec, int speed) {
        this.Health_point_Dec = Health_point_Dec;
        this.speed = speed;
    }
    public void render(SpriteBatch batch) {
        Vector2 position = new Vector2(l,m);
        batch.draw(texture,position.x,position.y,width,height);
    }
}
