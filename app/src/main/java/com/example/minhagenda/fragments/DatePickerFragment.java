package com.example.minhagenda.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    final Calendar c = Calendar.getInstance();
    private Integer ano = c.get(Calendar.YEAR);
    private Integer mes = c.get(Calendar.MONTH);
    private Integer dia = c.get(Calendar.DAY_OF_MONTH);
    private EditText editText;

    public DatePickerFragment(Context context) {
        Activity act = (Activity) context;
    }

    public DatePickerFragment(Context context, int editTextId) {
        Activity act = (Activity) context;
        editText = (EditText) act.findViewById(editTextId);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(requireContext(), this, ano, mes, dia);
    }

    @Override
    public void onDateSet(DatePicker view, int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes + 1;
        this.dia = dia;

        if (editText != null)
            updateDisplay();
    }

    private void updateDisplay() {
        String diaString = this.dia < 10 ? "0" + this.dia : this.dia.toString();
        String mesString = this.mes < 10 ? "0" + this.mes : this.mes.toString();

        editText.setText(diaString + "/" + mesString + "/" + this.ano);
    }
}
