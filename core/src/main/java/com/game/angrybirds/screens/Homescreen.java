package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;


public class Homescreen implements Screen, InputProcessor {
    private final Main game;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Rectangle playBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    public Homescreen(Main game) {
        this.game = game;
        background = new Texture("home2.jpg");
        playBtn = new Texture("play.png");
        exitBtn = new Texture("exit.png");

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
//        float xOffset = (1560-1280)/2f;

        game.getBatch().begin();
        game.getBatch().draw(background,0,0,1280,720);
        game.getBatch().draw(playBtn,520,250,250,125);
        game.getBatch().draw(exitBtn,520,150,250,125);
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

        if(playBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new ChooseLevel(game));
            return true;
        }

        if(exitBtnBounds.contains(touchPos.x, touchPos.y)){
            Gdx.app.exit();
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
