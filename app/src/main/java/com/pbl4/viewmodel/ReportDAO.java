package com.pbl4.viewmodel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pbl4.model.DBReport;

import java.util.List;

@Dao
public interface ReportDAO {
    @Query("SELECT * FROM dbReport")
    List<DBReport> getAll();

    @Query("SELECT * FROM dbReport WHERE id=:id")
    DBReport getById(String id);

    @Insert
    void addAll(DBReport... dbReports);

    @Update
    void update(DBReport dbReport);
}
