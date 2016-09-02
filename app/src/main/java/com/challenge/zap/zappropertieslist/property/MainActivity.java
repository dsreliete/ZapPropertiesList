package com.challenge.zap.zappropertieslist.property;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.zap.zappropertieslist.AlertDialogFragment;
import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.data.DataRepositoryImpl;
import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends InternetDetectionActivity implements MainContract.View,
                                        FilterPropertyDialogFragment.FilterProperty{

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_CODE = "codeProperty";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_URL = "url";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.no_item)
    TextView noPropertyText;
    @BindView(R.id.filter)
    FloatingActionButton filterFAB;

    private MainContract.UserActionListener userActionListener;
    private List<Property> propertyList = new ArrayList<>();
    private PropertyAdapter adapter;
    private int lastVisiblePosition;
    private String urlImage;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        userActionListener = new MainPresenter(this, new DataRepositoryImpl());

        if (MainActivity.hasConnection(this)) {
            userActionListener.fetchPropertyList();
        } else {
            AlertDialogFragment alertDialogFragment =
                    AlertDialogFragment.newInstance(getResources().getString(R.string.network_msg));
            alertDialogFragment.show(getSupportFragmentManager(), "alert");
        }
        setupRecyclerView(lastVisiblePosition, propertyList);

    }

    @OnClick(R.id.filter) public void filterProperties(){
        FragmentManager fm = getSupportFragmentManager();
        FilterPropertyDialogFragment fragment = FilterPropertyDialogFragment.newInstance();
        fragment.show(fm, "filter");
    }

    private void setupRecyclerView(int lastPosition, List<Property> propertyList) {
        adapter = new PropertyAdapter(propertyList, repoTouchListener);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(lastPosition);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public static boolean hasConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            return networkInfo.isConnected();
        }else{
            return false;
        }
    }

    @Override
    public void initDownload() {
        userActionListener.fetchPropertyList();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPropertiesList(List<Property> list) {
        propertyList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoProperty() {
        recyclerView.setVisibility(View.GONE);
        noPropertyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSortedList(List<Property> propertyList) {
        setupRecyclerView(lastVisiblePosition, propertyList);
    }

    @Override
    public void showPropertyDetailActivity(Property property) {
        if (property != null) {
            Intent intent = new Intent(this, PropertyDetailActivity.class);
            intent.putExtra(EXTRA_CODE, property.getCodeProperty());
            intent.putExtra(EXTRA_URL, property.getUrlImage());
            startActivity(intent);
        }
    }

    @Override
    public void filter(FilterPropertyDialogFragment.RadioEnun radioEnun) {
        userActionListener.sort(propertyList, radioEnun);
    }

    public interface PropertyTouchListener {
        void onPropertyTouch(Property touchedRepo);
    }

    PropertyTouchListener repoTouchListener = new PropertyTouchListener() {
        @Override
        public void onPropertyTouch(Property touchedProperty) {
            userActionListener.openItemDetails(touchedProperty);
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_POSITION, layoutManager.findLastVisibleItemPosition());
        outState.putString(EXTRA_URL, urlImage);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastVisiblePosition = savedInstanceState.getInt(EXTRA_POSITION);
        urlImage = savedInstanceState.getString(EXTRA_URL);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setupRecyclerView(layoutManager.findLastVisibleItemPosition(), propertyList);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setupRecyclerView(layoutManager.findLastVisibleItemPosition(), propertyList);
        }
    }

}
