package com.example.minhagenda.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    final Calendar c = Calendar.getInstance();
    private View view;
    private EditText editText;
    private Integer hora = c.get(Calendar.HOUR_OF_DAY);
    private Integer minuto = c.get(Calendar.MINUTE);

    public TimePickerFragment(Context context, int editTextId) {
        Activity act = (Activity) context;

        this.editText = (EditText) act.findViewById(editTextId);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, this.hora, this.minuto, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;

        updateDisplay();
    }

    private void updateDisplay() {
        String horaString = this.hora < 10 ? "0" + this.hora : this.hora.toString();
        String minutoString = this.minuto < 10 ? "0" + this.minuto : this.minuto.toString();
        this.editText.setText(horaString + ":" + minutoString);
    }
}
