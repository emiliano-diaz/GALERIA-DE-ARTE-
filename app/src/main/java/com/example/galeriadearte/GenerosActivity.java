package com.example.galeriadearte;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GenerosActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView listViewGeneros;
    ArrayList<String> nombresGeneros;
    ArrayList<Integer> idsGeneros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generos);

        dbHelper = new DBHelper(this);

        /*
        //--------------------agregar-------------------//
        dbHelper.insertarArtistaEnGenero("Cubismo", "Juan Gris");

        dbHelper.agregarObraAArtista("Juan Gris", "Retrato de Pablo Picasso", 1912, "Homenaje cubista a su colega Picasso.", 22000.0, "Colección privada", "retrato_picasso.jpg");
        dbHelper.agregarObraAArtista("Juan Gris", "Naturaleza muerta con guitarra", 1925, "Composición cubista con formas geométricas y colores vibrantes.", 18000.0, "Museo de Arte Moderno", "naturaleza_guitarra.jpg");



        //----------------------------------------------//

         */

        listViewGeneros = findViewById(R.id.listViewGeneros);

        nombresGeneros = new ArrayList<>();
        idsGeneros = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nombre FROM Genero", null);
        while (cursor.moveToNext()) {
            idsGeneros.add(cursor.getInt(0));
            nombresGeneros.add(cursor.getString(1));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombresGeneros
        );
        listViewGeneros.setAdapter(adapter);

        listViewGeneros.setOnItemClickListener((adapterView, view, position, id) -> {
            int generoId = idsGeneros.get(position);
            String nombreGenero = nombresGeneros.get(position);

            Intent intent = new Intent(this, ArtistasActivity.class);
            intent.putExtra("generoId", generoId);
            intent.putExtra("generoNombre", nombreGenero);
            startActivity(intent);

        });
    }
}

