package com.challenge.zap.zappropertieslist;

import com.challenge.zap.zappropertieslist.data.DataRepository;
import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.property.MainActivity;
import com.challenge.zap.zappropertieslist.property.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eliete on 8/29/16.
 */
public class MainActivityTest {

    @Mock
    DataRepository repository;

    @Mock
    MainActivity mainActivity;

    @Captor
    ArgumentCaptor<DataRepository.getListOnFinishedListener>
            getListOnFinishedListenerArgumentCaptor;

    @Mock
    MainPresenter mainPresenter;

    private static List<Property> propertyList = Arrays.asList(
            new Property(1, "apto", "78", 2, 2, "http://www.com.br", 120000, new Address()),
            new Property(1, "apto", "78", 2, 2, "http://www.com.br", 120000, new Address()),
            new Property(1, "apto", "78", 2, 2, "http://www.com.br", 120000, new Address()));

    @Before
    public void setupPropertyPresenter(){
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(mainActivity, repository);
    }

    @Test
    public void touchOnItem_shouldShowPropertyDetailActivity() {
        Property property = new Property(1, "apto", "78", 2, 2, "http://www.com.br", 120000, new Address());
        mainPresenter.openItemDetails(property);
        Mockito.verify(mainActivity).showPropertyDetailActivity(Mockito.any(Property.class));
    }

    @Test
    public void loadPropertyFromDataRepository_shouldLoadIntoView(){
        mainPresenter.fetchPropertyList();
        Mockito.verify(repository).getPropertiesList(getListOnFinishedListenerArgumentCaptor.capture());
        getListOnFinishedListenerArgumentCaptor.getValue().onFinishedList(propertyList);
        Mockito.verify(mainActivity).hideProgress();
        Mockito.verify(mainActivity).showPropertiesList(propertyList);
    }


    @Test
    public void failLoadPropertyFromDataProperty_shouldLoadErrorMessage(){
        mainPresenter.fetchPropertyList();
        Mockito.verify(repository).getPropertiesList(getListOnFinishedListenerArgumentCaptor.capture());
        getListOnFinishedListenerArgumentCaptor.getValue().onFinishedList(null);
        Mockito.verify(mainActivity).showNoProperty();
    }

}
