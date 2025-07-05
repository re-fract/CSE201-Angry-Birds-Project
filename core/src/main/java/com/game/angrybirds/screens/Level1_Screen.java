package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.game.angrybirds.Main;
import com.game.angrybirds.bird.*;
import com.game.angrybirds.block.GlassBlock;
import com.game.angrybirds.block.ParentBlock;
import com.game.angrybirds.block.StoneBlock;
import com.game.angrybirds.block.WoodBlock;
import com.game.angrybirds.pig.*;

import java.io.*;
import java.util.ArrayList;

public class Level1_Screen extends InputAdapter implements Screen, Serializable {
    private final Main game;
    private Texture background;
    private ArrayList<ParentPig> pigs;
    private ArrayList<ParentBlock> blocks;
    private ArrayList<ParentBird> birds;
    private int currentBirdIndex;
    private Texture slingshot;
    private Texture pauseBtnTexture;
    private World world;
    private OrthographicCamera camera;

    private Vector2 initialBallPosition;
    private Vector2 slingShotStartPoint;
    private ShapeRenderer shapeRenderer;

    private boolean loadGame;
    private boolean isIntialized = false;
    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private final float SCALE = 10f;

    private MouseJoint joint;
    private MouseJointDef jointDef;

    private Rectangle pauseBtnBounds;
    private Vector3 touchPoint;

    private boolean allBirdsShot = false;
    private float gameOverTimer = 0f;
    private float levelCompleteTimer = 0f;

    private Box2DDebugRenderer debugRenderer;
    private final ArrayList<Body> bodiesToDestroy;

    public Level1_Screen(Main game, boolean loadGame) {
        this.game = game;
        this.bodiesToDestroy = new ArrayList<>();
        this.loadGame = loadGame;

        background = new Texture("level1.png");
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        birds = new ArrayList<>();

        pauseBtnTexture = new Texture("pause.png");
        slingshot = new Texture("slingshot.png");

        slingShotStartPoint = new Vector2();
        shapeRenderer = new ShapeRenderer();
        touchPoint = new Vector3();

        pauseBtnBounds = new Rectangle(1180, 650, 80, 60);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        // Ensure main viewport is properly applied
        game.getViewport().apply();
        game.getCamera().update();

        if(isIntialized) return;

        world = new World(new Vector2(0, -9.81f), true);

        // Use the main game's camera and viewport for consistency
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280 / SCALE, 720 / SCALE);
        camera.update();
        debugRenderer = new Box2DDebugRenderer();

        initialBallPosition = new Vector2(155/SCALE,255/SCALE);
        birds.add(new RedBird(world, 155, 255));

        pigs.add(new NormalPig(world, 950, 150,3,2.5f));

