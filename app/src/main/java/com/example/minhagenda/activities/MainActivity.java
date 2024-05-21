package com.example.minhagenda.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Output;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhagenda.R;
import com.example.minhagenda.fragments.InputFragment;
import com.example.minhagenda.fragments.OutputFragment;

public class MainActivity extends AppCompatActivity implements OnInputDatePickerListener, TimePickerDialog.OnTimeSetListener, OnOutputDatePickerListener {

    private InputFragment inputFragment;
    private OutputFragment outputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFragment = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.input_fragment);
        outputFragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.output_fragment);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        inputFragment.onTimeSet(view, hourOfDay, minute);
    }

    @Override
    public void onInputDateSet(int year, int month, int dayOfMonth) {
        inputFragment.onInputDateSet(year, month + 1, dayOfMonth);
    }

    @Override
    public void onOutputDateSet(int year, int month, int dayOfMonth) {
        outputFragment.onOutputDateSet(year, month + 1, dayOfMonth);
    }

    @Override
    public void onOutputDateSet(String date) {
        outputFragment.onOutputDateSet(date);
    }
}