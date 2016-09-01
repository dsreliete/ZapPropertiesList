package com.challenge.zap.zappropertieslist.data;

import android.os.Handler;

import com.challenge.zap.zappropertieslist.data.model.Model;
import com.challenge.zap.zappropertieslist.data.model.ModelDetail;
import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.data.model.PropertyDetail;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;
import com.challenge.zap.zappropertieslist.data.webservice.ZapImoveisApi;
import com.challenge.zap.zappropertieslist.data.webservice.ZapImoveisApiManager;
import com.challenge.zap.zappropertieslist.data.webservice.ZapImoveisApiManagerImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eliete on 8/31/16.
 */
public class DataRepositoryImpl implements DataRepository {

    private List<Property> propertyList;
    private boolean handler;
    private ModelDetail property;
    private ZapImoveisApiManager manager = new ZapImoveisApiManagerImpl();
    private ZapImoveisApi zapImoveisApi = manager.getZapImoveisApiInstance();

    @Override
    public void getPropertiesList(final getListOnFinishedListener listener) {
        zapImoveisApi.getPropertyList().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        propertyList = response.body().propertyList;
                        returnPropertyList(propertyList, listener);
                    }else{
                        returnPropertyList(propertyList, listener);
                    }

                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                returnPropertyList(propertyList, listener);
            }
        });

    }

    public void returnPropertyList(final List<Property> list,
                               final getListOnFinishedListener listener) {
        handler = new Handler().post(new Runnable() {
            @Override
            public void run() {
                listener.onFinishedList(list);
            }
        });
    }

    @Override
    public void getDetailProperty(final getDetailOnFinishedListener listener, int codProperty) {
        zapImoveisApi.getPropertyDetail(codProperty).enqueue(new Callback<ModelDetail>() {
            @Override
            public void onResponse(Call<ModelDetail> call, Response<ModelDetail> response) {
                if (response.isSuccessful()){
                    property = response.body();
                    returnPropertyDetail(property, listener);
                }else{
                    returnPropertyDetail(property, listener);
                }
            }

            @Override
            public void onFailure(Call<ModelDetail> call, Throwable t) {
                returnPropertyDetail(property, listener);
            }
        });
    }

    public void returnPropertyDetail(final ModelDetail modelProperty,
                                   final getDetailOnFinishedListener listener) {
        handler = new Handler().post(new Runnable() {
            @Override
            public void run() {
                PropertyDetail propertyDetail = null;
                if (modelProperty != null){
                    if (modelProperty.getProperty() != null)
                        propertyDetail = modelProperty.getProperty();

                }
                listener.onFinishedDetail(propertyDetail);
            }
        });
    }

    @Override
    public void sendMessage(final getSentMessageOnFinishedListener listener, ZapMessage message) {
        zapImoveisApi.sendMessage(message).enqueue(new Callback<ZapMessage>() {
            @Override
            public void onResponse(Call<ZapMessage> call, Response<ZapMessage> response) {
                if (response.isSuccessful()){
                    returnSendMessage(listener, true);
                }else{
                    returnSendMessage(listener, false);
                }
            }

            @Override
            public void onFailure(Call<ZapMessage> call, Throwable t) {
                returnSendMessage(listener, false);
            }
        });
    }

    public void returnSendMessage(final getSentMessageOnFinishedListener listener, final boolean success) {
        handler = new Handler().post(new Runnable() {
            @Override
            public void run() {
                listener.onFinishedSendMessage(success);
            }
        });
    }
}
