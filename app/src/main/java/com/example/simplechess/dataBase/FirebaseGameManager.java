package com.example.simplechess.dataBase;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseGameManager {
    private Position position;
    DatabaseReference mDatabaseRef;

    public interface OnDataReceivedListener {
        void onDataReceived(Position position);
    }

    // Записываем данные в базу данные фигур так, чтобы они не перезаписывались
    // Также в базе данных хранится информация о том, какая это фигура и её позиция
    public void writeData(Figure figure, Position position, boolean isWhite) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("games").child("players").child("figures").child(figure.getIdString()).child("position").setValue(position);
        mDatabaseRef.child("games").child("players").child("figures").child(figure.getIdString()).child("isWhite").setValue(isWhite);
        mDatabaseRef.child("games").child("players").child("figures").child(figure.getIdString()).child("type").setValue(figure.getClass().getSimpleName());
    }

        // Добавляем слушатель событий ValueEventListener к mDatabaseRef
    public void readData(final OnDataReceivedListener listener) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("games").child("players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                position = dataSnapshot.getValue(Position.class);
                listener.onDataReceived(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}

