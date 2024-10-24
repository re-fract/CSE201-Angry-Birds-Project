package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

public class PauseScreen implements Screen, InputProcessor {
    private final Main game;
    private Texture background;
    private Texture resumeBtn;
    private Texture exitBtn;
    private Rectangle resumeBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;
    private int level;

    public PauseScreen(Main game, String bg, int level) {
        this.game = game;
        this.level = level;
        background = new Texture(bg);
        resumeBtn = new Texture("resume.png");
        exitBtn = new Texture("exit2.png");
        resumeBtnBounds = new Rectangle(500, 250, 120, 120);
        exitBtnBounds = new Rectangle(700, 250, 120, 120);
        touchPos = new Vector3();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1f,1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,1280,720);
        game.getBatch().draw(resumeBtn,500,250,120,120);
        game.getBatch().draw(exitBtn,700,250,120,120);
        game.getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {

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
        resumeBtn.dispose();
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

        if(resumeBtnBounds.contains(touchPos.x, touchPos.y)){
            if(level == 1){
                game.setScreen(new Level1_Screen(game));
            }
            else if(level == 2){
//                game.setScreen();
            }
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
