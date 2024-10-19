package com.game.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LevelEnd extends InputAdapter implements Screen  {
    private final Main game;
    private Texture level_comp_log;
    private Texture menu;
    private Texture Quit;
    private Rectangle menuBtnBounds;
    private Rectangle exitBtnBounds;


    private Vector3 touchPoint;

    public LevelEnd(Main game) {
        this.game = game;
        level_comp_log = new Texture("success.png");
        menu = new Texture("newmenubut.png");
        Quit=new Texture("quit.png");

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

        game.getBatch().draw(level_comp_log, 0, 0, 1400, 750);
        game.getBatch().draw(menu,550,90,100,100);
        game.getBatch().draw(Quit, 750, 90, 100, 100);
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
        Quit.dispose();
    }



    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convert screen coordinates to game world coordinates
        game.getCamera().unproject(touchPoint.set(screenX, screenY, 0));

        
        if (menuBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            // go to level screen
            game.setScreen(new MenuScreen(game));
        }

        
        if (exitBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            // Exit the game
            Gdx.app.exit();
        }
        return true;
    }
}
