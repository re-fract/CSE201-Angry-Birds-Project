package com.game.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Homescreen extends InputAdapter implements Screen  {
    private final Main game;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Rectangle playBtnBounds;
    private Rectangle exitBtnBounds;
    private Vector3 touchPoint;
    public Homescreen(Main game){
        this.game = game;
        background = new Texture("menu.png");
        playBtn = new Texture("playnew.png");
        exitBtn = new Texture("quit.png");
        playBtnBounds = new Rectangle(470, 150, 400, 400);
        exitBtnBounds = new Rectangle(10, 10, 100, 100);
        touchPoint = new Vector3();
    }
    public boolean touchDown(int screenX, int screenY,int pointer, int button) {
        // Convert screen coordinates to game world coordinates
        touchPoint.set(screenX, screenY,0);//setting the vector with where you touched
        game.getCamera().unproject(touchPoint);//unproject is method of Camera which give access
//        to game world co-ordinates

        // Check if play button is clicked
        if (playBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            game.setScreen(new MenuScreen(game));  // Switch to the menu screen
        }

        // Check if exit button is clicked
        if (exitBtnBounds.contains(touchPoint.x, touchPoint.y)) {
            Gdx.app.exit();  // Exit the game
        }
        return true;
    }
    @Override
    public void show(){
        Gdx.input.setInputProcessor(this);//by calling this in the show you can telling libgdx that this homescreen can take input
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float xOffset = (1560-1280)/2f;
        game.getBatch().begin();
        game.getBatch().draw(background, -xOffset, 0, 1560, 720);
        game.getBatch().draw(playBtn, playBtnBounds.x, playBtnBounds.y, playBtnBounds.width, playBtnBounds.height);
        game.getBatch().draw(exitBtn, exitBtnBounds.x, exitBtnBounds.y, exitBtnBounds.width, exitBtnBounds.height);
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
        playBtn.dispose();
        exitBtn.dispose();
    }
}
