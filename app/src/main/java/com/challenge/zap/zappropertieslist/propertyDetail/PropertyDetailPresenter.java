package com.challenge.zap.zappropertieslist.propertyDetail;

import com.challenge.zap.zappropertieslist.data.DataRepository;
import com.challenge.zap.zappropertieslist.data.model.PropertyDetail;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyDetailPresenter implements PropertyDetailContract.UserActionListener,
        DataRepository.getDetailOnFinishedListener, DataRepository.getSentMessageOnFinishedListener {

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
    public void sendMessage(ZapMessage message) {
        if (propertyContract != null)
            propertyContract.showProgress();
        dataRepository.sendMessage(this, message);
    }

    @Override
    public void onFinishedDetail(PropertyDetail property) {
        propertyContract.hideProgress();
        if (property != null){

            propertyContract.showDetailContainer(true);

            if (property.getCharacteristicsList().size() > 0){
                propertyContract.showCharacteristics(property.getCharacteristicsList());
            }

            if (property.getCliente() != null){
                propertyContract.showClientInformation(property.getCliente());
            }

            if (property.getCondominiumPrice() > 0 ){
                propertyContract.showCondominiumValue(property.getCondominiumPrice() );
            }

            if (property.getIptu() > 0){
                propertyContract.showIptuValue(property.getIptu());
            }

            if (property.getPrice() > 0){
                propertyContract.showPrice(property.getPrice());
            }

            if (property.getAddress() != null){
                propertyContract.showAddress(property.getAddress());
            }

            if (property.getArea() >= 0 || property.getDormitory() >= 0 || property.getParking() >= 0
                    || property.getSuites() >= 0){
                propertyContract.showDetailProperty(property.getArea(), property.getDormitory(),
                        property.getParking(), property.getSuites());
            }

            if (property.getInformation() != null){
                propertyContract.showInformation(property.getInformation());
            }

        }else{
            propertyContract.showDetailContainer(false);
            propertyContract.showNoDetail();
        }

    }

    @Override
    public void onFinishedSendMessage(boolean condition) {
        propertyContract.hideProgress();
        propertyContract.showSnackBar(condition);
    }
}
