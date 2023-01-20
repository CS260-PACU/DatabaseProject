package edu.pacificu.cs.cs260.databaseproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note  extends  ParentNote {
    @PrimaryKey(autoGenerate = true)
    private int nid;

    @ColumnInfo(name = "msg")
    private String msg;

    @ColumnInfo(name="datetime")
    private String datetime;

    @ColumnInfo(name="gps_lat")
    private double gps_Lat;

    @ColumnInfo(name="gps_lon")
    private double gps_Lon;

    @ColumnInfo(name = "image")
    private byte[] image;

    public int getNid ()
    {
        return nid;
    }

    public void setNid (int nid)
    {
        this.nid = nid;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public String getDatetime ()
    {
        return datetime;
    }

    public void setDatetime (String datetime)
    {
        this.datetime = datetime;
    }

    public double getGps_Lat ()
    {
        return gps_Lat;
    }

    public void setGps_Lat (double gps_Lat)
    {
        this.gps_Lat = gps_Lat;
    }

    public double getGps_Lon ()
    {
        return gps_Lon;
    }

    public void setGps_Lon (double gps_Lon)
    {
        this.gps_Lon = gps_Lon;
    }

    public byte[] getImage ()
    {
        return image;
    }

    public void setImage (byte[] image)
    {
        this.image = image;
    }
}
