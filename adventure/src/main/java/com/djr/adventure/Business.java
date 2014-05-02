package com.djr.adventure;

import java.io.Serializable;

/**
 * Created by Rafi on 3/23/2014.
 */
public class Business implements Serializable{
    String name;
    double longitude;
    double latitude;

    public Business(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() { return longitude; }

    public double getLatitude() {
        return latitude;
    }

    public String toString() {
        String ret = name + ": longitude=" + longitude + " latitude=" + latitude + "\n";
        return ret;
    }
}
