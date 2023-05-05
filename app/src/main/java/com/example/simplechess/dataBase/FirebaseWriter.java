package com.example.simplechess.dataBase;

import android.util.Log;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseWriter {
    DatabaseReference mDatabaseRef;
    public void writeData(Figure figure, Position position, boolean isWhite) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("position").setValue(position);
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("isWhite").setValue(figure.isWhite());
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("type").setValue(figure.getClass().getSimpleName());
        Log.d("Data Saved", "Data Saved");
    }
}
