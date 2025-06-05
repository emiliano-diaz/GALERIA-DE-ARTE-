package com.example.galeriadearte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "GaleriaDeArte.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void insertarArtistaEnGenero(String nombreGenero, String nombreArtista) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Obtener ID del género (si existe)
        Cursor cursor = db.rawQuery("SELECT id FROM Genero WHERE nombre = ?", new String[]{nombreGenero});
        int idGenero = -1;
        if (cursor.moveToFirst()) {
            idGenero = cursor.getInt(0);
        }
        cursor.close();

        // Si el género no existe, crearlo
        if (idGenero == -1) {
            db.execSQL("INSERT INTO Genero (nombre) VALUES (?)", new Object[]{nombreGenero});

            // Obtener el nuevo ID generado
            cursor = db.rawQuery("SELECT id FROM Genero WHERE nombre = ?", new String[]{nombreGenero});
            if (cursor.moveToFirst()) {
                idGenero = cursor.getInt(0);
            }
            cursor.close();
        }

        // Insertar el artista en el género
        if (idGenero != -1) {
            db.execSQL("INSERT INTO Artista (nombre, idGenero) VALUES (?, ?)", new Object[]{nombreArtista, idGenero});
        }

        db.close();
    }




    public void agregarObraAArtista(String nombreArtista, String titulo, int anio, String descripcion, double precio, String duenio, String imagen) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Obtener ID del artista
        Cursor cursor = db.rawQuery("SELECT id FROM Artista WHERE nombre = ?", new String[]{nombreArtista});
        int idArtista = -1;
        if (cursor.moveToFirst()) {
            idArtista = cursor.getInt(0);
        }
        cursor.close();

        // Insertar la obra si el artista existe
        if (idArtista != -1) {
            db.execSQL("INSERT INTO Obra (titulo, anio, descripcion, precio, duenio, imagen, idArtista) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    new Object[]{titulo, anio, descripcion, precio, duenio, imagen, idArtista});
        }

        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla Géneros
        db.execSQL("CREATE TABLE Genero (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL)");

        // Tabla Artistas
        db.execSQL("CREATE TABLE Artista (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "idGenero INTEGER, " +
                "FOREIGN KEY(idGenero) REFERENCES Genero(id))");

        // Tabla Obras
        db.execSQL("CREATE TABLE Obra (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, " +
                "anio INTEGER, " +
                "descripcion TEXT, " +
                "precio REAL, " +
                "duenio TEXT, " +
                "imagen TEXT, " +  // ruta del recurso de imagen
                "idArtista INTEGER, " +
                "FOREIGN KEY(idArtista) REFERENCES Artista(id))");

        // Insertar Géneros
        db.execSQL("INSERT INTO Genero (nombre) VALUES ('Impresionismo')");
        db.execSQL("INSERT INTO Genero (nombre) VALUES ('Cubismo')");
        db.execSQL("INSERT INTO Genero (nombre) VALUES ('Realismo')");

        // Insertar Artistas
        db.execSQL("INSERT INTO Artista (nombre, idGenero) VALUES ('Claude Monet', 1)");
        db.execSQL("INSERT INTO Artista (nombre, idGenero) VALUES ('Pablo Picasso', 2)");
        db.execSQL("INSERT INTO Artista (nombre, idGenero) VALUES ('Jean-François Millet', 3)");

        // Insertar Obras
        db.execSQL("INSERT INTO Obra (titulo, anio, descripcion, precio, duenio, imagen, idArtista) " +
                "VALUES ('Nenúfares', 1906, 'Obra icónica del impresionismo', 12000.50, 'Museo de París', 'nenufares.jpg', 1)");

        db.execSQL("INSERT INTO Obra (titulo, anio, descripcion, precio, duenio, imagen, idArtista) " +
                "VALUES ('Les Demoiselles de Avignon', 1907, 'Pintura revolucionaria del cubismo', 25000.00, 'Colección privada', 'demoiselles.jpg', 2)");

        db.execSQL("INSERT INTO Obra (titulo, anio, descripcion, precio, duenio, imagen, idArtista) " +
                "VALUES ('El Ángelus', 1859, 'Escena campesina realista', 8000.75, 'Museo del Louvre', 'angelus.jpg', 3)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Por ahora, simplemente borramos todo y volvemos a crear
        db.execSQL("DROP TABLE IF EXISTS Obra");
        db.execSQL("DROP TABLE IF EXISTS Artista");
        db.execSQL("DROP TABLE IF EXISTS Genero");
        onCreate(db);
    }


}
