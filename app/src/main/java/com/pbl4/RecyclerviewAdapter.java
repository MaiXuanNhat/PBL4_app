package com.pbl4;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbl4.databinding.FragmentReportBinding;
import com.pbl4.databinding.ReportRowItemBinding;
import com.pbl4.model.DBReport;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>{
    private ArrayList<DBReport> dbReportList;

    public RecyclerviewAdapter(ArrayList<DBReport> dbReportList) {
        this.dbReportList = dbReportList;
        Log.d("tag", this.dbReportList.get(0).getTemp() +"");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_row_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("tag", "onBindViewHolder: " + position);
        DBReport currentReport = dbReportList.get(position);
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        holder.binding.stt.setText(currentReport.getId());
        holder.binding.thoigian.setText(format.format(currentReport.getTime()));
        holder.binding.nhietdo.setText(currentReport.getTemp()+"");
        holder.binding.khautrang.setText(currentReport.isMaskOn() ? "Có":"Không");
        holder.binding.ruatay.setText(currentReport.isWashHand() ? "Có":"Không");
        holder.binding.mocua.setText(currentReport.isOpenDoor() ? "Có":"Không");
        Log.d("tag", "onBindViewHolder: " + format.format(currentReport.getTime()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ReportRowItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ReportRowItemBinding.bind(itemView);
        }
    }
}
