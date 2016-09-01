package com.challenge.zap.zappropertieslist.data;

import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.data.model.PropertyDetail;

import java.util.List;

/**
 * Created by eliete on 8/31/16.
 */
public interface DataRepository {

    interface getListOnFinishedListener {
        void onFinishedList(List<Property> items);
    }

    void getPropertiesList(getListOnFinishedListener listener);

    interface getDetailOnFinishedListener {
        void onFinishedDetail(PropertyDetail property);
    }

    void getDetailProperty(getDetailOnFinishedListener listener, int codProperty);
}
