package com.game.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


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
