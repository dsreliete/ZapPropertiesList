package com.challenge.zap.zappropertieslist.data;

import android.os.Handler;
import android.util.Log;

import com.challenge.zap.zappropertieslist.data.model.Model;
import com.challenge.zap.zappropertieslist.data.model.Property;
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
    private ZapImoveisApiManager manager = new ZapImoveisApiManagerImpl();
    private ZapImoveisApi zapImoveisApi = manager.getZapImoveisApiInstance();


    @Override
    public void getPropertiesList(final getListOnFinishedListener listener) {
        zapImoveisApi.getPropertyList().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.e("eliete", "response.raw() "+ response.raw());
                if (response.isSuccessful()){
                   propertyList = response.body().propertyList;
                    returnPropertyList(propertyList, listener);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                propertyList = null;
                returnPropertyList(propertyList, listener);
            }
        });

    }

    public void returnPropertyList(final List<Property> list,
                               final getListOnFinishedListener listener) {
        handler = new Handler().post(new Runnable() {
            @Override
            public void run() {
                Log.e("eliete", "list =  "+ list);
                listener.onFinishedList(list);
            }
        });
    }
}
