package com.game.angrybirds.screens;

import com.badlogic.gdx.physics.box2d.World;

import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable {

    public ArrayList<int[]> pigPositions; // [x, y, health,flag]
    public ArrayList<int[]> blockPositions; // [x, y, health,flag]
    public ArrayList<int[]> birdPositions; // [x, y,flag]
    public int currentBirdIndex;
    public World world ;



}
