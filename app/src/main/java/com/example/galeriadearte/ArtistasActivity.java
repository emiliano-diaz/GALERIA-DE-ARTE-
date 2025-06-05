package com.example.galeriadearte; // Usá aquí tu paquete real

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ArtistasActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView listViewArtistas;
    ArrayList<String> nombresArtistas;
    ArrayList<Integer> idsArtistas;
    int generoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistas);

        dbHelper = new DBHelper(this);
        listViewArtistas = findViewById(R.id.listViewArtistas);
        nombresArtistas = new ArrayList<>();
        idsArtistas = new ArrayList<>();

        Intent intent = getIntent();
        generoId = intent.getIntExtra("generoId", -1);
        if (generoId == -1) {
            finish();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Acá cambió "genero_id" por "idGenero" para que coincida con la tabla criada
        Cursor cursor = db.rawQuery(
                "SELECT id, nombre FROM Artista WHERE idGenero = ?",
                new String[]{String.valueOf(generoId)}
        );

        while (cursor.moveToNext()) {
            idsArtistas.add(cursor.getInt(0));
            nombresArtistas.add(cursor.getString(1));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombresArtistas
        );
        listViewArtistas.setAdapter(adapter);

        listViewArtistas.setOnItemClickListener((adapterView, view, position, id) -> {
            // Obtenemos el ID del artista seleccionado según su posición
            int artistaId = idsArtistas.get(position);
            String nombreArtista = nombresArtistas.get(position);
            // Creamos un Intent para abrir ObrasActivity
            Intent intentObras = new Intent(this, ObrasActivity.class);
            intentObras.putExtra("artistaId", artistaId);
            intentObras.putExtra("artistaNombre", nombreArtista);

            startActivity(intentObras);
        });
    }
}
