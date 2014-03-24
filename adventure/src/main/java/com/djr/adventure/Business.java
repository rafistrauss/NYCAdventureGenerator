package com.djr.adventure;

/**
 * Created by Rafi on 3/23/2014.
 */
public class Business {
    String name;
    String address;
    String city;
    String state;
    String country;

    public Business(String name, String address, String city, String state, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String toString() {
        String ret = name + "\n" + address + "\n" + city + "\n" + state + "\n" + country;
        return ret;
    }
}
