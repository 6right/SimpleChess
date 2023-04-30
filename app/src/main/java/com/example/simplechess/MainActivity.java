package com.example.simplechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.simplechess.login.Login;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this);
        Button button = new Button(this);
        button.setText("Назад");
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            finish();
            startActivity(intent);

        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(button);
        linearLayout.addView(game);

        setContentView(linearLayout);
    }
}