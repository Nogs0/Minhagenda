package com.example.minhagenda.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.minhagenda.R;
import com.example.minhagenda.activities.OnOutputDatePickerListener;
import com.example.minhagenda.database.CompromissosDB;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputFragment extends Fragment implements OnOutputDatePickerListener {

    private Button btnHoje;
    private Button btnOutroDia;
    private DatePickerFragment datePickerFragment;
    private TextView textCompromissos;
    private TextView textDataFiltrada;
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
        btnHoje.setOnClickListener(v -> {
            Log.i("BOTAO HOJE", String.format("HOJE: %s.", LocalDate.now()));
            textDataFiltrada = this.view.findViewById(R.id.txtDataFiltrada);
            textDataFiltrada.setText("Filtro: Hoje");
            String compromissos = compromissosDB.getCompromissosByDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            textCompromissos.setText(compromissos);
        });

        btnOutroDia = (Button) view.findViewById(R.id.btnOutroDia);
        datePickerFragment = new DatePickerFragment(getContext(), false);

        btnOutroDia.setOnClickListener((v) -> {
            datePickerFragment.show(this.getParentFragmentManager(), "outroDiaDatePicker");
        });
    }

    @Override
    public void onOutputDateSet(int year, int month, int dayOfMonth) {
        String diaString = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        String mesString = month < 10 ? "0" + month : String.valueOf(month);
        String date = diaString + "/" + mesString + "/" + year;
        textCompromissos.setText(compromissosDB.getCompromissosByDate(date));
        textDataFiltrada = this.view.findViewById(R.id.txtDataFiltrada);
        textDataFiltrada.setText("Filtro: " + date);
        Log.i("DATA ESCOLHIDA PRO FITLRO", date);
    }

    @Override
    public void onOutputDateSet(String date) {
        textCompromissos.setText(compromissosDB.getCompromissosByDate(date));
    }
}