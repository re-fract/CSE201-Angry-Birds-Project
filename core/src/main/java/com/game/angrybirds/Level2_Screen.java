package com.game.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.game.angrybirds.bird.red_bird;

public class Level2_Screen extends InputAdapter implements Screen {
    private final Main game;
    private Texture background;
    private Texture bird;
    private Texture pig;
    private Texture slingshot;
    private Texture block;
    private red_bird newbird;
    private World world;
    // Rectangle bounds for detecting button clicks
    private Rectangle pauseBtnBounds;
    private Vector3 touchPoint;

    public Level2_Screen(Main game) {
        this.game = game;

        // Load textures
        background = new Texture("game_background.png"); // Background for level 2
        pig = new Texture("pig.png");                       // Pig texture
        slingshot = new Texture("slingshoot.png");         // Slingshot texture
        block = new Texture("block.png");                   // Block texture
        bird = new Texture("bird.jpg");                     // Bird texture

        // Set up touch point and pause button bounds
        touchPoint = new Vector3();
        pauseBtnBounds = new Rectangle(10, 10, 50, 50); // Set bounds for the pause button
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        world = new World(new Vector2(0, -9.81f), true);
        newbird = new red_bird(world, 90, 150);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1560, 720); // Draw background
        game.getBatch().draw(slingshot, 100, 150, 100, 200); // Draw slingshot

        Vector2 birdPosition = newbird.getBody().getPosition();
       game.getBatch().draw(bird, birdPosition.x , birdPosition.y , 70, 70);

        // Draw blocks and pigs
        game.getBatch().draw(block, 900, 148, 100, 50);  // Block 1
        game.getBatch().draw(block, 1000, 148, 100, 50); // Block 2
        game.getBatch().draw(block, 1100, 148, 100, 50); // Block 3

        game.getBatch().draw(block, 950, 192, 100, 50);  // Block 4
        game.getBatch().draw(pig, 1050, 192, 70, 70);    // Pig in the middle
        game.getBatch().draw(block, 1150, 192, 100, 50); // Block 5

        game.getBatch().draw(block, 1000, 237, 100, 50); // Block 6
        game.getBatch().draw(block, 1100, 237, 100, 50); // Block 7

        game.getBatch().draw(pig, 1050, 285, 70, 70);    // Pig on top


        game.getBatch().draw(new Texture("pause.png"), pauseBtnBounds.x, pauseBtnBounds.y, pauseBtnBounds.width, pauseBtnBounds.height); // Draw pause button

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
        bird.dispose();
        pig.dispose();
        slingshot.dispose();
        block.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touchPoint.set(screenX, screenY, 0);
        game.getCamera().unproject(touchPoint);

        // Check if pause button is clicked
        if (pauseBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            game.setScreen(new PauseScreen(game));
        }

        return true;
    }
}
