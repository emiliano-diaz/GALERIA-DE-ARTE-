package com.example.galeriadearte;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ObrasActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listViewObras;
    ArrayList<String> nombresObras;
    int artistaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        dbHelper = new DBHelper(this);
        listViewObras = findViewById(R.id.listViewObras);
        nombresObras = new ArrayList<>();

        Intent intent = getIntent();
        artistaId = intent.getIntExtra("artistaId", -1);

        if (artistaId == -1) {
            finish(); // sin ID → cerrar
            return;
        }

        List<Obra> obras = new ArrayList<Obra>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id, titulo, anio, descripcion, precio, duenio, imagen, idArtista FROM Obra WHERE idArtista = ?",
                new String[]{String.valueOf(artistaId)}
        );


        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                int anio = cursor.getInt(cursor.getColumnIndexOrThrow("anio"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));
                String duenio = cursor.getString(cursor.getColumnIndexOrThrow("duenio"));
                String imagen = cursor.getString(cursor.getColumnIndexOrThrow("imagen"));
                int idArtistaObra = cursor.getInt(cursor.getColumnIndexOrThrow("idArtista"));

                Obra obra = new Obra(id, titulo, anio, descripcion, precio, duenio, imagen, idArtistaObra);
                obras.add(obra);
            } while (cursor.moveToNext());
        }
        cursor.close();



        ListView listView = findViewById(R.id.listViewObras);
        ObraAdapter adapter = new ObraAdapter(this, obras);
        listView.setAdapter(adapter);
    }
}
