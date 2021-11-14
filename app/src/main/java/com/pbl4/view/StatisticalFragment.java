package com.pbl4.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pbl4.R;
import com.pbl4.databinding.FragmentStatisticalBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StatisticalFragment extends Fragment {

    private FragmentStatisticalBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatisticalBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetActionListener();
    }

    private void SetActionListener() {
        SetDay();
        binding.txtFromStas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtFromStas.setText("");
            }
        });
        binding.txtToStas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtToStas.setText("");
            }
        });
        binding.btnFindStas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void SetDay() {
        binding.txtFromStas.setText(GetRealTime());
        binding.txtToStas.setText(GetRealTime());
    }

    private String GetRealTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }
}