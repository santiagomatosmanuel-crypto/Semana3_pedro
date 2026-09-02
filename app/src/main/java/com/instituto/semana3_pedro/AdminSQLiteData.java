package com.instituto.semana3_pedro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class AdminSQLiteData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "municipalidad_amarilis.db";
    private static final int DATABASE_VERSION = 1;

    public AdminSQLiteData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla Áreas
        db.execSQL("CREATE TABLE areas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_area TEXT NOT NULL)");

        // Insertar área por defecto
        db.execSQL("INSERT INTO areas (nombre_area) VALUES ('Atención Ciudadana')");

        // Tabla Ciudadanos
        db.execSQL("CREATE TABLE ciudadanos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni TEXT UNIQUE NOT NULL, " +
                "nombres TEXT NOT NULL, " +
                "apellidos TEXT NOT NULL, " +
                "telefono TEXT)");

        // Tabla Consultas
        db.execSQL("CREATE TABLE consultas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "codigo_ticket TEXT UNIQUE NOT NULL, " +
                "ciudadano_id INTEGER NOT NULL, " +
                "area_id INTEGER NOT NULL, " +
                "asunto TEXT NOT NULL, " +
                "descripcion TEXT NOT NULL, " +
                "estado TEXT DEFAULT 'Pendiente', " +
                "fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(ciudadano_id) REFERENCES ciudadanos(id), " +
                "FOREIGN KEY(area_id) REFERENCES areas(id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS consultas");
        db.execSQL("DROP TABLE IF EXISTS ciudadanos");
        db.execSQL("DROP TABLE IF EXISTS areas");
        onCreate(db);
    }
}