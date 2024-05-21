package com.example.minhagenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class CompromissosDB {
    private SQLiteDatabase database;

    public CompromissosDB(Context context) {
        database = new CompromissosDBHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public String getCompromissosHoje() {

        String[] argsWhere = new String[]{
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        };

        Cursor cursor = queryCompromissos("data = ?", argsWhere);
        StringBuilder stringBuilder = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.DATE));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.HOUR));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.DESCRIPTION));

                if (hora != null && !descricao.isEmpty()) {
                    stringBuilder.append(data).append(" - ").append(hora).append(" - ").append(descricao).append("\n");
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        return stringBuilder.toString();
    }

    public String getCompromissosByDate(String date) {
        String[] argsWhere = new String[]{
                date
        };

        Cursor cursor = queryCompromissos("data = ?", argsWhere);
        StringBuilder stringBuilder = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.DATE));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.HOUR));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosHc.Cols.DESCRIPTION));

                if (hora != null && !descricao.isEmpty()) {
                    stringBuilder.append(data).append(" - ").append(hora).append(" - ").append(descricao).append("\n");
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        return stringBuilder.toString();
    }

    private static ContentValues getValoresConteudo(Compromisso c) {
        ContentValues valores = new ContentValues();

        valores.put(CompromissosDBSchema.CompromissosHc.Cols.DATE, c.getData());
        valores.put(CompromissosDBSchema.CompromissosHc.Cols.HOUR, c.getHora());
        valores.put(CompromissosDBSchema.CompromissosHc.Cols.DESCRIPTION, c.getDescricao());
        return valores;
    }

    public void addCompromisso(Compromisso r) {
        ContentValues valores = getValoresConteudo(r);
        database.insert(CompromissosDBSchema.CompromissosHc.NAME, null, valores);
    }

    public Cursor queryCompromissos(String clausulaWhere, String[] argsWhere) {
        return database.query(CompromissosDBSchema.CompromissosHc.NAME,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
    }
}