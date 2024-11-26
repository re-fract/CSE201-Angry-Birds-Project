package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

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
    private final float SCALE = 10f;

    public PauseScreen(Main game, Screen currentScreen, String background) {
        this.game = game;
        this.currentScreen = currentScreen;
        this.background = new Texture(background);
        resumeBtn = new Texture("resume.png");
        exitBtn = new Texture("exit2.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Montserrat-SemiBold.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        parameter.borderWidth = 2;
        parameter.color = new Color(188 / 255f, 104 / 255f, 53 / 255f, 1.0f);
        parameter.borderColor = Color.BLACK;

        font = generator.generateFont(parameter);
        generator.dispose();

        resumeBtnBounds = new Rectangle(500/SCALE, 250/SCALE, 120/SCALE, 120/SCALE);
        exitBtnBounds = new Rectangle(700/SCALE, 250/SCALE, 120/SCALE, 120/SCALE);
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

        font.getData().setScale(0.1f);

        game.getBatch().begin();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().draw(background,0,0,1280/SCALE,720/SCALE);
        font.draw(game.getBatch(),"PAUSED!",490/SCALE,450/SCALE );
        game.getBatch().draw(resumeBtn,resumeBtnBounds.x,resumeBtnBounds.y,resumeBtnBounds.width,resumeBtnBounds.height);
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
        touchPos.set(screenX, screenY, pointer);
        game.getCamera().unproject(touchPos);

        if(resumeBtnBounds.contains(touchPos.x, touchPos.y)){
            game.setScreen(currentScreen);
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
