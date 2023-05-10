package com.example.simplechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.simplechess.login.Login;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button2 = new Button(this);
        Button button1 = new Button(this);
        button1.setText("Назад");
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            finish();
            startActivity(intent);

        });
        button2.setText("Играть");
        button2.setOnClickListener(v -> {
            game = new Game(this);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(button1);
            linearLayout.addView(game);

            setContentView(linearLayout);
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(button2);

        setContentView(linearLayout);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DESTROY", "DESTROY");
    }
}
