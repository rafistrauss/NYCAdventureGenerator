package com.djr.adventure;

import java.io.Serializable;

/**
 * Created by Rafi on 3/23/2014.
 */
public class Business implements Serializable{
    String name;
    double longitude;
    double latitude;
    String address;
    String category;

    public Business(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Business(String name, double longitude, double latitude, String address) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() { return longitude; }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() { return address; }

    public String getCategory() { return category; }

    public String toString() {
        String ret = name + ": longitude=" + longitude + " latitude=," + latitude + " address=" + address + "\n";
        return ret;
    }
}
