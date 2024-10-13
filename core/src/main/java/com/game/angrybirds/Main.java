package com.game.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
//    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Homescreen(this));
//        image = new Texture("libgdx.png");

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

    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }

    public SpriteBatch getBatch(){
        return batch;
    }
}
