package com.example.simplechess;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private SurfaceHolder surfaceHolder;
    private Game game;
    private boolean isRunning = false;

    public GameLoop(Game game, SurfaceHolder holder) {
        this.game = game;
        surfaceHolder = holder;

    }
    public void startLoop(){
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        Log.d("start loop", "LOOP IS STARTED");
        super.run();
        Canvas canvas = null;
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // отрисовка объектов на canvas
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void stopLoop() {
        isRunning = false;
        try {
            join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
