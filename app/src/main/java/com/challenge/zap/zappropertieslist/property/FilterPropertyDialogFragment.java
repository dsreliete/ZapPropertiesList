package com.challenge.zap.zappropertieslist.property;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.challenge.zap.zappropertieslist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliete on 9/1/16.
 */
public class FilterPropertyDialogFragment extends android.support.v4.app.DialogFragment{

    @BindView(R.id.sort_radio)
    RadioGroup sortRadioGroup;

    public static FilterPropertyDialogFragment newInstance(){
        FilterPropertyDialogFragment dialog = new FilterPropertyDialogFragment();
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle(R.string.filter);

        sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioEnun option = null;
                switch (checkedId){
                    case R.id.price_radio:
                        option = RadioEnun.PRICE;
                        break;
                    case R.id.dormitory_radio:
                        option = RadioEnun.DORMITORY;
                        break;
                    case R.id.park_radio:
                        option = RadioEnun.PARKING;
                        break;
                }
                FilterProperty listener = (FilterProperty) getActivity();
                listener.filter(option);
                dismiss();
            }
        });
    }

    public enum RadioEnun {
        PRICE, DORMITORY, PARKING
    }

    public interface FilterProperty {
        void filter(RadioEnun radioEnum);
    }

}
