package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Field field;
    private Bishop bishop;
    private boolean shouldDrawBishop = false;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        field = new Field(context, new Size(8,8));
        bishop = new Bishop(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // Перезапуск игры (нити)
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                gameLoop.join();
                retry = false;
            } catch (InterruptedException e) {
                gameLoop.startLoop();// попытаться остановить поток еще раз
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        field.draw(canvas);

        if (shouldDrawBishop) {
            bishop.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            shouldDrawBishop = !shouldDrawBishop;
        }
        return super.onTouchEvent(event);
    }
}
