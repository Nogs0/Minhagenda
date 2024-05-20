package com.example.minhagenda.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.minhagenda.R;

import java.time.LocalDate;

public class OutputFragment extends Fragment {

    private Button btnHoje;
    private Button btnOutroDia;
    private DatePickerFragment datePickerFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_output, container, false);
        btnHoje = (Button) view.findViewById(R.id.btnHoje);

        btnHoje.setOnClickListener(v -> {
            Log.i("BOTAO HOJE", String.format("HOJE: %s.", LocalDate.now()));
        });

        btnOutroDia = (Button) view.findViewById(R.id.btnOutroDia);
        datePickerFragment = new DatePickerFragment(getContext());

        btnOutroDia.setOnClickListener((v) ->
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "outroDiaDatePicker"));

        return view;
    }
}