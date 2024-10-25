package com.game.angrybirds.bird;
public abstract class ParentBird {
    private int Health_point_Dec;
    private int speed;
    public ParentBird(int Health_point_Dec, int speed) {
        this.Health_point_Dec = Health_point_Dec;
        this.speed = speed;
    }
}
