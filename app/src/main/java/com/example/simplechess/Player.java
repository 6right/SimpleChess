package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;

public class Player {
    protected Pawn[] pawns = new Pawn[8];
    protected Rook[] rooks = new Rook[2];
    protected Knight[] knights = new Knight[2];
    protected Bishop[] bishops = new Bishop[2];
    protected Queen queen;

    public Player(Context context, boolean isWhite) {
        for (int i = 0; i < 8; i++) {
            pawns[i] = new Pawn(
                    context,
                    (isWhite ? new Position(i, 1) : new Position(i, 6)),
                    isWhite
            );
        }
        for (int i = 0; i < 2; i++) {
            rooks[i] = new Rook(
                    context,
                    (isWhite ? new Position(i * 7, 0) : new Position(i * 7, 7)),
                    isWhite
            );
            knights[i] = new Knight(
                    context,
                    (isWhite ? new Position(i * 5 + 1, 0) : new Position(i * 5 + 1, 7)),
                    isWhite
            );
            bishops[i] = new Bishop(
                    context,
                    (isWhite ? new Position(i * 3 + 2, 0) : new Position(i * 3 + 2, 7)),
                    isWhite
            );
        }
        queen = new Queen(
                context,
                (isWhite ? new Position(3, 0) : new Position(3, 7)),
                isWhite
        );
    }

    protected void draw(Canvas canvas) {
        for (int i = 0; i < 8; i++) {
            pawns[i].draw(canvas);
        }
//        for (int i = 0; i < 2; i++) {
//            rooks[i].draw(canvas);
//            knights[i].draw(canvas);
//            bishops[i].draw(canvas);
//        }
//        queen.draw(canvas);
    }
}
