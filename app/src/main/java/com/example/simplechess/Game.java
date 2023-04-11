package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private Thread gameThread; // поток игры
    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated( SurfaceHolder surfaceHolder) {
        // начать поток игры при создании поверхности
        gameThread = new Thread(new GameLoopRunnable(surfaceHolder));
        gameThread.start();

    }

    @Override
    public void surfaceChanged( SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed( SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // попытаться остановить поток еще раз
            }
        }

    }

    private class GameLoopRunnable implements Runnable {
        private SurfaceHolder surfaceHolder;

        public GameLoopRunnable(SurfaceHolder holder) {
            surfaceHolder = holder;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        // отрисовка объектов на canvas
                        draw(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bishop bishop = new Bishop();
        bishop.draw(canvas);

        ChessField chessField = new ChessField();
        chessField.draw(canvas);
    }
}
