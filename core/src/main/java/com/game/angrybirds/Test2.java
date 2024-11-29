// package com.game.angrybirds;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.World;
// import com.game.angrybirds.Main;
// import com.game.angrybirds.block.ParentBlock;
// import com.game.angrybirds.block.WoodBlock;
// import com.game.angrybirds.pig.NormalPig;
// import com.game.angrybirds.pig.ParentPig;
// import com.game.angrybirds.screens.Level1_Screen;
// import org.junit.Before;
// import org.junit.Test;

// import static org.junit.Assert.assertEquals;

// public class Test2 {
//     private Level1_Screen level1Screen;
//     private World testWorld;

//     @Before
//     public void setup() {

//         Main mockGame = new Main();
//         level1Screen = new Level1_Screen(mockGame,false);
//         testWorld = new World(new Vector2(0, -9.81f), true);


//         ParentPig pig = new NormalPig(testWorld, 500, 200, 10, 2.5f);
//         level1Screen.getPigs().add(pig);
//         ParentBlock block = new WoodBlock(testWorld, 600, 150, 15);
//         level1Screen.getBlocks().add(block);
//     }
//     @Test
//     public void testContactListenerDamageApplication() {
//         ParentPig pig = level1Screen.getPigs().get(0);
//         assertEquals(10, pig.getHealth());
//         int damage = level1Screen.getDamageBasedOnSpeed(26);
//         pig.takeDamage(damage);
//         assertEquals(7, pig.getHealth());

//         ParentBlock block = level1Screen.getBlocks().get(0);
//         assertEquals(15, block.getHealth());
//         int damage2 = level1Screen.getDamageBasedOnSpeed(24);
//         block.takeDamage(damage2);
//         assertEquals(13, block.getHealth());
//     }
// }
