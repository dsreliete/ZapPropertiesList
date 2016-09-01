package com.challenge.zap.zappropertieslist.data.webservice;

import com.challenge.zap.zappropertieslist.data.model.Model;
import com.challenge.zap.zappropertieslist.data.model.ModelDetail;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eliete on 8/25/16.
 */
public interface ZapImoveisApi {

    @GET("/imoveis")
    Call<Model> getPropertyList();

    @GET("/imoveis/{code}")
    Call<ModelDetail> getPropertyDetail(@Path("code") int code);

    @POST("/imoveis/contato")
    Call<ZapMessage> sendMessage(@Body ZapMessage zapMessage);

}
