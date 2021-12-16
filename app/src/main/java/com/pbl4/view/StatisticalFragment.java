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

import com.pbl4.AppDatabase;
import com.pbl4.R;
import com.pbl4.databinding.FragmentStatisticalBinding;
import com.pbl4.model.DBReport;
import com.pbl4.viewmodel.ReportDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticalFragment extends Fragment {

    private FragmentStatisticalBinding binding;
    private List<DBReport> allDBReports;
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
        binding = FragmentStatisticalBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) binding.stasToolbar;
        toolbar.setTitle("Thống kê");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpLocalDatabase();
        SetUpStatistical();
        SetActionListener();
    }

    private void setUpLocalDatabase() {
        AppDatabase appDatabase = AppDatabase.Instance(getContext());
        reportDAO = appDatabase.reportDAO();
    }

    private void SetUpStatistical() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                allDBReports = reportDAO.getAll();
                binding.txtFromStas.setText(allDBReports.get(0).getTime());
                binding.txtToStas.setText(allDBReports.get(allDBReports.size()-1).getTime());
                setStatiscal(allDBReports);
            }
        });
    }

    private void SetActionListener() {
        binding.btnFindStas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = binding.txtFromStas.getText().toString();
                String to = binding.txtToStas.getText().toString();

                if (from.equals("")) from = "*";
                if (to.equals("")) to = "*";

                List<DBReport> filter = getFilterStas(from, to);

                if (filter.size() > 0) setStatiscal(filter);
            }
        });
        binding.btnReloadStas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Refresh location fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                StatisticalFragment statisticalFragment = new StatisticalFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, statisticalFragment).addToBackStack(null).commit();
            }
        });
    }

    private List<DBReport> getFilterStas(String from, String to) {
        List<DBReport> filter = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (from.equals("*") && to.equals("*")){
                filter = allDBReports;
            }
            else {
                if (from.equals("*") && !to.equals("*")){
                    // Loc tu date ve truoc
                    Date date = format.parse(to);
                    for (DBReport dbReport : allDBReports){
                        Date rpDate = format.parse(dbReport.getTime());
                        if (date.compareTo(rpDate) >= 0){
                            filter.add(dbReport);
                        }
                    }
                }
                else if (!from.equals("*") && to.equals("*")){
                    // Loc tu date ve sau
                    Date date = format.parse(from);
                    for (DBReport dbReport : allDBReports){
                        Date rpDate = format.parse(dbReport.getTime());
                        if (date.compareTo(rpDate) <= 0){
                            filter.add(dbReport);
                        }
                    }
                }
                else {
                    Date fromDate = format.parse(from);
                    Date toDate = format.parse(to);

                    for (DBReport dbReport : allDBReports){
                        Date rpDate = format.parse(dbReport.getTime());
                        if (rpDate.compareTo(fromDate) >=0 && rpDate.compareTo(toDate) <= 0){
                            filter.add(dbReport);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return filter;
    }

    private void setStatiscal(List<DBReport> filter) {
        int cntMask = 0;
        double sumTemp = 0;
        int cntHand = 0;
        int cntOpen = 0;

        for (DBReport dbReport : filter){
            if (dbReport.isMaskOn()) cntMask++;
            sumTemp += dbReport.getTemp();
            if (dbReport.isWashHand()) cntHand++;
            if (dbReport.isOpenDoor()) cntOpen++;
        }

        // So nguoi
        binding.txtCntNum.setText(filter.size()+"");
        binding.txtPerNum.setText("100%");
        // Khau trang
        binding.txtCntMask.setText(cntMask+"");
        binding.txtPerMask.setText(String.format("%.1f", (double)100*cntMask/filter.size())+"%");
        // Nhiet do trung binh
        binding.txtAvgTemp.setText((double)sumTemp/filter.size()+"°C");
        // Rua tay
        binding.txtCntHand.setText(cntHand+"");
        binding.txtPerHand.setText(String.format("%.1f",(double)100*cntHand/filter.size())+"%");
        // Mo cua
        binding.txtCntOpen.setText(cntOpen+"");
        binding.txtPerOpen.setText(String.format("%.1f",(double)100*cntOpen/filter.size())+"%");
    }
}