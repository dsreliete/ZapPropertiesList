package com.challenge.zap.zappropertieslist.data.webservice;

import com.challenge.zap.zappropertieslist.data.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eliete on 8/25/16.
 */
public interface ZapImoveisApi {

    @GET("/imoveis")
    Call<Model> getPropertyList();

}
