package com.takehome.doordash.mgodsay.doordashdiscover.details;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takehome.doordash.mgodsay.doordashdiscover.databinding.RestaurantDetailsBinding;
import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsAdapter extends RecyclerView.Adapter<RestaurantDetailsAdapter.ViewHolder> {

    private final List<Restaurant> restaurantItemList = new ArrayList<>();
    private final ObservableField<Boolean> imagesVisible;

    public RestaurantDetailsAdapter(ObservableField<Boolean> imagesVisible)
    {
        this.imagesVisible = imagesVisible;
    }

    @NonNull
    @Override
    public RestaurantDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RestaurantDetailsBinding binding = RestaurantDetailsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setImagesVisible(imagesVisible);
        return new RestaurantDetailsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailsAdapter.ViewHolder holder, int position) {
        holder.bind(restaurantItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    void add(Restaurant restaurant) {
        restaurantItemList.add(restaurant);
        this.notifyItemInserted(restaurantItemList.size() - 1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RestaurantDetailsBinding binding;

        ViewHolder (RestaurantDetailsBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
        ViewHolder(View view) {
            super(view);
        }

        void bind(@NonNull Restaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }
}
