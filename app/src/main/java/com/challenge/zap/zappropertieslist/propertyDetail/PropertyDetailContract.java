package com.challenge.zap.zappropertieslist.propertyDetail;

import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Client;

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

        void showInformation(String info);

        void showCharacteristics(List<String> list);

        void showDetailContainer(boolean condition);

    }

    interface UserActionListener{

        void fetchPropertyDetail(int codeProperty);

    }
}
