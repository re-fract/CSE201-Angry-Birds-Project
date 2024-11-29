package com.game.angrybirds.screens;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.game.angrybirds.Main;
import com.game.angrybirds.block.ParentBlock;
import com.game.angrybirds.block.WoodBlock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class L1_pig_health_RED {
        private Level1_Screen level1Screen;
        private World testWorld;

        @Before
        public void setup() {

            Main mockGame = new Main();
            level1Screen = new Level1_Screen(mockGame);
            testWorld = new World(new Vector2(0, -9.81f), true);

            ParentBlock block = new WoodBlock(testWorld, 600, 150, 15);
            level1Screen.blocks.add(block);
        }

    @Test
    public void testBlockHealthReduction() {
        ParentBlock block = level1Screen.blocks.get(0);
        assertEquals(15, block.getHealth());

        block.takeDamage(4);
        assertEquals(11, block.getHealth());

        block.takeDamage(11);
        assertEquals(0, block.getHealth());
    }

}
