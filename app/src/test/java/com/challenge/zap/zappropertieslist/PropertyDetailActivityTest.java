package com.challenge.zap.zappropertieslist;

import com.challenge.zap.zappropertieslist.data.DataRepository;
import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Client;
import com.challenge.zap.zappropertieslist.data.model.PropertyDetail;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailActivity;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;

/**
 * Created by eliete on 9/1/16.
 */
public class PropertyDetailActivityTest {

    @Mock
    DataRepository repository;

    @Mock
    PropertyDetailActivity propDetailActivity;

    @Captor
    ArgumentCaptor<DataRepository.getDetailOnFinishedListener>
            getDetailOnFinishedListenerArgumentCaptor;

    @Mock
    PropertyDetailPresenter mainPresenter;

    String [] characArray = {"salao", "elevador"};
    List<String> characList = Arrays.asList(characArray);
    PropertyDetail propertyDetail = new PropertyDetail(1, "apto", 205, 4, 3,2580000,
            new Address("street", "45", "city", "neighboor", "complement"),
            2, new Client("eliete", 45), characList, 457, 456, "text", "text", "offer", "12/12/4568");



    @Before
    public void setupPropertyPresenter(){
        MockitoAnnotations.initMocks(this);
        mainPresenter = new PropertyDetailPresenter(propDetailActivity, repository);
    }

    @Test
    public void loadPropertyDetailFromDataRepository_shouldLoadIntoView(){
        mainPresenter.fetchPropertyDetail(1);
        Mockito.verify(repository).getDetailProperty(getDetailOnFinishedListenerArgumentCaptor.capture(), eq(1));
        getDetailOnFinishedListenerArgumentCaptor.getValue().onFinishedDetail(propertyDetail);
        Mockito.verify(propDetailActivity).hideProgress();
        Mockito.verify(propDetailActivity).showDetailContainer(true);
        Mockito.verify(propDetailActivity).showCharacteristics(propertyDetail.getCharacteristicsList());
        Mockito.verify(propDetailActivity).showClientInformation(propertyDetail.getClient());
        Mockito.verify(propDetailActivity).showCondominiumValue(propertyDetail.getCondominiumPrice());
        Mockito.verify(propDetailActivity).showIptuValue(propertyDetail.getIptu());
        Mockito.verify(propDetailActivity).showPrice(propertyDetail.getPrice());
        Mockito.verify(propDetailActivity).showAddress(propertyDetail.getAddress());
        Mockito.verify(propDetailActivity).showDetailProperty(propertyDetail.getArea(),
                propertyDetail.getDormitory(), propertyDetail.getParking(), propertyDetail.getSuites());
        Mockito.verify(propDetailActivity).showObservation(propertyDetail.getObservation());
        Mockito.verify(propDetailActivity).showInformation(propertyDetail.getInformation());
        Mockito.verify(propDetailActivity).showDate(propertyDetail.getDate());
        Mockito.verify(propDetailActivity).showSale(propertyDetail.getOffer());
    }

    @Test
    public void failLoadPropertyFromDataProperty_shouldLoadErrorMessage(){
        mainPresenter.fetchPropertyDetail(1);
        Mockito.verify(repository).getDetailProperty(getDetailOnFinishedListenerArgumentCaptor.capture(), eq(1));
        getDetailOnFinishedListenerArgumentCaptor.getValue().onFinishedDetail(null);
        Mockito.verify(propDetailActivity).showNoDetail();
    }



}
