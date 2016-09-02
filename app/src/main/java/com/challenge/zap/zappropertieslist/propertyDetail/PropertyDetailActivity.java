package com.challenge.zap.zappropertieslist.propertyDetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.Utils;
import com.challenge.zap.zappropertieslist.data.DataRepositoryImpl;
import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Client;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;
import com.challenge.zap.zappropertieslist.property.MainActivity;
import com.challenge.zap.zappropertieslist.propertyAdd.AddPropertyDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eliete on 8/30/16.
 */
public class PropertyDetailActivity extends AppCompatActivity implements
        PropertyDetailContract.View, AddPropertyDialogFragment.SendMessage {

    public static final String TAG = PropertyDetailActivity.class.getSimpleName();
    public static final String EXTRA_FRAGMENT = "AddProperty";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.no_item)
    TextView noDetailTextView;
    @BindView(R.id.backdrop)
    ImageView backdropImageView;
    @BindView(R.id.cardview)
    CardView cardView;
    @BindView(R.id.send)
    FloatingActionButton sendButton;

    @BindView(R.id.money_image)
    ImageView moneyImageView;
    @BindView(R.id.sale)
    TextView priceTextView;
    @BindView(R.id.sale_value_text)
    TextView saleTextView;
    @BindView(R.id.condom_value_text)
    TextView condominiumTextView;
    @BindView(R.id.iptu_value_text)
    TextView iptuTextView;

    @BindView(R.id.location_image)
    ImageView locationImageView;
    @BindView(R.id.address)
    TextView addressTextView;
    @BindView(R.id.street_number_text)
    TextView streetNumberTextView;
    @BindView(R.id.complement_text)
    TextView complementTextView;
    @BindView(R.id.neighboor_city_text)
    TextView neighboorCityTextView;

    @BindView(R.id.star_image)
    ImageView detailImageView;
    @BindView(R.id.detail)
    TextView detailTextView;
    @BindView(R.id.dorm_text)
    TextView dormTextView;
    @BindView(R.id.suites_text)
    TextView suitesTextView;
    @BindView(R.id.park_text)
    TextView parkTextView;
    @BindView(R.id.area_text)
    TextView areaTextView;

    @BindView(R.id.bookmark_image)
    ImageView characImageView;
    @BindView(R.id.characteristics)
    TextView characTextView;
    @BindView(R.id.character_text)
    TextView characterTextView;

    @BindView(R.id.obs_image)
    ImageView obsImageView;
    @BindView(R.id.observation)
    TextView observationTextView;
    @BindView(R.id.obs_text)
    TextView obsTextView;

    @BindView(R.id.ads_image)
    ImageView adsImageView;
    @BindView(R.id.ads)
    TextView advertTextView;
    @BindView(R.id.ads_text)
    TextView adsTextView;

    @BindView(R.id.offer_image)
    ImageView offerImageView;
    @BindView(R.id.offer)
    TextView offerTextView;
    @BindView(R.id.offer_text)
    TextView ofTextView;

    @BindView(R.id.info_image)
    ImageView infoImageView;
    @BindView(R.id.info)
    TextView infoTextView;
    @BindView(R.id.info_text)
    TextView iTextView;

    @BindView(R.id.date_image)
    ImageView dateImageView;
    @BindView(R.id.date)
    TextView dateTextView;
    @BindView(R.id.date_text)
    TextView dtTextView;

    private int propertyCode;
    private String urlImage;
    private PropertyDetailContract.UserActionListener userActionListener;
    private int clientId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (getIntent() != null)
            urlImage = getIntent().getStringExtra(MainActivity.EXTRA_URL);
            propertyCode = getIntent().getIntExtra(MainActivity.EXTRA_CODE, 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        downloadImage(urlImage);

        userActionListener = new PropertyDetailPresenter(this, new DataRepositoryImpl());
        userActionListener.fetchPropertyDetail(propertyCode);

    }

    private void downloadImage(String urlImage) {
        Picasso.with(this)
                .load(urlImage)
                .placeholder(R.drawable.default_placeholder)
                .into(backdropImageView);
    }

    @OnClick(R.id.send) public void sendMessage(){
        FragmentManager fm = getSupportFragmentManager();
        AddPropertyDialogFragment fragment = AddPropertyDialogFragment.newInstance(clientId);
        fragment.show(fm, EXTRA_FRAGMENT);
    }

    @Override
    public void broadcastMessage(ZapMessage message) {
        userActionListener.sendMessage(message);
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
    public void showDetailContainer(boolean condition) {
        if (condition) {
            cardView.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.VISIBLE);
        }else {
            cardView.setVisibility(View.GONE);
            sendButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSnackBar(boolean condition) {
        String message;
        message = condition ? getResources().getString(R.string.message_success)
                : getResources().getString(R.string.message_success);
        Snackbar.make(this.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    @Override
    public void showClientInformation(Client client) {
        if (client != null) {
            clientId = client.getClientId();

            adsTextView.setVisibility(View.VISIBLE);
            adsTextView.setText(client.getName());

            adsImageView.setVisibility(View.VISIBLE);
            advertTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showPrice(int price) {
        saleTextView.setVisibility(View.VISIBLE);
        saleTextView.setText(getResources().getString(R.string.sale_price, Utils.moneyMask(price,
                                                                            Utils.MONEY)));
        moneyImageView.setVisibility(View.VISIBLE);
        priceTextView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showCondominiumValue(int value) {
        condominiumTextView.setVisibility(View.VISIBLE);
        condominiumTextView.setText(getResources().getString(R.string.cond_price, Utils.moneyMask(value,
                Utils.MONEY)));
    }

    @Override
    public void showIptuValue(int value) {
        iptuTextView.setVisibility(View.VISIBLE);
        iptuTextView.setText(getResources().getString(R.string.iptu_price, Utils.moneyMask(value,
                Utils.MONEY)));
    }

    @Override
    public void showAddress(Address address) {
        locationImageView.setVisibility(View.VISIBLE);
        addressTextView.setVisibility(View.VISIBLE);

        streetNumberTextView.setVisibility(View.VISIBLE);
        streetNumberTextView.setText(getResources().getString(R.string.street_number,
                address.getStreet() , address.getNumber()));
        if (!address.getComplement().isEmpty()){
            complementTextView.setVisibility(View.VISIBLE);
            complementTextView.setText(address.getComplement());
        }
        neighboorCityTextView.setVisibility(View.VISIBLE);
        neighboorCityTextView.setText(getResources().getString(R.string.neighbor_city,
                address.getNeighborhood() , address.getCity()));
    }

    @Override
    public void showDetailProperty(int area, int dorm, int park, int suite) {
        detailImageView.setVisibility(View.VISIBLE);
        detailTextView.setVisibility(View.VISIBLE);

        dormTextView.setVisibility(View.VISIBLE);
        dormTextView.setText(getResources().getString(R.string.dorm, dorm));

        areaTextView.setVisibility(View.VISIBLE);
        areaTextView.setText(getResources().getString(R.string.area, area));

        suitesTextView.setVisibility(View.VISIBLE);
        suitesTextView.setText(getResources().getString(R.string.suite, suite));

        parkTextView.setVisibility(View.VISIBLE);
        parkTextView.setText(getResources().getString(R.string.park, park));
    }

    @Override
    public void showObservation(String observ) {
        obsTextView.setVisibility(View.VISIBLE);
        obsTextView.setText(observ);

        obsImageView.setVisibility(View.VISIBLE);
        observationTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void showCharacteristics(List<String> list) {
        String charac = "";
        for(int i = 0; i < list.size(); i++){
            if ((i == 0 && list.size() == 1) || i == list.size() - 1){
                charac += list.get(i);
            }else {
                charac += list.get(i) + " , ";
            }
        }
        characterTextView.setVisibility(View.VISIBLE);
        characterTextView.setText(charac);

        characImageView.setVisibility(View.VISIBLE);
        characTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDate(String date) {
        dateImageView.setVisibility(View.VISIBLE);
        dateTextView.setVisibility(View.VISIBLE);

        dtTextView.setVisibility(View.VISIBLE);
        dtTextView.setText(Utils.formatDate(date));
    }

    @Override
    public void showInformation(String information) {

        infoImageView.setVisibility(View.VISIBLE);
        infoTextView.setVisibility(View.VISIBLE);

        iTextView.setVisibility(View.VISIBLE);
        iTextView.setText(information);
    }

    @Override
    public void showSale(String offer) {
        offerImageView.setVisibility(View.VISIBLE);
        offerTextView.setVisibility(View.VISIBLE);

        ofTextView.setVisibility(View.VISIBLE);
        ofTextView.setText(offer);

    }

    @Override
    public void showNoDetail() {
        noDetailTextView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MainActivity.EXTRA_CODE, propertyCode);
        outState.putString(MainActivity.EXTRA_URL, urlImage);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        propertyCode = savedInstanceState.getInt(MainActivity.EXTRA_CODE);
        urlImage = savedInstanceState.getString(MainActivity.EXTRA_URL);
    }
}
