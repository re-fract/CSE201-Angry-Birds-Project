package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

public class ChooseLevel implements Screen, InputProcessor {
    private final Main game;
    private Texture background;
    private Texture level1Btn;
    private Texture level2Btn;
    private Texture num1;
    private Texture num2;
    private Rectangle level1BtnBounds;
    private Rectangle level2BtnBounds;
    private Vector3 touchPos;


    public ChooseLevel(Main game) {
        this.game = game;
        background = new Texture("home.png");
        level1Btn = new Texture("level1Btn.png");
        level2Btn = new Texture("level2Btn.png");
        num1 = new Texture("one.png");
        num2 = new Texture("two.png");
        level1BtnBounds = new Rectangle(227,135,300,450);
        level2BtnBounds = new Rectangle(757,135,300,450);
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
        game.getBatch().draw(level1Btn,level1BtnBounds.x,level1BtnBounds.y,level1BtnBounds.width,level1BtnBounds.height);
        game.getBatch().draw(num1,310,300,128,128);
        game.getBatch().draw(level2Btn,level2BtnBounds.x,level2BtnBounds.y,level2BtnBounds.width,level2BtnBounds.height);
        game.getBatch().draw(num2,850,300,128,128);
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
        level1Btn.dispose();
        level2Btn.dispose();
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

        if(level1BtnBounds.contains(touchPos.x, touchPos.y)){
//            game.setScreen(new Level1_Screen(game));
            game.setScreen(new Tutorial());
            return true;
        }

        if(level2BtnBounds.contains(touchPos.x, touchPos.y)){
//                    game.setScreen();
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
