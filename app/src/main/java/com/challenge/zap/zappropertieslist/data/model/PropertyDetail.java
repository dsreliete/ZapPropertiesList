package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyDetail {

    @SerializedName("CodImovel")
    public int codeProperty;
    @SerializedName("TipoImovel")
    public String propertyType;
    @SerializedName("AreaTotal")
    public int area;
    @SerializedName("Dormitorios")
    public int dormitory;
    @SerializedName("Vagas")
    public int parking;
    @SerializedName("Fotos")
    public List<String> urlImageList;
    @SerializedName("PrecoVenda")
    public int price;
    @SerializedName("Endereco")
    public Address address;
    @SerializedName("Suites")
    public int suites;
    @SerializedName("DataAtualizacao")
    public String date;
    @SerializedName("Cliente")
    public Client cliente;
    @SerializedName("CaracteristicasComum")
    public List<String> characteristicsList;
    @SerializedName("ValorIPTU")
    public int iptu;
    @SerializedName("PrecoCondominio")
    public int condominiumPrice;
    @SerializedName("Observacao")
    public String information;

    public int getCodeProperty() {
        return codeProperty;
    }

    public void setCodeProperty(int codeProperty) {
        this.codeProperty = codeProperty;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
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

    public List<String> getUrlImageList() {
        return urlImageList;
    }

    public void setUrlImageList(List<String> urlImageList) {
        this.urlImageList = urlImageList;
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

    public int getSuites() {
        return suites;
    }

    public void setSuites(int suites) {
        this.suites = suites;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public List<String> getCharacteristicsList() {
        return characteristicsList;
    }

    public void setCharacteristicsList(List<String> characteristicsList) {
        this.characteristicsList = characteristicsList;
    }

    public int getIptu() {
        return iptu;
    }

    public void setIptu(int iptu) {
        this.iptu = iptu;
    }

    public int getCondominiumPrice() {
        return condominiumPrice;
    }

    public void setCondominiumPrice(int condominiumPrice) {
        this.condominiumPrice = condominiumPrice;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "PropertyDetail{" +
                "codeProperty=" + codeProperty +
                ", propertyType='" + propertyType + '\'' +
                ", area='" + area + '\'' +
                ", dormitory=" + dormitory +
                ", parking=" + parking +
                ", urlImage='" + urlImageList + '\'' +
                ", price=" + price +
                ", address=" + address +
                ", suites=" + suites +
                ", date='" + date + '\'' +
                ", cliente=" + cliente +
                ", characteristicsList=" + characteristicsList +
                ", iptu=" + iptu +
                ", condominiumPrice=" + condominiumPrice +
                ", information='" + information + '\'' +
                '}';
    }
}
