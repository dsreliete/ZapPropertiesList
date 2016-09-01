package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eliete on 8/31/16.
 */
public class Client {

    @SerializedName("NomeFantasia")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }
}
