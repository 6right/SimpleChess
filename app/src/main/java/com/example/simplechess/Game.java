package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    private Field field;
    private Player whitePlayer;
    private Player blackPlayer;
    private ScreenSize screenSize;

    private boolean shouldDrawBishop = false;

    public Game(Context context) {
        super(context);

        // Получить ячейку по координатам
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        context = getContext();
        screenSize = new ScreenSize(holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());
        CellCounts cellCounts = new CellCounts(8, 8);

        field = new Field(cellCounts, screenSize);
        whitePlayer = new Player(context, true);
        blackPlayer = new Player(context, false);
        gameLoop = new GameLoop(this, holder);

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
        whitePlayer.draw(canvas);
        blackPlayer.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            shouldDrawBishop = !shouldDrawBishop;
        }
        return super.onTouchEvent(event);
    }
}
