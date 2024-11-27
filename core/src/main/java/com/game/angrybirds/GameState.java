package com.game.angrybirds;
import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable {
    public ArrayList<float[]> pigPositions; // [x, y, health, radius,flag]
    public ArrayList<float[]> blockPositions; // [x, y, health, flag]
    public ArrayList<float[]> birdPositions; // [x, y, flag]
    public int currentBirdIndex;
//    public World world ;
}