        blocks.add(new WoodBlock(world, 900, 110));
        blocks.add(new WoodBlock(world, 1000, 110));

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);

        EdgeShape groundShape = new EdgeShape();
        groundShape.set(-500, 10, 500, 10);
        groundBody.createFixture(groundShape, 1);
        groundShape.dispose();

        createWalls();

        jointDef = new MouseJointDef();
        jointDef.bodyA = groundBody;
        jointDef.collideConnected = true;
        jointDef.maxForce = 1000.0f * birds.get(0).getBody().getMass();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();

                if (bodyA.getUserData() instanceof ParentBird || bodyB.getUserData() instanceof ParentBird) {
                    ParentBird currentBird = null;
                    if (bodyA.getUserData() instanceof ParentBird) {
                        currentBird = (ParentBird) bodyA.getUserData();
                    } else if (bodyB.getUserData() instanceof ParentBird) {
                        currentBird = (ParentBird) bodyB.getUserData();
                    }

                    if (currentBird != null) {
                        float speed = currentBird.getBody().getLinearVelocity().len();
                        int damage = getDamageBasedOnSpeed(speed);

                        if (bodyA.getUserData() instanceof ParentPig || bodyB.getUserData() instanceof ParentPig) {
                            ParentPig pig = (bodyA.getUserData() instanceof ParentPig) ? (ParentPig) bodyA.getUserData() : (ParentPig) bodyB.getUserData();
                            pig.takeDamage(damage);
                            System.out.println("Pig took " + damage + " damage. Health left: " + pig.getHealth());
                        }
                        else if (bodyA.getUserData() instanceof ParentBlock || bodyB.getUserData() instanceof ParentBlock) {
                            ParentBlock block = (bodyA.getUserData() instanceof ParentBlock) ? (ParentBlock) bodyA.getUserData() : (ParentBlock) bodyB.getUserData();
                            block.takeDamage(damage);
                            System.out.println("Block took " + damage + " damage. Health left: " + block.getHealth());
                        }
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });

        isIntialized = true;
    }

    public int getDamageBasedOnSpeed(float speed) {
        if (speed > 30) {
            return 3;
        } else if (speed > 15) {
            return 2;
        } else if (speed > 5) {
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

        debugRenderer.render(world, camera.combined);

        if(allBirdsShot){
            gameOverTimer += delta;

            if(gameOverTimer >= 12f){
                game.setScreen(new GameOver(game,1));
            }
        }

        if(pigs.isEmpty()){
            levelCompleteTimer += delta;

            if(levelCompleteTimer >= 3f) {
                game.setScreen(new LevelCompleted(game));
            }
        }

        // Apply main viewport for UI elements
        game.getViewport().apply();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,Main.VIRTUAL_WIDTH,Main.VIRTUAL_HEIGHT);
        game.getBatch().draw(pauseBtnTexture, pauseBtnBounds.x, pauseBtnBounds.y, pauseBtnBounds.width, pauseBtnBounds.height);
        game.getBatch().end();

        // Use physics camera for game objects
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(slingshot, 100/SCALE, 100/SCALE, 100/SCALE, 200/SCALE);

        ParentBird currentBird = birds.get(currentBirdIndex);
        currentBird.getSprite().setPosition(
            currentBird.getBody().getPosition().x - currentBird.getSprite().getWidth() / 2,
            currentBird.getBody().getPosition().y - currentBird.getSprite().getHeight() / 2
        );
        currentBird.getSprite().setRotation(MathUtils.radiansToDegrees * currentBird.getBody().getAngle());
        currentBird.getSprite().draw(game.getBatch());

        for (int i = 0; i < birds.size(); i++) {
            if (i != currentBirdIndex) {
                ParentBird bird = birds.get(i);
                bird.getSprite().setPosition(
                    bird.getBody().getPosition().x - bird.getSprite().getWidth() / 2,
                    bird.getBody().getPosition().y - bird.getSprite().getHeight() / 2
                );
                bird.getSprite().setRotation(MathUtils.radiansToDegrees * bird.getBody().getAngle());
                bird.getSprite().draw(game.getBatch());
            }
        }

        for (int i = 0; i < blocks.size(); i++) {
            ParentBlock block = blocks.get(i);
            block.checkFall();

            if (block != null && !block.isDestroyed()) {
                block.getSprite().setPosition(
                    block.getBody().getPosition().x - block.getSprite().getWidth() / 2,
                    block.getBody().getPosition().y - block.getSprite().getHeight() / 2
                );
                block.getSprite().setRotation(MathUtils.radiansToDegrees * block.getBody().getAngle());
                block.getSprite().draw(game.getBatch());
            }

            if (block != null && block.isMarkedForDestruction()) {
                bodiesToDestroy.add(block.getBody());
                block.setBody(null);
                blocks.remove(i);
                i--;
            }
        }

        for (int i = 0; i < pigs.size(); i++) {
            ParentPig pig = pigs.get(i);
            pig.checkFall();

            if(pig != null && !pig.isDestroyed()) {
                pig.getSprite().setPosition(
                    pig.getBody().getPosition().x - pig.getSprite().getWidth() / 2,
                    pig.getBody().getPosition().y - pig.getSprite().getHeight() / 2
                );
                pig.getSprite().setRotation(MathUtils.radiansToDegrees * pig.getBody().getAngle());

                pig.getSprite().draw(game.getBatch());
            }

            if (pig != null && pig.isMarkedForDestruction()) {
                bodiesToDestroy.add(pig.getBody());
                pig.setBody(null);
                pigs.remove(i);
                i--;
            }
        }

        game.getBatch().draw(pauseBtnTexture, pauseBtnBounds.x, pauseBtnBounds.y, pauseBtnBounds.width, pauseBtnBounds.height);
        game.getBatch().end();

        if (joint != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 0, 0, 1);

            Vector2 slingshotOrigin = currentBird.getBody().getPosition();
            shapeRenderer.line(slingshotOrigin.x, slingshotOrigin.y, slingShotStartPoint.x, slingShotStartPoint.y);

            Vector3 mousePosition = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            shapeRenderer.line(slingshotOrigin.x, slingshotOrigin.y, mousePosition.x, mousePosition.y);

            shapeRenderer.end();
        }

        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        slingshot.dispose();
        pauseBtnTexture.dispose();
        for (ParentBlock block : blocks) {
            block.dispose();
        }
        for (ParentPig pig : pigs) {
            pig.dispose();
        }
        for (ParentBird bird : birds) {
            bird.dispose();
        }
    }

    private QueryCallback queryCallback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {
            if (!fixture.testPoint(touchPoint.x, touchPoint.y))
                return true;

            jointDef.bodyB = fixture.getBody();
            jointDef.target.set(touchPoint.x, touchPoint.y);
            joint = (MouseJoint) world.createJoint(jointDef);
            return false;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Use main viewport for UI elements like pause button
        Vector3 uiTouchPoint = new Vector3();
        game.getViewport().unproject(uiTouchPoint.set(screenX, screenY, 0));
        
        // Check pause button with UI coordinates
        if(pauseBtnBounds.contains(uiTouchPoint.x, uiTouchPoint.y)){
            game.setScreen(new PauseScreen(game,this,"level1.png"));
            return true;
        }

        // Use physics camera for game objects
        camera.unproject(touchPoint.set(screenX, screenY, 0));

        float ballRadius = 2f;
        float scaledBallRadius = ballRadius / 2 * SCALE;

        if (touchPoint.dst(new Vector3(initialBallPosition, 0)) < scaledBallRadius) {
            world.QueryAABB(queryCallback, touchPoint.x - 1, touchPoint.y - 1, touchPoint.x + 1, touchPoint.y + 1);
            slingShotStartPoint.set(touchPoint.x, touchPoint.y);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (joint == null) return false;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (joint == null) return false;

        ParentBird currentBird = birds.get(currentBirdIndex);
        currentBird.getBody().setType(BodyDef.BodyType.DynamicBody);

        Vector2 slingshotOrigin = initialBallPosition;
        camera.unproject(touchPoint.set(screenX, screenY, 0));
        Vector2 dragDirection = new Vector2(touchPoint.x - slingshotOrigin.x, touchPoint.y - slingshotOrigin.y);
        if (dragDirection.len() > 0) {
            dragDirection.nor();
            float impulseStrength = dragDirection.len() * 55f * SCALE;
            currentBird.getBody().applyLinearImpulse(dragDirection.scl(-impulseStrength), currentBird.getBody().getWorldCenter(), true);
        }

        world.destroyJoint(joint);
        joint = null;

        if(currentBirdIndex < birds.size()-1) {
            currentBirdIndex++;
            birds.get(currentBirdIndex).getBody().setTransform(155/SCALE,255/SCALE,0);
        }
        else{
            allBirdsShot = true;
        }

        return true;
    }

    private void createWalls(){
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        ChainShape left_shape = new ChainShape();
        Vector2[] left_vert = new Vector2[2];
        left_vert[0] = new Vector2(0, 0);
        left_vert[1] = new Vector2(0, (720-1)/SCALE);
        left_shape.createChain(left_vert);

        ChainShape right_shape = new ChainShape();
        Vector2[] right_vert = new Vector2[2];
        right_vert[0] = new Vector2((1280-1)/SCALE,0);
        right_vert[1] = new Vector2((1280-1)/SCALE, (720-1)/SCALE);
        right_shape.createChain(right_vert);

        body.createFixture(left_shape,1);
        body.createFixture(right_shape,1);

        left_shape.dispose();
        right_shape.dispose();
    }

    public ArrayList<ParentPig> getPigs() {
        return pigs;
    }

    public ArrayList<ParentBlock> getBlocks() {
        return blocks;
    }
}
