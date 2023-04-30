package com.example.simplechess.dataBase;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Импорт Context
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Импорт фигур
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;

import java.util.concurrent.ConcurrentHashMap;


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
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("position").setValue(position);
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("isWhite").setValue(isWhite);
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").child(figure.getIdString()).child("type").setValue(figure.getClass().getSimpleName());
    }

   // Считываем данные из базы данных
    public ConcurrentHashMap<Position, Figure> readData(boolean isWhite, Context context, Cell cell){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
        mDatabaseRef.child("games").child("players").child(isWhite ? "true" : "false").child("figures").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot figureSnapshot : snapshot.getChildren()) {
                    String type = figureSnapshot.child("type").getValue(String.class);
                    Position position = figureSnapshot.child("position").getValue(Position.class);
                    boolean isWhite = figureSnapshot.child("isWhite").getValue(Boolean.class);
                    int id = Integer.parseInt(figureSnapshot.getKey());
                    switch (type) {
                        case "Pawn":
                            figureMap.put(
                                    position,
                                    new Pawn(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                        case "Rook":
                            figureMap.put(
                                    position,
                                    new Rook(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                        case "Knight":
                            figureMap.put(
                                    position,
                                    new Knight(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                        case "Bishop":
                            figureMap.put(
                                    position,
                                    new Bishop(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                        case "Queen":
                            figureMap.put(
                                    position,
                                    new Queen(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                        case "King":
                            figureMap.put(
                                    position,
                                    new King(context, id, position, isWhite, cell.getHeight(), cell.getWidth())
                            );
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
        return figureMap;
    }
}

