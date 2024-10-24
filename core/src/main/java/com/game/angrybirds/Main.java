package com.game.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.angrybirds.screens.Homescreen;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Homescreen(this));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch(){
        return batch;
    }
}
