package com.example.mychess.chess;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
/*
 * Main activity is the entry point to our application.
 * */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanseState) {
        super.onCreate(savedInstanseState);

        // Set window to fullscreen (will hide status bar)
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Set content view to game, so that objects in the Game class can be rendered to the screen.
        setContentView(new Game(this));

    }


}
