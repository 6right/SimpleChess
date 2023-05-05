package com.example.simplechess.dataBase;

import android.util.Log;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseWriter {
    DatabaseReference mDatabaseRef;
    public void writeDataFrom(Position from, Position to) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").child("from").setValue(from);
        mDatabaseRef.child("users").child("to").setValue(to);
    }

//        Figure figure, Position position, boolean isWhite
//        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child("position").setValue(position);
//        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child("isWhite").setValue(figure.isWhite());
//        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child("type").setValue(figure.getClass().getSimpleName());

}
