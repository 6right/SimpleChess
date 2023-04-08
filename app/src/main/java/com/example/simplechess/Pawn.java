package com.example.simplechess;

public class Pawn extends Figure {
    public Pawn(Position position) {
        this.position = position;
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
