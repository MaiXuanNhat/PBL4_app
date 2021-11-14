package com.pbl4.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pbl4.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.pbl4.RecyclerviewAdapter;
import com.pbl4.databinding.FragmentReportBinding;
import com.pbl4.model.DBReport;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private ArrayList<DBReport> dbReports;
    private RecyclerviewAdapter recyclerviewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetActionListener();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvReport.setLayoutManager(layoutManager);
        dbReports = new ArrayList<>();

        dbReports.add((new DBReport(new Timestamp(new Date().getTime()),37.5,true,true,true)));
        recyclerviewAdapter = new RecyclerviewAdapter(dbReports);
        recyclerviewAdapter.notifyDataSetChanged();
        binding.rvReport.setAdapter(recyclerviewAdapter);

    }

    private void SetActionListener() {
        SetDay();
        binding.txtFromReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtFromReport.setText("");
            }
        });
        binding.txtToReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtToReport.setText("");
            }
        });
        binding.btnFindReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void SetDay() {
        binding.txtFromReport.setText(GetRealTime());
        binding.txtToReport.setText(GetRealTime());
    }

    private String GetRealTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }

}