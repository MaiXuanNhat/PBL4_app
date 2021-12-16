package com.pbl4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pbl4.model.DBReport;
import com.pbl4.model.DateConverter;
import com.pbl4.viewmodel.ReportDAO;

@Database(entities = {DBReport.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReportDAO reportDAO();
    private static AppDatabase _Instance;
    public static AppDatabase Instance(Context context){
        if (_Instance == null){
            _Instance = Room.databaseBuilder(context,
                    AppDatabase.class, "DBReport").build();
        }
        return _Instance;
    }
}
