package com.example.minhagenda.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.minhagenda.R;
import com.example.minhagenda.activities.OnInputDatePickerListener;
import com.example.minhagenda.activities.OnOutputDatePickerListener;
import com.example.minhagenda.database.Compromisso;
import com.example.minhagenda.database.CompromissosDB;

public class InputFragment extends Fragment implements OnInputDatePickerListener, TimePickerDialog.OnTimeSetListener {

    private View view;
    private EditText inputDescription;
    private EditText inputDate;
    private DatePickerFragment datePickerFragment;
    private EditText inputTime;
    private TimePickerFragment timePickerFragment;
    private Button btnOk;
    private CompromissosDB compromissoDB;
    private OnOutputDatePickerListener outputListener;

    @Override
    public void onResume() {
        super.onResume();
        if (compromissoDB == null)
            compromissoDB = new CompromissosDB(getActivity().getBaseContext());

        if (outputListener == null)
            outputListener = (OnOutputDatePickerListener) getContext();

        inputDescription = (EditText) this.view.findViewById(R.id.inputDescription);
        inputDate = (EditText) this.view.findViewById(R.id.inputDate);
        datePickerFragment = new DatePickerFragment(getContext(), true);

        // On Click Event
        inputDate.setOnClickListener(
                (view) -> datePickerFragment.show(this.getParentFragmentManager(), "datePicker"));

        inputTime = (EditText) this.view.findViewById(R.id.inputTime);
        timePickerFragment = new TimePickerFragment(getContext());
        // On Click Event
        inputTime.setOnClickListener((view) ->
                timePickerFragment.show(this.getParentFragmentManager(), "timePicker"));

        btnOk = (Button) this.view.findViewById(R.id.btnOk);
        // On Click Event
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DATA", inputDate.getText().toString());
                Log.d("HORA", inputTime.getText().toString());
                Log.d("DESCRICAO", inputDescription.getText().toString());

                createCompromisso(inputDate.getText().toString(), inputTime.getText().toString(), inputDescription.getText().toString());
                inputDate.setText("");
                inputTime.setText("");
                inputDescription.setText("");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_input, container, false);

        return this.view;
    }

    private Boolean isValid() {
        return (inputDescription.getText().length() > 0 &&
                inputDate.getText().length() > 0 &&
                inputTime.getText().length() > 0);
    }

    private void createCompromisso(String data, String hora, String descricao) {
        if (isValid()) {
            Compromisso compromisso = new Compromisso(data, hora, descricao);
            compromissoDB.addCompromisso(compromisso);
            outputListener.onOutputDateSet(data);
            Log.d("main", "Compromisso cadastrado");
            Toast toast = Toast.makeText(getContext(), "Compromisso cadastrado!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Log.d("main", "Dados incompletos");
            Toast toast = Toast.makeText(getContext(), "Dados incompletos!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String horaString = hourOfDay < 10 ? "0" + hourOfDay : String.valueOf(hourOfDay);
        String minutoString = minute < 10 ? "0" + minute : String.valueOf(minute);

        inputTime.setText(horaString + ":" + minutoString);
    }

    @Override
    public void onInputDateSet(int year, int month, int dayOfMonth) {
        String diaString = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        String mesString = month < 10 ? "0" + month : String.valueOf(month);
        String date = diaString + "/" + mesString + "/" + year;
        inputDate.setText(date);
    }
}