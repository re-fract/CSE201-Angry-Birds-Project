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
    //other attributes

    public void Save_GameState(){
    }

}
