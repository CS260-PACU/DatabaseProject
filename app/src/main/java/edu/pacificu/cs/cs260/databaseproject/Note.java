package edu.pacificu.cs.cs260.databaseproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "msg")
    public String msg;

    @ColumnInfo(name="datetime")
    public String datetime;

    @ColumnInfo(name="gps_lat")
    public double gps_Lat;

    @ColumnInfo(name="gps_lon")
    public double gps_Lon;

    @ColumnInfo(name = "image")
    public byte[] image;
}
