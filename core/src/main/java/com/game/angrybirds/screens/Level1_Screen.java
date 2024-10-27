package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.game.angrybirds.Main;
import com.game.angrybirds.bird.*;
import com.game.angrybirds.block.ParentBlock;
import com.game.angrybirds.block.WoodBlock;
import com.game.angrybirds.pig.*;

import java.util.ArrayList;

public class Level1_Screen extends InputAdapter implements Screen {
    private final Main game;
    private Texture background;
    private ArrayList<ParentPig> pigs;
    private ArrayList<ParentBlock> blocks;
    private RedBird redBird;
    private Texture slingshot;
    private Texture pauseBtnTexture;
    private World world;

    // Rectangle bounds for detecting button clicks
    private Rectangle pauseBtnBounds;
    private Vector3 touchPoint;

    public Level1_Screen(Main game) {
        this.game = game;

        // Load textures
        background = new Texture("level1.png"); // Background for level 2
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();

        pauseBtnTexture = new Texture("pause.png");
        slingshot = new Texture("slingshot.png");         // Slingshot texture

        // Set up touch point and pause button bounds
        touchPoint = new Vector3();
        pauseBtnBounds = new Rectangle(1220, 660, 50, 50); // Set bounds for the pause button
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        world = new World(new Vector2(0, -9.81f), true);

        redBird = new RedBird(world,90,100);

        pigs.add(new NormalPig(world,950,235));
        pigs.add(new CrownPig(world,950,144));

        blocks.add(new WoodBlock(world,800,100));
        blocks.add(new WoodBlock(world,900,100));
        blocks.add(new WoodBlock(world,1000,100));
        blocks.add(new WoodBlock(world,850,144));
        blocks.add(new WoodBlock(world,1050,144));
        blocks.add(new WoodBlock(world,900,190));
        blocks.add(new WoodBlock(world,1000,190));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720); // Draw background
        game.getBatch().draw(slingshot, 100, 100, 100, 200); // Draw slingshot


        redBird.render(game.getBatch());

        for(ParentBlock block : blocks) {
            block.render(game.getBatch());
        }

        for(ParentPig pig : pigs) {
            pig.render(game.getBatch());
        }

        game.getBatch().draw(pauseBtnTexture, pauseBtnBounds.x, pauseBtnBounds.y, pauseBtnBounds.width, pauseBtnBounds.height); // Draw pause button

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
        slingshot.dispose();
        redBird.dispose();
        for(ParentBlock block : blocks) {
            block.dispose();
        }
        for(ParentPig pig : pigs) {
            pig.dispose();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touchPoint.set(screenX, screenY, 0);
        game.getCamera().unproject(touchPoint);

        // Check if pause button is clicked
        if (pauseBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            game.setScreen(new PauseScreen(game,this));
        }

        return true;
    }
}
