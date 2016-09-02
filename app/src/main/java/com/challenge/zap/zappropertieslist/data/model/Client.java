package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eliete on 8/31/16.
 */
public class Client {

    @SerializedName("NomeFantasia")
    public String name;

    @SerializedName("CodCliente")
    public int clientId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(String name, int clientId) {
        this.name = name;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
