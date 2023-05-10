package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.dataBase.FirebaseWriter;
import com.example.simplechess.field.CellCounts;
import com.example.simplechess.field.ScreenSize;
import com.example.simplechess.player.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import org.lwjgl.system.CallbackI;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    private Field field;
    private Player whitePlayer;
    private Player blackPlayer;
    boolean thisIsWhitePlayer = true;
    // Переменная для определения, какой игрок ходит
    boolean isWhitePlayerMove = true;
    private ScreenSize screenSize;
    FirebaseGameManager firebaseGameManager;

    public Game(Context context) {
        super(context);

        new FirebaseWriter(new Users());
        firebaseGameManager = new FirebaseGameManager(this);
        // Получить ячейку по координатам
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
    }

    public Player getPlayer(boolean isWhite) {
        return isWhite ? whitePlayer : blackPlayer;
    }

    public Field getField() {
        return field;
    }

    public void setMove(){
        this.isWhitePlayerMove = !this.isWhitePlayerMove;
    }

    // Пытался создавать объекты в конструкторе Game, но была проблема с Canvas
    // Можно попробовать вернуть, так как по итогу не использую Canvas, хватает Context'a
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        context = getContext();
        screenSize = new ScreenSize(holder.getSurfaceFrame().width(),
                holder.getSurfaceFrame().height());
        CellCounts cellCounts = new CellCounts(8, 8);

        // Создание игроков и поля
        field = new Field(cellCounts, screenSize);
        whitePlayer = new Player(context, true, field.getCell());
        blackPlayer = new Player(context, false, field.getCell());
        // Рандомно выдаётся цвет игроку
        thisIsWhitePlayer = true;

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
                SurfaceHolder holder = getHolder();
                if (holder != null) {
                    gameLoop.join();
                    retry = false;
                }
            } catch (InterruptedException e) {
                gameLoop.stopLoop();// попытаться остановить поток еще раз
            }
        }
    }

    // Отрисовка поля и фигур через игроков
    public void draw(Canvas canvas) {
        if(canvas == null) {
            return;
        }
        super.draw(canvas);

        field.draw(canvas);
        whitePlayer.draw(canvas, field);
        blackPlayer.draw(canvas, field);
    }

    // Метод, который вызывается при нажатии на экран
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            // false != true - ходит черный игрок != это устройство белого игрока
            // true != false - ходит белый игрок != это устройство черного игрока
            // То есть проверяем, ходит ли игрок, которому принадлежит это устройство
            // Если нет то выходим из метода
            if (isWhitePlayerMove != thisIsWhitePlayer) {
                return super.onTouchEvent(event);
            }

            if (thisIsWhitePlayer) {
                whitePlayer.handleClick(this, x,y);
            } else {
                blackPlayer.handleClick(this, x,y);
            }

        }
        return super.onTouchEvent(event);
    }
}
