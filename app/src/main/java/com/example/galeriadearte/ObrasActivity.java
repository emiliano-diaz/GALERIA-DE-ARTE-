package com.example.galeriadearte;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ObrasActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ViewPager2 viewPager;
    int artistaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        dbHelper = new DBHelper(this);
        viewPager = findViewById(R.id.viewPagerObras);

        Intent intent = getIntent();
        artistaId = intent.getIntExtra("artistaId", -1);
        if (artistaId == -1) {
            finish();
            return;
        }

        List<Obra> obras = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id, titulo, anio, descripcion, precio, duenio, imagen, idArtista FROM Obra WHERE idArtista = ?",
                new String[]{String.valueOf(artistaId)}
        );

        if (cursor.moveToFirst()) {
            do {
                Obra obra = new Obra(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7)
                );
                obras.add(obra);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ObrasPagerAdapter adapter = new ObrasPagerAdapter(this, obras);
        viewPager.setAdapter(adapter);
    }
}
