package com.pbl4.view;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import com.pbl4.AppDatabase;
import com.pbl4.R;
import java.util.Calendar;
import com.pbl4.databinding.FragmentDetailBinding;
import com.pbl4.model.DBReport;
import com.pbl4.viewmodel.ReportDAO;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
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
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) binding.detailToolbar;
        toolbar.setTitle("Chi tiết");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpLocalDatabase();

        Bundle bundle = this.getArguments();
        String id = bundle.getString("CurrentReportID");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DBReport currentReport = reportDAO.getById(id);
                binding.txtDetailTime.setText(currentReport.getTime());
                binding.txtDetailHand.setText(currentReport.isWashHand() ? "Có" : "Không");
                binding.txtDetailTemp.setText(currentReport.getTemp() +"°C");
                binding.txtDetailMask.setText(currentReport.isMaskOn() ? "Có" : "Không");
                binding.txtDetailOpen.setText(currentReport.isOpenDoor() ? "Có" : "Không");
            }
        });

        SetActionListener();
    }

    private void setUpLocalDatabase() {
        AppDatabase appDatabase = AppDatabase.Instance(getContext());
        reportDAO = appDatabase.reportDAO();
    }

    private void SetActionListener() {

    }

}