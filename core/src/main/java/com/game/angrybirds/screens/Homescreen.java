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
    private Texture loadBtn;
    private Texture exitBtn;
    private Rectangle playBtnBounds;
    private Rectangle loadBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPos;

    public Homescreen(Main game) {
        this.game = game;
        background = new Texture("home2.jpg");
        playBtn = new Texture("play2.png");
        loadBtn = new Texture("load2.png");
        exitBtn = new Texture("exit4.png");

        playBtnBounds = new Rectangle(515, 330, 250, 125);
        loadBtnBounds = new Rectangle(515, 245, 250, 125);
        exitBtnBounds = new Rectangle(515, 160, 250, 125);
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
    public void render(float delta) {
        // Apply viewport and set projection matrix
        game.getViewport().apply();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        Gdx.gl.glClearColor(1f,1f,1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background,0,0,Main.VIRTUAL_WIDTH,Main.VIRTUAL_HEIGHT);
        game.getBatch().draw(playBtn,playBtnBounds.x,playBtnBounds.y,playBtnBounds.width,playBtnBounds.height);
        game.getBatch().draw(loadBtn,loadBtnBounds.x,loadBtnBounds.y,loadBtnBounds.width,loadBtnBounds.height);
        game.getBatch().draw(exitBtn,exitBtnBounds.x,exitBtnBounds.y,exitBtnBounds.width,exitBtnBounds.height);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        loadBtn.dispose();
        exitBtn.dispose();
    }

    @Override
    public boolean keyDown(int i) { return false; }
    @Override
    public boolean keyUp(int i) { return false; }
    @Override
    public boolean keyTyped(char c) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Use viewport to unproject
        touchPos.set(screenX, screenY, 0);
        game.getViewport().unproject(touchPos);

        if(playBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new ChooseLevel(game));
            return true;
        }
        if(loadBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(new LoadGameScreen(game));
        }
        if(exitBtnBounds.contains(touchPos.x, touchPos.y)){
            Gdx.app.exit();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    @Override
    public boolean touchDragged(int i, int i1, int i2) { return false; }
    @Override
    public boolean mouseMoved(int i, int i1) { return false; }
    @Override
    public boolean scrolled(float v, float v1) { return false; }
}
