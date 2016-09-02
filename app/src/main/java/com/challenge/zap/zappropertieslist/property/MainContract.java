package com.challenge.zap.zappropertieslist.property;

import android.support.annotation.NonNull;

import com.challenge.zap.zappropertieslist.data.model.Property;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public interface MainContract {

    interface View{

        void showProgress();

        void hideProgress();

        void showPropertiesList(List<Property> list);

        void showPropertyDetailActivity(Property user);

        void showNoProperty();

        void showSortedList(List<Property> propertyList);

    }

    interface UserActionListener{

        void fetchPropertyList();

        void openItemDetails(@NonNull Property requestedProperty);

        void sort(List<Property> list, FilterPropertyDialogFragment.RadioEnun radioEnun);

    }
}
