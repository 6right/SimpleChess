package com.example.simplechess.dataBase;

import android.util.Log;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseWriter {
    DatabaseReference mDatabaseRef;

    public void writeDataFromTo(Position from, Position to) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").child("from").setValue(from);
        mDatabaseRef.child("users").child("to").setValue(to);
    }

    public void writeDataFrom(Position from) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").child("from").setValue(from);
    }

    public void writeDataTo(Position clickedPosition) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").child("to").setValue(clickedPosition);
    }

    public void removeData() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").child("from").removeValue();
        mDatabaseRef.child("users").child("to").removeValue();
    }
}
