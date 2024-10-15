package com.game.angrybirds.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends InputAdapter implements Screen  {
    private final Main game;
    private Texture background;
    private Texture level1Btn;
    private Texture level2Btn;

    // Hitbox areas for buttons
    private Rectangle level1BtnBounds;
    private Rectangle level2BtnBounds;

    // To get input coordinates
    private Vector3 touchPoint;

    public MenuScreen(Main game) {
        this.game = game;
        background = new Texture("menu.png");
        level1Btn = new Texture("level1.png");
        level2Btn = new Texture("level2.png");
        level1BtnBounds = new Rectangle(400, 300, 170, 170); // Position and size of the Level 1 button
        level2BtnBounds = new Rectangle(700, 300, 170, 170); // Position and size of the Level 2 button
        touchPoint = new Vector3();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        // Draw the background and buttons
        game.getBatch().draw(background, 0, 0, 1560, 720);
        game.getBatch().draw(level1Btn, level1BtnBounds.x, level1BtnBounds.y, level1BtnBounds.width, level1BtnBounds.height);
        game.getBatch().draw(level2Btn, level2BtnBounds.x, level2BtnBounds.y, level2BtnBounds.width, level2BtnBounds.height);
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
        level1Btn.dispose();
        level2Btn.dispose();
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPoint.set(screenX, screenY, 0);
        game.getCamera().unproject(touchPoint);
        if (level1BtnBounds.contains(touchPoint.x, touchPoint.y)) {
            // transition to Level 1 Screen
            game.setScreen(new Level1Screen(game));
        }
        if (level2BtnBounds.contains(touchPoint.x, touchPoint.y)) {
            // transition to Level 2 Screen
            game.setScreen(new Level2_Screen(game));
        }
        return true;
    }
}
