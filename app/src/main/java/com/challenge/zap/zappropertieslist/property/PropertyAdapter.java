package com.challenge.zap.zappropertieslist.property;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.data.model.Property;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    List<Property> propertyList;
    Context context;
    private MainActivity.PropertyTouchListener touchListener;

    public PropertyAdapter(List<Property> list, MainActivity.PropertyTouchListener listener) {
        propertyList = list;
        touchListener = listener;

    }

    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view, touchListener);
    }

    @Override
    public void onBindViewHolder(PropertyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public Property getItem(int position){
        return propertyList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MainActivity.PropertyTouchListener touchListener;

        public ViewHolder(View itemView, MainActivity.PropertyTouchListener propTouchListener) {
            super(itemView);
            touchListener = propTouchListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Property property = getItem(position);
            touchListener.onPropertyTouch(property);
        }
    }
}
