package com.game.angrybirds.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.angrybirds.Main;

public class LevelCompleted extends InputAdapter implements Screen  {
    private final Main game;
    private Texture level_comp_log;
    private Texture menu;
    private Texture exit;
    private Rectangle menuBtnBounds;
    private Rectangle exitBtnBounds;


    private Vector3 touchPoint;

    public LevelCompleted(Main game) {
        this.game = game;
        level_comp_log = new Texture("success.png");
        menu = new Texture("menuBtn.png");
        exit = new Texture("exit2.png");

        menuBtnBounds = new Rectangle(550, 90, 100, 100);
        exitBtnBounds = new Rectangle(750, 90, 100, 100);

        touchPoint = new Vector3();


        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        game.getBatch().draw(level_comp_log, 0, 0, 1280, 720);
        game.getBatch().draw(menu,550,90,100,100);
        game.getBatch().draw(exit, 750, 90, 100, 100);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        menu.dispose();
        level_comp_log.dispose();
        exit.dispose();
    }



    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        game.getCamera().unproject(touchPoint.set(screenX, screenY, 0));

        if (menuBtnBounds.contains(touchPoint.x, touchPoint.y)) {

            game.setScreen(new Homescreen(game));
        }


        if (exitBtnBounds.contains(touchPoint.x, touchPoint.y)) {

            Gdx.app.exit();
        }
        return true;
    }
}
