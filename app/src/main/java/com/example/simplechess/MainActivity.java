package com.example.simplechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChessBoardView chessBoardView = new ChessBoardView(this);
        setContentView(chessBoardView);
    }
}