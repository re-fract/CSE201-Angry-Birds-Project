package com.game.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class Homescreen implements Screen {
    private final Main game;
    private Texture background;
//    private BitmapFont font;
    private Texture playBtn;
    private Texture exitBtn;

    public Homescreen(Main game){
        this.game = game;
        background = new Texture("home.png");
        playBtn = new Texture("play.png");
        exitBtn = new Texture("exit.png");
//        font = new BitmapFont();
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float xOffset = (1560-1280)/2f;

        game.getBatch().begin();
        game.getBatch().draw(background,-xOffset,0,1560,720);
        game.getBatch().draw(playBtn,540,350,250,125);
        game.getBatch().draw(exitBtn,540,250,250,125);

//        font.draw(game.getBatch(),"Play",600,400);
//        font.draw(game.getBatch(),"Exit",600,300);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        exitBtn.dispose();
//        font.dispose();
    }


}
