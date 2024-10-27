package com.game.angrybirds.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;


public class Homescreen implements Screen, InputProcessor {
    private final Main game;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture dummyBtn;
    private Rectangle playBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    public Homescreen(Main game) {
        this.game = game;
        background = new Texture("home2.jpg");
        playBtn = new Texture("play.png");
        exitBtn = new Texture("exit.png");

        dummyBtn = new Texture("dummyBtn.png");

        playBtnBounds = new Rectangle(520, 250, 250, 125);
        exitBtnBounds = new Rectangle(520, 150, 250, 125);
        touchPos = new Vector3();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background,0,0,1280,720);
        game.getBatch().draw(playBtn,playBtnBounds.x,playBtnBounds.y,playBtnBounds.width,playBtnBounds.height);
        game.getBatch().draw(exitBtn,exitBtnBounds.x,exitBtnBounds.y,exitBtnBounds.width,exitBtnBounds.height);

        game.getBatch().draw(dummyBtn,520,50,70,70);
        game.getBatch().draw(dummyBtn,650,50,70,70);

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
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, pointer);
        game.getCamera().unproject(touchPos);

        //Making dummy btn bounds
        Rectangle dummyBtnBounds1 = new Rectangle(520,50,70,70);
        Rectangle dummyBtnBounds2 = new Rectangle(650,50,70,70);

        if(playBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new ChooseLevel(game));
            return true;
        }

        if(exitBtnBounds.contains(touchPos.x, touchPos.y)){
            Gdx.app.exit();
            return true;
        }

        if(dummyBtnBounds1.contains(touchPos.x, touchPos.y)){
            game.setScreen(new GameOver(game));
            return true;
        }

        if(dummyBtnBounds2.contains(touchPos.x, touchPos.y)){
            game.setScreen(new LevelCompleted(game));
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
