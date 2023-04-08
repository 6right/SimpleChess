package com.example.simplechess;

public class Bishop extends Figure {
    public Bishop(Position position) {
        this.position = position;
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
