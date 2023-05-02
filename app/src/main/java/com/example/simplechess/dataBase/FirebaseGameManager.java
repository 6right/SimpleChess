package com.example.simplechess.dataBase;

import static android.content.ContentValues.TAG;

import android.util.Log;

// Импорт Context

import androidx.annotation.NonNull;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Импорт фигур

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class FirebaseGameManager {

    DatabaseReference mDatabaseRef;
    ArrayList<FirebaseFigure> databasePositions = new ArrayList<>();
    Semaphore semaphore = new Semaphore(0);
    // Записываем данные в базу данные фигур так, чтобы они не перезаписывались
    // Также в базе данных хранится информация о том, какая это фигура и её позиция
    public void writeData(Figure figure, Position position, boolean isWhite) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("position").setValue(position);
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("isWhite").setValue(isWhite);
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("type").setValue(figure.getClass().getSimpleName());
        Log.d("Data Saved", "Data Saved");
    }

   // Считываем данные из базы данных
    public void readData(){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("games").child("players").child("false").child("figures").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot figureSnapshot : snapshot.getChildren()) {
                    Position position = figureSnapshot.child("position").getValue(Position.class);
                    boolean isWhite = figureSnapshot.child("isWhite").getValue(Boolean.class);
                    int id = Integer.parseInt(figureSnapshot.getKey());
                    databasePositions.add(new FirebaseFigure(id, position, isWhite));
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public ArrayList<FirebaseFigure> getDatabasePositions() {
        return databasePositions;
    }
}

