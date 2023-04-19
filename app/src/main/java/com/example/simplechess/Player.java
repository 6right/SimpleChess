package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;

public class Player {
    private Pawn[] pawns = new Pawn[8];
    private Rook[] rooks = new Rook[2];
    private Knight[] knights = new Knight[2];
    private Bishop[] bishops = new Bishop[2];
    private Queen queen;
    private King king;

    public Player(Context context, boolean isWhite, Cell cell) {
        for (int i = 0; i < 8; i++) {
            pawns[i] = new Pawn(
                    context,
                    (isWhite ? new Position(i, 1) : new Position(i, 6)),
                    isWhite,
                    cell
            );
        }
        for (int i = 0; i < 2; i++) {
            rooks[i] = new Rook(
                    context,
                    (isWhite ? new Position(i * 7, 0) : new Position(i * 7, 7)),
                    isWhite,
                    cell
            );
            knights[i] = new Knight(
                    context,
                    (isWhite ? new Position(i * 5 + 1, 0) : new Position(i * 5 + 1, 7)),
                    isWhite,
                    cell
            );
            bishops[i] = new Bishop(
                    context,
                    (isWhite ? new Position(i * 3 + 2, 0) : new Position(i * 3 + 2, 7)),
                    isWhite,
                    cell
            );
        }
        queen = new Queen(
                context,
                (isWhite ? new Position(3, 0) : new Position(3, 7)),
                isWhite,
                cell
        );

        king = new King(
                context,
                (isWhite ? new Position(4, 0) : new Position(4, 7)),
                isWhite,
                cell
        );
    }

    protected void draw(Canvas canvas) {
        for (int i = 0; i < 8; i++) {
            pawns[i].draw(canvas);
        }
        for (int i = 0; i < 2; i++) {
            rooks[i].draw(canvas);
            knights[i].draw(canvas);
            bishops[i].draw(canvas);
        }
        king.draw(canvas);
        queen.draw(canvas);
    }
}
