package com.example.minhagenda.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.minhagenda.R;
import com.example.minhagenda.database.CompromissosDB;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputFragment extends Fragment {

    private Button btnHoje;
    private Button btnOutroDia;
    private DatePickerFragment datePickerFragment;
    private TextView textCompromissos;
    private EditText textFiltro;
    private CompromissosDB compromissosDB;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_output, container, false);
        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (compromissosDB == null)
            compromissosDB = new CompromissosDB(getActivity().getBaseContext());

        textCompromissos = (TextView) this.view.findViewById(R.id.textCompromissos);
        btnHoje = (Button) this.view.findViewById(R.id.btnHoje);

        textFiltro = (EditText) this.view.findViewById(R.id.textFiltro);
        btnHoje.setOnClickListener(v -> {
            Log.i("BOTAO HOJE", String.format("HOJE: %s.", LocalDate.now()));
            textFiltro.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            String compromissos = compromissosDB.getCompromissosHoje();
            textCompromissos.setText(compromissos);
        });

        btnOutroDia = (Button) view.findViewById(R.id.btnOutroDia);
        datePickerFragment = new DatePickerFragment(getContext(), R.id.textFiltro);

        btnOutroDia.setOnClickListener((v) -> {
            textFiltro.setText("");
            datePickerFragment.show(getActivity().getSupportFragmentManager(), "outroDiaDatePicker");
        });

        String compromissos = compromissosDB.getCompromissosByDate(textFiltro.getText().toString());
        textCompromissos.setText(compromissos);
    }
}