package com.game.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
//    private Texture image;
    private OrthographicCamera camera;
    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Homescreen(this));
//        image = new Texture("libgdx.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
//        FreeTypeFontGenerator
    }

    @Override
    public void render() {
        super.render();
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, 140, 210);
//        batch.end();
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }

    public SpriteBatch getBatch(){
        return batch;
    }
}
