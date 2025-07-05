package com.game.angrybirds.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

public class GameOver implements Screen, InputProcessor {
    private final Main game;
    private Texture pigLaugh;
    private Texture retryBtn;
    private Texture exitBtn;
    private int level;
    private BitmapFont font;
    private Rectangle retryBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    GameOver(Main game, int level) {
        this.game = game;
        this.level = level;
        pigLaugh = new Texture("piglaugh2.png");
        retryBtn = new Texture("retry.png");
        exitBtn = new Texture("exit3.png");

        font = new BitmapFont(Gdx.files.internal("LuckiestGuy.fnt"));
        font.getData().setScale(0.08f);

        retryBtnBounds = new Rectangle(400,170,200,100);
        exitBtnBounds = new Rectangle(700,170,200,100);
        touchPos = new Vector3();

        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void show() {
        // Ensure viewport is properly applied when screen is shown
        game.getViewport().apply();
        game.getCamera().update();
    }

    @Override
    public void render(float v) {
        // Apply viewport and set projection matrix
        game.getViewport().apply();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        font.draw(game.getBatch(),"LEVEL FAILED!",465,600);
        game.getBatch().draw(pigLaugh,400,300,502,250);
        game.getBatch().draw(retryBtn,retryBtnBounds.x,retryBtnBounds.y,retryBtnBounds.width,retryBtnBounds.height);
        game.getBatch().draw(exitBtn,exitBtnBounds.x,exitBtnBounds.y,exitBtnBounds.width,exitBtnBounds.height);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height, true);
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
        pigLaugh.dispose();
        retryBtn.dispose();
        exitBtn.dispose();
        font.dispose();
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
        touchPos.set(screenX, screenY, 0);
        game.getViewport().unproject(touchPos);

        if(retryBtnBounds.contains(touchPos.x, touchPos.y)){
            if(level == 1) {
                game.setScreen(new Level1_Screen(game, false));
            }
            else if(level == 2) {
                game.setScreen(new Level2_Screen(game, false));
            }
            else if(level == 3) {
                game.setScreen(new Level3_Screen(game, false));
            }
            return true;
        }

        if(exitBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new Homescreen(game));
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
