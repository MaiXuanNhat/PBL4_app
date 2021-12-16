package com.pbl4.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pbl4.AppDatabase;
import com.pbl4.FirebaseService;
import com.pbl4.R;

import java.util.Arrays;
import java.util.Calendar;
import com.pbl4.databinding.FragmentDetailBinding;
import com.pbl4.model.DBReport;
import com.pbl4.viewmodel.ReportDAO;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private ReportDAO reportDAO;
    private StorageReference mStorageReference;

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

        setUpFirebaseStorage();

        Bundle bundle = this.getArguments();
        String id = bundle.getString("CurrentReportID");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DBReport currentReport = reportDAO.getById(id);
                setImage(currentReport.getImgName());
                binding.txtDetailTime.setText(currentReport.getTime());
                binding.txtDetailHand.setText(currentReport.isWashHand() ? "Có" : "Không");
                binding.txtDetailTemp.setText(currentReport.getTemp() +"°C");
                binding.txtDetailMask.setText(currentReport.isMaskOn() ? "Có" : "Không");
                binding.txtDetailOpen.setText(currentReport.isOpenDoor() ? "Có" : "Không");
            }
        });

        SetActionListener();
    }

    private void setUpFirebaseStorage() {
        mStorageReference = FirebaseStorage.getInstance().getReference();
    }

    private void setUpLocalDatabase() {
        AppDatabase appDatabase = AppDatabase.Instance(getContext());
        reportDAO = appDatabase.reportDAO();
    }

    private void setImage(String imgName){
        try {
            final File tempFile = File.createTempFile(imgName.split("\\.")[0], imgName.split("\\.")[1]);
            mStorageReference.child(imgName).getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
                    binding.detailIMG.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Load image fail!", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetActionListener() {

    }

}