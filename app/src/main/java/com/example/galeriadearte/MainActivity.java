package com.example.galeriadearte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnInfo, btnGeneros, btnOfertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInfo = findViewById(R.id.btnInfo);
        btnGeneros = findViewById(R.id.btnGeneros);
        btnOfertas = findViewById(R.id.btnOfertas);

        btnInfo.setOnClickListener(view -> {
            // abrir pantalla de info
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });

        btnGeneros.setOnClickListener(view -> {
            // abrir pantalla de géneros
            Intent intent = new Intent(MainActivity.this, GenerosActivity.class);
            startActivity(intent);
        });

        btnOfertas.setOnClickListener(view -> {
            // abrir pantalla de ofertas
            Intent intent = new Intent(MainActivity.this, OfertasActivity.class);
            startActivity(intent);
        });
    }
}
