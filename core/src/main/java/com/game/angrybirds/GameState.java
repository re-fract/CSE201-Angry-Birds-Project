//basic structure
package com.game.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;

public class GameState implements Serializable {
    

    public int current_Level;
    public int pig_Alive;
    public int bird_Alive;
    public int box_Left;
    //other attributs

    public void Save_GameState(GameState gameState) {
            FileHandle file = Gdx.files.local("gamestate.sav");
    }

    public GameState Load_GameState() {
        try {
            FileHandle file = Gdx.files.local("gamestate.sav");
            if (file.exists()) {
                ObjectInputStream in = new ObjectInputStream(file.read());
                GameState gameState = (GameState) in.readObject();
                return gameState;
            } 
            else {
                return null; 
            }
        }
        catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
