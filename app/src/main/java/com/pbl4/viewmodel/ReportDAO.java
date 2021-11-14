package com.pbl4.viewmodel;

import androidx.room.Dao;
import androidx.room.Query;

import com.pbl4.model.DBReport;

import java.util.List;

@Dao
public interface ReportDAO {
    @Query("SELECT * FROM dbReport")
    List<DBReport> GetAllReport();
}
