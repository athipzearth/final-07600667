package com.example.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "result")
    public final String result;

    @ColumnInfo(name = "distance")
    public final String distance;

    @ColumnInfo(name = "time")
    public final String time;


    public User(int id,String result, String distance,String time) {
        this.id = id;
        this.result = result;
        this.distance = distance;
        this.time = time;
    }
}
