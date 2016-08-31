package com.challenge.zap.zappropertieslist.property;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.zap.zappropertieslist.AlertDialogFragment;
import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.data.model.Property;
import com.challenge.zap.zappropertieslist.data.DataRepositoryImpl;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends InternetDetectionActivity implements MainContract.View{

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_CODE = "codeProperty";
    public static final String EXTRA_POSITION = "position";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.no_item)
    TextView noPropertyText;

    private MainContract.UserActionListener userActionListener;
    private List<Property> propertyList = new ArrayList<>();
    private PropertyAdapter adapter;
    private int lastVisiblePosition;
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
        setupRecyclerView(lastVisiblePosition);

    }

    private void setupRecyclerView(int lastPosition) {
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
    public void showPropertyDetailActivity(Property property) {
        if (property != null) {
            Intent intent = new Intent(this, PropertyDetailActivity.class);
            intent.putExtra(EXTRA_CODE, property.getCodImovel());
            startActivity(intent);
        }
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
        outState.putInt(MainActivity.EXTRA_POSITION, layoutManager.findLastVisibleItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastVisiblePosition = savedInstanceState.getInt(MainActivity.EXTRA_POSITION);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setupRecyclerView(layoutManager.findLastVisibleItemPosition());
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setupRecyclerView(layoutManager.findLastVisibleItemPosition());
        }
    }

}
