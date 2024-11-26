package com.game.angrybirds.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

public class GameOver implements Screen, InputProcessor {
    private final Main game;
    private Texture pigLaugh;
    private Texture retryBtn;
    private Texture exitBtn;
    private BitmapFont font;
    private Rectangle retryBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    GameOver(Main game) {
        this.game = game;
        pigLaugh = new Texture("piglaugh2.png");
        retryBtn = new Texture("retry.png");
        exitBtn = new Texture("exit3.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("LuckiestGuy-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 2;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;

        font = generator.generateFont(parameter);
        generator.dispose();

        retryBtnBounds = new Rectangle(400,170,200,100);
        exitBtnBounds = new Rectangle(700,170,200,100);
        touchPos = new Vector3();

        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getCamera().setToOrtho(false, 1280, 720);

        game.getBatch().begin();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        font.draw(game.getBatch(),"LEVEL FAILED!",465,600);
        game.getBatch().draw(pigLaugh,400,300,502,250);
        game.getBatch().draw(retryBtn,retryBtnBounds.x,retryBtnBounds.y,retryBtnBounds.width,retryBtnBounds.height);
        game.getBatch().draw(exitBtn,exitBtnBounds.x,exitBtnBounds.y,exitBtnBounds.width,exitBtnBounds.height);
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
        touchPos.set(screenX, screenY, pointer);
        game.getCamera().unproject(touchPos);

        if(retryBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new Level1_Screen(game));
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
