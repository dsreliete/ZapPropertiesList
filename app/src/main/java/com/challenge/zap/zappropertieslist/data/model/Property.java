package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eliete on 8/30/16.
 */
public class Property {

    @SerializedName("CodImovel")
    public int codeProperty;
    @SerializedName("TipoImovel")
    public String propertyType;
    @SerializedName("AreaTotal")
    public String area;
    @SerializedName("Dormitorios")
    public int dormitory;
    @SerializedName("Vagas")
    public int parking;
    @SerializedName("UrlImagem")
    public String urlImage;
    @SerializedName("PrecoVenda")
    public int price;
    @SerializedName("Endereco")
    public Address address;
    @SerializedName("Suites")
    public String suite;
    @SerializedName("SubTipoOferta")
    public String sale;


    public Property(int codeProperty, String propertyType, String area, int dormitory, int parking, String urlImage, int price, Address address) {
        this.codeProperty = codeProperty;
        this.propertyType = propertyType;
        this.area = area;
        this.dormitory = dormitory;
        this.parking = parking;
        this.urlImage = urlImage;
        this.price = price;
        this.address = address;
    }

    public int getCodeProperty() {
        return codeProperty;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getArea() {
        return area;
    }

    public int getDormitory() {
        return dormitory;
    }

    public int getParking() {
        return parking;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public double getPrice() {
        return price;
    }

    public Address getAddress() {
        return address;
    }

    public String getSuite() {
        return suite;
    }

    public String getSale() {
        return sale;
    }

    @Override
    public String toString() {
        return "Property{" +
                "codeProperty=" + codeProperty +
                ", propertyType='" + propertyType + '\'' +
                ", area='" + area + '\'' +
                ", dormitory=" + dormitory +
                ", parking=" + parking +
                ", urlImage='" + urlImage + '\'' +
                ", price=" + price +
                ", address=" + address +
                ", suite='" + suite + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
}
