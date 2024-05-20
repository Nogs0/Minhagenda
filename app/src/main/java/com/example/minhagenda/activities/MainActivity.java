package com.example.minhagenda.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.minhagenda.R;
import com.example.minhagenda.fragments.InputFragment;
import com.example.minhagenda.fragments.OutputFragment;
import com.example.minhagenda.models.CompromissosDB;

public class MainActivity extends AppCompatActivity {


    private CompromissosDB compromissoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compromissoDB = new CompromissosDB(this);

    }
}