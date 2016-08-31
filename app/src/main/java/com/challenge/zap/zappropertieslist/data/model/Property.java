package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eliete on 8/30/16.
 */
public class Property {

    public int CodImovel;
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

    public Property(int codImovel, String propertyType, String area, int dormitory, int parking, String urlImage, int price, Address address) {
        CodImovel = codImovel;
        this.propertyType = propertyType;
        this.area = area;
        this.dormitory = dormitory;
        this.parking = parking;
        this.urlImage = urlImage;
        this.price = price;
        this.address = address;
    }

    public int getCodImovel() {
        return CodImovel;
    }

    public void setCodImovel(int codImovel) {
        CodImovel = codImovel;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getDormitory() {
        return dormitory;
    }

    public void setDormitory(int dormitory) {
        this.dormitory = dormitory;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Property = " +
                CodImovel + "," +
                propertyType + "," +
                area + "," +
                dormitory + "," +
                parking + "," +
                urlImage + "," +
                price + "," +
                address;
    }
}
