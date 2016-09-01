package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eliete on 8/31/16.
 */
public class ModelDetail {

    @SerializedName("Imovel")
    PropertyDetail property;

    public PropertyDetail getProperty() {
        return property;
    }

    public void setProperty(PropertyDetail property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "ModelDetail{" +
                "property=" + property +
                '}';
    }
}
