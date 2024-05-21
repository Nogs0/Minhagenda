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

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    final Calendar c = Calendar.getInstance();
    private Integer hora = c.get(Calendar.HOUR_OF_DAY);
    private Integer minuto = c.get(Calendar.MINUTE);

    private TimePickerDialog.OnTimeSetListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TimePickerDialog.OnTimeSetListener)
            listener = (TimePickerDialog.OnTimeSetListener) context;
        else
            throw new ClassCastException();
    }

    public TimePickerFragment(Context context) {
        Activity act = (Activity) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, this.hora, this.minuto, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hora, int minuto) {
        listener.onTimeSet(view, hora, minuto);
    }

}
