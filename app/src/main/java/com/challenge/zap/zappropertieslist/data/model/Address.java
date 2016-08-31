package com.challenge.zap.zappropertieslist.data.model;

/**
 * Created by eliete on 8/30/16.
 */
public class Address {

    public String street;
    public String number;
    public String city;
    public String neighborhood;
    public String zone;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Address = " +
                street + "," +
                number + "," +
                city + "," +
                neighborhood + "," +
                zone;
    }
}
