package com.challenge.zap.zappropertieslist.property;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.Utils;
import com.challenge.zap.zappropertieslist.data.model.Property;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliete on 8/31/16.
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private List<Property> propertyList;
    private Context context;
    private MainActivity.PropertyTouchListener touchListener;

    public PropertyAdapter(List<Property> list, MainActivity.PropertyTouchListener listener) {
        propertyList = list;
        touchListener = listener;
    }

    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view, touchListener);
    }

    @Override
    public void onBindViewHolder(PropertyAdapter.ViewHolder holder, int position) {
        Property property = getItem(position);
        if (property != null){
            holder.areatTextView.setText(context.getResources().getString(R.string.area, property.area));
            holder.typeTextView.setText(property.propertyType);
            holder.addressTextView.setText(context.getResources().getString(R.string.neighbor_city,
                    property.address.neighborhood, property.address.city));
            holder.priceTextView.setText(Utils.moneyMask(property.price, Utils.MONEY));

            Picasso.with(context)
                    .load(property.urlImage)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.propertyImageView);

        }
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public Property getItem(int position){
        return propertyList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.prop_image)
        ImageView propertyImageView;
        @BindView(R.id.area_text)
        TextView areatTextView;
        @BindView(R.id.type_text)
        TextView typeTextView;
        @BindView(R.id.address_text)
        TextView addressTextView;
        @BindView(R.id.price_text)
        TextView priceTextView;

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
