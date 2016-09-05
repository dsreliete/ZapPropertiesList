package com.challenge.zap.zappropertieslist.propertyDetail;

import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Client;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;

import java.util.List;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyDetailContract {

    interface View{

        void showProgress();

        void hideProgress();

        void showNoDetail();

        void showClientInformation(Client client);

        void showCondominiumValue(int value);

        void showIptuValue(int value);

        void showPrice(int price);

        void showAddress(Address address);

        void showDetailProperty(int area, int dorm, int park, int suite);

        void showObservation(String info);

        void showCharacteristics(List<String> list);

        void showDetailContainer(boolean condition);

        void showSnackBar(boolean condition);

        void showDate(String date);

        void showInformation(String information);

        void showSale(String offer);

        void showPhotos(List<String> list);
    }

    interface UserActionListener{

        void fetchPropertyDetail(int codeProperty);

        void sendMessage(ZapMessage message);

    }
}
