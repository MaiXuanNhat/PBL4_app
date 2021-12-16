package com.pbl4.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pbl4.AppDatabase;
import com.pbl4.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.pbl4.RecyclerviewAdapter;
import com.pbl4.databinding.FragmentReportBinding;
import com.pbl4.model.DBReport;
import com.pbl4.viewmodel.ReportDAO;

public class ReportFragment extends Fragment {

    private FragmentReportBinding binding;
    private List<DBReport> dbReports;
    private RecyclerviewAdapter recyclerviewAdapter;
    private ReportDAO reportDAO;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvReport.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) binding.rpToolbar;
        toolbar.setTitle("Báo cáo");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpLocalDatabase();

        SetActionListener();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        AsyncTask.execute(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                dbReports = reportDAO.getAll();
                binding.txtFromReport.setText(dbReports.get(0).getTime());
                binding.txtToReport.setText(dbReports.get(dbReports.size()-1).getTime());

                RecyclerviewAdapter.reportClickListener reportClickListener = new RecyclerviewAdapter.reportClickListener() {
                    @Override
                    public void onCLick(View view, int i) {
                        Bundle bundle = new Bundle();
                        DBReport dbReport = dbReports.get(i);
                        bundle.putString("CurrentReportID", dbReport.getId());
                        DetailFragment detailFragment = new DetailFragment();
                        detailFragment.setArguments(bundle);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView, detailFragment).addToBackStack(null).commit();
                    }
                };

                recyclerviewAdapter = new RecyclerviewAdapter(dbReports, reportClickListener);
                binding.rvReport.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpLocalDatabase() {
        AppDatabase appDatabase = AppDatabase.Instance(getContext());
        reportDAO = appDatabase.reportDAO();
    }

    private void SetActionListener() {
        binding.btnFindReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = binding.txtFromReport.getText().toString();
                String to = binding.txtToReport.getText().toString();

                if (from.equals("")) from = "*";
                if (to.equals("")) to = "*";

                recyclerviewAdapter.getFilter().filter(from + " " + to);
            }
        });

        binding.btnReloadReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Refresh location fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ReportFragment reportFragment = new ReportFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, reportFragment).addToBackStack(null).commit();
            }
        });
    }
}