package com.example.minhagenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissosDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "compromissosDB";
    private static final int DATABASE_VERSION = 1;

    public CompromissosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissosHc.NAME);
        db.execSQL("CREATE TABLE " + CompromissosDBSchema.CompromissosHc.NAME + " (" +
                "_id integer PRIMARY KEY autoincrement, "
                + CompromissosDBSchema.CompromissosHc.Cols.DATE + ", "
                + CompromissosDBSchema.CompromissosHc.Cols.HOUR + ", "
                + CompromissosDBSchema.CompromissosHc.Cols.DESCRIPTION
                +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        onCreate(db);
    }
}

