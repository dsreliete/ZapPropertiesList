package com.challenge.zap.zappropertieslist.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliete on 8/31/16.
 */
public class Model {

    @SerializedName("Imoveis")
    public List<Property> propertyList = new ArrayList<>();
}
