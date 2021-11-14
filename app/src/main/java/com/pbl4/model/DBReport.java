package com.pbl4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "dbReport")
public class DBReport {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private Date time;
    @ColumnInfo
    private double temp;
    @ColumnInfo
    private boolean maskOn;
    @ColumnInfo
    private boolean washHand;
    @ColumnInfo
    private boolean openDoor;

    public DBReport(Date time, double temp, boolean maskOn, boolean washHand, boolean openDoor) {
        this.time = time;
        this.temp = temp;
        this.maskOn = maskOn;
        this.washHand = washHand;
        this.openDoor = openDoor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public boolean isMaskOn() {
        return maskOn;
    }

    public void setMaskOn(boolean maskOn) {
        this.maskOn = maskOn;
    }

    public boolean isWashHand() {
        return washHand;
    }

    public void setWashHand(boolean washHand) {
        this.washHand = washHand;
    }

    public boolean isOpenDoor() {
        return openDoor;
    }

    public void setOpenDoor(boolean openDoor) {
        this.openDoor = openDoor;
    }
}
