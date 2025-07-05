package com.game.angrybirds.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.game.angrybirds.Main;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            // Fixed size application for better web compatibility
            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1280, 720);
            cfg.padVertical = 0;
            cfg.padHorizontal = 0;
            cfg.antialiasing = true;
            cfg.canvasId = "embed-html";
            return cfg;
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new Main();
        }
}
