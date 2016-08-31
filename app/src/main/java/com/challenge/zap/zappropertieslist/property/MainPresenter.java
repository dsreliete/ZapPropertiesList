package com.challenge.zap.zappropertieslist.property;

import android.support.annotation.NonNull;

import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.data.DataRepository;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public class MainPresenter implements MainContract.UserActionListener,
        DataRepository.getListOnFinishedListener {

    private MainContract.View mainContract;
    private DataRepository repository;

    public MainPresenter(MainActivity mainActivity, DataRepository service) {
        mainContract = mainActivity;
        repository = service;
    }

    @Override
    public void fetchPropertyList() {
        if (mainContract != null) {
            mainContract.showProgress();
        }
        repository.getPropertiesList(this);
    }

    @Override
    public void openItemDetails(@NonNull Property requestedProperty) {
        mainContract.showPropertyDetailActivity(requestedProperty);
    }

    @Override
    public void onFinishedList(List<Property> list) {
        if (list != null){
            mainContract.hideProgress();
            mainContract.showPropertiesList(list);
        }else{
            mainContract.showNoProperty();
        }
    }


}
