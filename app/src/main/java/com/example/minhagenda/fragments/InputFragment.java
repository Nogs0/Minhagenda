package com.example.minhagenda.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minhagenda.R;
import com.example.minhagenda.database.Compromisso;
import com.example.minhagenda.database.CompromissosDB;

public class InputFragment extends Fragment {

    private View view;
    private EditText inputDescription;
    private EditText inputDate;
    private DatePickerFragment datePickerFragment;
    private EditText inputTime;
    private TimePickerFragment timePickerFragment;
    private Button btnOk;
    private CompromissosDB compromissoDB;
    private TextView textViewCompromissos;

    @Override
    public void onResume() {
        super.onResume();
        if (compromissoDB == null)
            compromissoDB = new CompromissosDB(getActivity().getBaseContext());

        inputDescription = (EditText) this.view.findViewById(R.id.inputDescription);
        inputDate = (EditText) this.view.findViewById(R.id.inputDate);
        datePickerFragment = new DatePickerFragment(getContext(), R.id.inputDate);

        // On Click Event
        inputDate.setOnClickListener(
                (view) -> datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker"));

        inputTime = (EditText) this.view.findViewById(R.id.inputTime);
        timePickerFragment = new TimePickerFragment(getContext(), R.id.inputTime);
        // On Click Event
        inputTime.setOnClickListener((view) ->
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker"));

        btnOk = (Button) this.view.findViewById(R.id.btnOk);
        // On Click Event
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DATA", inputDate.getText().toString());
                Log.d("HORA", inputTime.getText().toString());
                Log.d("DESCRICAO", inputDescription.getText().toString());

                createCompromisso(inputDate.getText().toString(), inputTime.getText().toString(), inputDescription.getText().toString());
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
        } else {
            Log.d("main", "Dados incompletos");
        }
    }
}