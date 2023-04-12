package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Field field;
    private Bishop bishop;
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
//         начать поток игры при создании поверхности
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//         Перезапуск игры (нити)

    }

    @Override
    public void surfaceDestroyed( SurfaceHolder surfaceHolder) {
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

        bishop.draw(canvas);
    }
}
