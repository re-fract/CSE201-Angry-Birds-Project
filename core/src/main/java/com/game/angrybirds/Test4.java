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

 // public class Test4 {
 //     private Level1_Screen level1Screen;
 //     private World testWorld;

 //     @Before
 //     public void setup() {

 //         Main mockGame = new Main();
 //         level1Screen = new Level1_Screen(mockGame,false);
 //         testWorld = new World(new Vector2(0, -9.81f), true);


 //         ParentBlock block = new WoodBlock(testWorld, 600, 150, 15);
 //         level1Screen.getBlocks().add(block);
 //         level1Screen.getBlocks().get(0).setYpos(100);
 //         level1Screen.getBlocks().get(0).setfallen(false);
 //     }

 //     @Test
 //     public void Falltest() {
 //         ParentBlock block = level1Screen.getBlocks().get(0);
 //         assertEquals(block.isHasFallen(), false);

 //         block.getBody().setTransform(new Vector2(0,0),10f);
 //         assertEquals(level1Screen.getBlocks().get(0).isHasFallen(), true);

 //     }
 // }
