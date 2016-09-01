package com.challenge.zap.zappropertieslist.propertyDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.Utils;
import com.challenge.zap.zappropertieslist.data.DataRepositoryImpl;
import com.challenge.zap.zappropertieslist.data.model.Address;
import com.challenge.zap.zappropertieslist.data.model.Client;
import com.challenge.zap.zappropertieslist.property.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eliete on 8/30/16.
 */
public class PropertyDetailActivity extends AppCompatActivity implements PropertyDetailContract.View {

    public static final String TAG = PropertyDetailActivity.class.getSimpleName();

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
    Button sendButton;

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
    ImageView obsImageView;
    @BindView(R.id.characteristics)
    TextView obsTextView;
    @BindView(R.id.character_text)
    TextView characterTextView;

    @BindView(R.id.info_image)
    ImageView infoImageView;
    @BindView(R.id.information)
    TextView iTextView;
    @BindView(R.id.info_text)
    TextView infoTextView;

    @BindView(R.id.ads_image)
    ImageView adsImageView;
    @BindView(R.id.ads)
    TextView advertTextView;
    @BindView(R.id.ads_text)
    TextView adsTextView;

    private int propertyCode;
    private String urlImage;

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

        PropertyDetailContract.UserActionListener userActionListener =
                new PropertyDetailPresenter(this, new DataRepositoryImpl());
        userActionListener.fetchPropertyDetail(propertyCode);

    }

    private void downloadImage(String urlImage) {
        Picasso.with(this)
                .load(urlImage)
                .placeholder(R.drawable.default_placeholder)
                .into(backdropImageView);
    }

    @OnClick(R.id.send) public void sendMessage(){
        Toast.makeText(this, "dfdlfkds", Toast.LENGTH_SHORT).show();
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
    public void showClientInformation(Client client) {
        adsTextView.setVisibility(View.VISIBLE);
        adsTextView.setText(client.getName());

        adsImageView.setVisibility(View.VISIBLE);
        advertTextView.setVisibility(View.VISIBLE);
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
        if (address.getComplement() != null){
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
    public void showInformation(String info) {
        infoTextView.setVisibility(View.VISIBLE);
        infoTextView.setText(info);

        infoImageView.setVisibility(View.VISIBLE);
        iTextView.setVisibility(View.VISIBLE);
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

        obsImageView.setVisibility(View.VISIBLE);
        obsTextView.setVisibility(View.VISIBLE);
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
