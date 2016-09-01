package com.challenge.zap.zappropertieslist.propertyDetail;

import com.challenge.zap.zappropertieslist.data.DataRepository;
import com.challenge.zap.zappropertieslist.data.model.PropertyDetail;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyDetailPresenter implements PropertyDetailContract.UserActionListener,
        DataRepository.getDetailOnFinishedListener {

    PropertyDetailContract.View propertyContract;
    DataRepository dataRepository;

    public PropertyDetailPresenter(PropertyDetailActivity propertyDetailActivity,
                                   DataRepository repository) {
        propertyContract = propertyDetailActivity;
        dataRepository = repository;
    }

    @Override
    public void fetchPropertyDetail(int codeProperty) {
        if (propertyContract != null)
            propertyContract.showProgress();
        dataRepository.getDetailProperty(this, codeProperty);
    }

    @Override
    public void onFinishedDetail(PropertyDetail property) {
        propertyContract.hideProgress();
        if (property != null){

            if (property.getDate() != null){
                propertyContract.showDateContainer(property.getDate());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getCharacteristicsList() != null){
                propertyContract.showCharacteristics(property.getCharacteristicsList());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getCliente() != null){
                propertyContract.showClientInformation(property.getCliente());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getCondominiumPrice() > 0 ){
                propertyContract.showCondominiumValue(property.getCondominiumPrice() );
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getIptu() > 0){
                propertyContract.showIptuValue(property.getIptu());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getPrice() > 0){
                propertyContract.showPrice(property.getPrice());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getAddress() != null){
                propertyContract.showAddress(property.getAddress());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getArea() > 0 || property.getDormitory() > 0 || property.getParking() > 0
                    || property.getSuites() > 0){
                propertyContract.showDetailProperty(property.getArea(), property.getDormitory(),
                        property.getParking(), property.getSuites());
            }else{
                propertyContract.showNoDetail();
            }

            if (property.getInformation() != null){
                propertyContract.showInformation(property.getInformation());
            }else{
                propertyContract.showNoDetail();
            }

        }else{
            propertyContract.showNoDetail();
        }

    }
}
