package com.example.simplechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Field field = new Field(this /*,new Size(8, 8)*/);
        setContentView(field);
    }
}