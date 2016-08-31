package com.challenge.zap.zappropertieslist.data.webservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eliete on 8/25/16.
 */
public class ZapImoveisApiManagerImpl implements ZapImoveisApiManager {


    public static final String BASE_URL = " http://demo4573903.mockable.io";
    private ZapImoveisApi zapImoveisApi;

    public ZapImoveisApiManagerImpl() {
        setupZapImoveisApi();
    }

    @Override
    public ZapImoveisApi getZapImoveisApiInstance() {
        return zapImoveisApi;
    }

    @Override
    public void setupZapImoveisApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHTTPClient = new OkHttpClient
                .Builder()
                //.cache(new Cache(new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString()), 8 * 1024 * 1024))
                .addInterceptor(logging)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHTTPClient)
                .build();

        zapImoveisApi = retrofit.create(ZapImoveisApi.class);
    }
}
