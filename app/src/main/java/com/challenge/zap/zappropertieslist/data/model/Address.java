package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eliete on 8/30/16.
 */
public class Address implements Serializable {

    @SerializedName("Logradouro")
    public String street;
    @SerializedName("Numero")
    public String number;
    @SerializedName("Cidade")
    public String city;
    @SerializedName("Bairro")
    public String neighborhood;
    @SerializedName("Complemento")
    public String complement;

    public Address() {

    }

    public Address(String street, String number, String city, String neighborhood, String complement) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.neighborhood = neighborhood;
        this.complement = complement;
    }

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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}
