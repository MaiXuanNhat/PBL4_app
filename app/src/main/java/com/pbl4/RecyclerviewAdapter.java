package com.pbl4;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbl4.databinding.FragmentReportBinding;
import com.pbl4.databinding.ReportRowItemBinding;
import com.pbl4.model.DBReport;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> implements Filterable {
    private List<DBReport> allDBReportList;
    private List<DBReport> dbReportList;
    private reportClickListener listener;

    public RecyclerviewAdapter(List<DBReport> dbReportList, reportClickListener listener) {
        this.allDBReportList = dbReportList;
        this.dbReportList = dbReportList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_row_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DBReport currentReport = dbReportList.get(position);
        holder.binding.stt.setText((position+1) + "");
        holder.binding.thoigian.setText(currentReport.getTime());
        holder.binding.nhietdo.setText(String.format("%.2f", currentReport.getTemp())+"°C");
        holder.binding.khautrang.setText(currentReport.isMaskOn() ? "Có":"Không");
        holder.binding.ruatay.setText(currentReport.isWashHand() ? "Có":"Không");
        holder.binding.mocua.setText(currentReport.isOpenDoor() ? "Có":"Không");
    }

    @Override
    public int getItemCount() {
        return dbReportList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String fromString = charSequence.toString().split(" ")[0];
                String toString = charSequence.toString().split(" ")[1];
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    if (fromString.equals("*") && toString.equals("*")){
                        dbReportList = allDBReportList;
                    }
                    else {
                        List<DBReport> filter = new ArrayList<>();
                        if (fromString.equals("*") && !toString.equals("*")){
                            // Loc tu date ve truoc
                            Date date = format.parse(toString);
                            for (DBReport dbReport : allDBReportList){
                                Date rpDate = format.parse(dbReport.getTime());
                                if (date.compareTo(rpDate) >= 0){
                                    filter.add(dbReport);
                                }
                            }
                        }
                        else if (!fromString.equals("*") && toString.equals("*")){
                            // Loc tu date ve sau
                            Date date = format.parse(fromString);
                            for (DBReport dbReport : allDBReportList){
                                Date rpDate = format.parse(dbReport.getTime());
                                if (date.compareTo(rpDate) <= 0){
                                    filter.add(dbReport);
                                }
                            }
                        }
                        else {
                            Date from = format.parse(fromString);
                            Date to = format.parse(toString);

                            for (DBReport dbReport : allDBReportList){
                                Date rpDate = format.parse(dbReport.getTime());
                                if (rpDate.compareTo(from) >=0 && rpDate.compareTo(to) <= 0){
                                    filter.add(dbReport);
                                }
                            }
                        }
                        dbReportList = filter;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dbReportList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dbReportList = (List<DBReport>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ReportRowItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ReportRowItemBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCLick(view,getAdapterPosition());
        }
    }
    public interface reportClickListener{
        void onCLick(View view,int i);
    }
}
