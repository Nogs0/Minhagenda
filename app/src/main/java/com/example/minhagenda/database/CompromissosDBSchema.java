package com.example.minhagenda.database;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.minhagenda.R;
import com.example.minhagenda.fragments.InputFragment;
import com.example.minhagenda.fragments.OutputFragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class CompromissosDBSchema {
    public static final class CompromissosHc {
        public static final String NAME = "Compromissos";
        public static final class Cols {
            public static final String DATE = "data";
            public static final String HOUR = "hora";
            public static final String DESCRIPTION = "descricao";
        }
    }
}