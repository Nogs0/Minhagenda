package com.example.minhagenda.models;

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
        //Política de upgrade é simplesmente descartar o conteúdo e começar novamente
        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissosHc.NAME);
        onCreate(db);
    }
}

