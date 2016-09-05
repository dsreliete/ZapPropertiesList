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
    @SerializedName("PrecoVenda")
    public int price;
    @SerializedName("Endereco")
    public Address address;
    @SerializedName("Suites")
    public int suites;
    @SerializedName("Cliente")
    public Client client;
    @SerializedName("CaracteristicasComum")
    public List<String> characteristicsList;
    @SerializedName("ValorIPTU")
    public int iptu;
    @SerializedName("PrecoCondominio")
    public int condominiumPrice;
    @SerializedName("Observacao")
    public String observation;
    @SerializedName("SubTipoOferta")
    public String offer;
    @SerializedName("InformacoesComplementares")
    public String information;
    @SerializedName("DataAtualizacao")
    public String date;
    @SerializedName("Fotos")
    public List<String> photosList;

    public PropertyDetail(int codeProperty, String propertyType, int area, int dormitory, int parking,
                          int price, Address address, int suites, Client client,
                          List<String> characteristicsList, int iptu, int condominiumPrice,
                          String observation, String offer, String information, String date) {
        this.codeProperty = codeProperty;
        this.propertyType = propertyType;
        this.area = area;
        this.dormitory = dormitory;
        this.parking = parking;
        this.price = price;
        this.address = address;
        this.suites = suites;
        this.client = client;
        this.characteristicsList = characteristicsList;
        this.iptu = iptu;
        this.condominiumPrice = condominiumPrice;
        this.observation = observation;
        this.offer = offer;
        this.information = information;
        this.date = date;
    }

    public int getCodeProperty() {
        return codeProperty;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public int getArea() {
        return area;
    }

    public int getDormitory() {
        return dormitory;
    }

    public int getParking() {
        return parking;
    }

    public int getPrice() {
        return price;
    }

    public Address getAddress() {
        return address;
    }

    public int getSuites() {
        return suites;
    }

    public Client getClient() {
        return client;
    }

    public List<String> getCharacteristicsList() {
        return characteristicsList;
    }

    public int getIptu() {
        return iptu;
    }

    public int getCondominiumPrice() {
        return condominiumPrice;
    }

    public String getObservation() {
        return observation;
    }

    public String getOffer() {
        return offer;
    }

    public String getInformation() {
        return information;
    }

    public String getDate() {
        return date;
    }

    public List<String> getPhotosList() {
        return photosList;
    }

    @Override
    public String toString() {
        return "PropertyDetail{" +
                "codeProperty=" + codeProperty +
                ", propertyType='" + propertyType + '\'' +
                ", area=" + area +
                ", dormitory=" + dormitory +
                ", parking=" + parking +
                ", price=" + price +
                ", address=" + address +
                ", suites=" + suites +
                ", client=" + client +
                ", characteristicsList=" + characteristicsList +
                ", iptu=" + iptu +
                ", condominiumPrice=" + condominiumPrice +
                ", observation='" + observation + '\'' +
                ", offer='" + offer + '\'' +
                ", information='" + information + '\'' +
                ", date='" + date + '\'' +
                ", photosList='" + photosList + '\'' +
                '}';
    }
}
