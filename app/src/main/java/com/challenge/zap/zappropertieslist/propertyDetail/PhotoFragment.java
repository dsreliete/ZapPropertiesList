package com.challenge.zap.zappropertieslist.propertyDetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.challenge.zap.zappropertieslist.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliete on 9/5/16.
 */
public class PhotoFragment extends Fragment {

    @BindView(R.id.fragment_photo)
    ImageView photoFrag;
    private String url;


    public static PhotoFragment newInstance(String url){
        Bundle bundle = new Bundle();
        bundle.putString(PropertyDetailActivity.EXTRA_URL, url);
        PhotoFragment dialog = new PhotoFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, rootView);
        url = getArguments().getString(PropertyDetailActivity.EXTRA_URL);

        Picasso.with(getActivity())
                .load(url)
                .placeholder(R.drawable.default_placeholder)
                .into(photoFrag);

        return rootView;
    }


}
