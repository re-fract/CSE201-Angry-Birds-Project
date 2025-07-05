package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;
import com.game.angrybirds.screens.Homescreen; // Add import for Homescreen

public class PauseScreen implements Screen, InputProcessor {
    private final Main game;
    private final Screen currentScreen;
    private Texture background;
    private Texture resumeBtn;
    private Texture exitBtn;
    private BitmapFont font;
    private Rectangle resumeBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    public PauseScreen(Main game, Screen currentScreen, String background) {
        this.game = game;
        this.currentScreen = currentScreen;
        this.background = new Texture(background);
        resumeBtn = new Texture("resume.png");
        exitBtn = new Texture("exit2.png");

        font = new BitmapFont(Gdx.files.internal("LuckiestGuy.fnt"));
        font.getData().setScale(2.0f);

        // Use UI coordinates instead of physics coordinates
        resumeBtnBounds = new Rectangle(440, 250, 120, 120);
        exitBtnBounds = new Rectangle(640, 250, 120, 120);
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
        
        Gdx.gl.glClearColor(1f,1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background,0,0,Main.VIRTUAL_WIDTH,Main.VIRTUAL_HEIGHT);
        font.draw(game.getBatch(),"PAUSED!",540,450);
        game.getBatch().draw(resumeBtn,resumeBtnBounds.x,resumeBtnBounds.y,resumeBtnBounds.width,resumeBtnBounds.height);
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
        resumeBtn.dispose();
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

        if(resumeBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(currentScreen);
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
