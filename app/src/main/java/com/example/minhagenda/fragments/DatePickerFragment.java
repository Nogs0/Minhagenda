package com.example.minhagenda.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.minhagenda.activities.OnInputDatePickerListener;
import com.example.minhagenda.activities.OnOutputDatePickerListener;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    final Calendar c = Calendar.getInstance();
    private Integer ano = c.get(Calendar.YEAR);
    private Integer mes = c.get(Calendar.MONTH);
    private Integer dia = c.get(Calendar.DAY_OF_MONTH);

    private boolean input = false;
    private OnInputDatePickerListener inputListener;
    private OnOutputDatePickerListener outputListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnInputDatePickerListener)
            inputListener = (OnInputDatePickerListener) context;
        if(context instanceof OnOutputDatePickerListener)
            outputListener = (OnOutputDatePickerListener) context;
        else
            throw new ClassCastException();
    }

    public DatePickerFragment(Context context, boolean input) {
        Activity act = (Activity) context;
        this.input = input;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(requireContext(), this, ano, mes, dia);
    }

    @Override
    public void onDateSet(DatePicker view, int ano, int mes, int dia) {
        if (this.inputListener != null && this.input)
            this.inputListener.onInputDateSet(ano, mes, dia);
        if (this.outputListener != null && !this.input)
            this.outputListener.onOutputDateSet(ano, mes, dia);
    }
}
