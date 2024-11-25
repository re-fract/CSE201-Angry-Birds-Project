package com.game.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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
import com.game.angrybirds.block.ParentBlock;
import com.game.angrybirds.block.WoodBlock;
import com.game.angrybirds.pig.*;

import java.util.ArrayList;

public class Level2_Screen extends InputAdapter implements Screen {
    private final Main game;
    private Texture background;
    private ArrayList<ParentPig> pigs;
    private ArrayList<ParentBlock> blocks;
    private RedBird redBird;
    private Texture slingshot;
    private Texture pauseBtnTexture;
    private World world;
    private OrthographicCamera camera;

    private Vector2 slingShotStartPoint;
    private ShapeRenderer shapeRenderer;

    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private final float SCALE = 10f;

    private MouseJoint joint;
    private MouseJointDef jointDef;

    // Rectangle bounds for detecting button clicks
    private Rectangle pauseBtnBounds;
    private Vector3 touchPoint;

    private Box2DDebugRenderer debugRenderer;

    public Level2_Screen(Main game) {
        this.game = game;

        // Load textures
        background = new Texture("level1.png");
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();

        pauseBtnTexture = new Texture("pause.png");
        slingshot = new Texture("slingshot.png");

        slingShotStartPoint = new Vector2();
        shapeRenderer = new ShapeRenderer();
        touchPoint = new Vector3();
        pauseBtnBounds = new Rectangle(1220/SCALE, 660/SCALE, 50/SCALE, 50/SCALE);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        world = new World(new Vector2(0, -9.81f), true);
        camera = game.getCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / SCALE, Gdx.graphics.getHeight() / SCALE);
        camera.update();
        debugRenderer = new Box2DDebugRenderer();

        createWalls();

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);

        EdgeShape groundShape = new EdgeShape();
        groundShape.set(0, 10, 128, 10);
        groundBody.createFixture(groundShape, 0);
        groundShape.dispose();

        redBird = new RedBird(world, 90, 115);

        pigs.add(new NormalPig(world, 950, 235,5,3.2f));
        pigs.add(new CrownPig(world, 950, 144,3,2.5f));

        blocks.add(new WoodBlock(world, 800, 105));
        blocks.add(new WoodBlock(world, 900, 105));
        blocks.add(new WoodBlock(world, 1000, 105));
        blocks.add(new WoodBlock(world, 850, 144));
        blocks.add(new WoodBlock(world, 1020, 144));
        blocks.add(new WoodBlock(world, 900, 190));
        blocks.add(new WoodBlock(world, 1000, 190));

        jointDef = new MouseJointDef();
        jointDef.bodyA = groundBody;
        jointDef.collideConnected = true;
        jointDef.maxForce = 1000.0f * redBird.getBody().getMass();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                // Detect when the bird collides with a pig or block
                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();

                if (bodyA == redBird.getBody() || bodyB == redBird.getBody()) {
                    Body collidedBody = (bodyA == redBird.getBody()) ? bodyB : bodyA;
                    if (collidedBody.getUserData() instanceof ParentPig) {
                        // Calculate damage based on bird speed
                        float speed = redBird.getBody().getLinearVelocity().len();
                        int damage = getDamageBasedOnSpeed(speed);
                        ParentPig pig = (ParentPig) collidedBody.getUserData();
                        pig.takeDamage(damage);

                        System.out.println("Pig took " + damage + " damage. Health left: " + pig.getHealth());
                    } else if (collidedBody.getUserData() instanceof ParentBlock) {
                        // Calculate damage based on bird speed
                        float speed = redBird.getBody().getLinearVelocity().len();
                        int damage = getDamageBasedOnSpeed(speed);
                        ParentBlock block = (ParentBlock) collidedBody.getUserData();
                        block.takeDamage(damage);

                        // Log the health of the block after taking damage
                        System.out.println("Block took " + damage + " damage. Health left: " + block.getHealth());
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
    }

    private int getDamageBasedOnSpeed(float speed) {
        if (speed/SCALE > 25) {
            return 3;
        } else if (speed/SCALE > 15) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        debugRenderer.render(world, camera.combined);

        game.getBatch().begin();
//        game.getBatch().draw(background,0,0,1280/SCALE,720/SCALE);
//        game.getBatch().draw(slingshot, 100, 100, 100, 200);

        game.getBatch().setProjectionMatrix(camera.combined);

        redBird.getSprite().setPosition(
            redBird.getBody().getPosition().x - redBird.getSprite().getWidth() / 2,
            redBird.getBody().getPosition().y - redBird.getSprite().getHeight() / 2
        );
        redBird.getSprite().setRotation(MathUtils.radiansToDegrees * redBird.getBody().getAngle());

        redBird.getSprite().draw(game.getBatch());

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
                world.destroyBody(block.getBody());
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

            if(pig != null && pig.isMarkedForDestruction()){
                world.destroyBody(pig.getBody());
                pig.setBody(null);
                pigs.remove(i);
                i--;
            }
        }

        game.getBatch().draw(pauseBtnTexture, pauseBtnBounds.x, pauseBtnBounds.y, pauseBtnBounds.width, pauseBtnBounds.height);
        game.getBatch().end();

        if (joint != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 0, 0, 1);
            Vector2 slingshotOrigin = redBird.getBody().getPosition();
            Vector3 mousePosition = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            shapeRenderer.line(slingshotOrigin.x, slingshotOrigin.y, mousePosition.x, mousePosition.y);
            shapeRenderer.end();
        }
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
        background.dispose();
        slingshot.dispose();
        pauseBtnTexture.dispose();
        redBird.dispose();
        for (ParentBlock block : blocks) {
            block.dispose();
        }
        for (ParentPig pig : pigs) {
            pig.dispose();
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
        camera.unproject(touchPoint.set(screenX, screenY, 0));
        world.QueryAABB(queryCallback, touchPoint.x - 1, touchPoint.y - 1, touchPoint.x + 1, touchPoint.y + 1);

        if(joint!=null){
            slingShotStartPoint.set(touchPoint.x, touchPoint.y);
        }

        if(pauseBtnBounds.contains(touchPoint.x, touchPoint.y)){
            new PauseScreen(game,this);
            return true;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (joint == null) return false;

        camera.unproject(touchPoint.set(screenX, screenY, 0));
        slingShotStartPoint.set(touchPoint.x, touchPoint.y);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (joint == null) return false;

        Vector2 slingshotOrigin = redBird.getBody().getPosition();
        camera.unproject(touchPoint.set(screenX, screenY, 0));
        Vector2 dragDirection = new Vector2(touchPoint.x - slingshotOrigin.x, touchPoint.y - slingshotOrigin.y);

        if (dragDirection.len() > 0) {
            dragDirection.nor();
            float impulseStrength = dragDirection.len() * 60f * SCALE;
            redBird.getBody().applyLinearImpulse(dragDirection.scl(-impulseStrength), redBird.getBody().getWorldCenter(), true);
        }

        world.destroyJoint(joint);
        joint = null;

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
        left_vert[1] = new Vector2(0, (Gdx.graphics.getHeight()-1)/SCALE);
        left_shape.createChain(left_vert);

        ChainShape right_shape = new ChainShape();
        Vector2[] right_vert = new Vector2[2];
        right_vert[0] = new Vector2((Gdx.graphics.getWidth()-1)/SCALE,0);
        right_vert[1] = new Vector2((Gdx.graphics.getWidth()-1)/SCALE, (Gdx.graphics.getHeight()-1)/SCALE);
        right_shape.createChain(right_vert);

        body.createFixture(left_shape,1);
        body.createFixture(right_shape,1);

        left_shape.dispose();
        right_shape.dispose();
    }
}



