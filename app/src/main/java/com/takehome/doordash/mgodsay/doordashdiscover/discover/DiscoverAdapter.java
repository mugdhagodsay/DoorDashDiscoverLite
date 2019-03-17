package com.takehome.doordash.mgodsay.doordashdiscover.discover;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takehome.doordash.mgodsay.doordashdiscover.databinding.RestaurantItemBinding;
import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder>
{
    private final List<Restaurant> restaurantItemList = new ArrayList<>();
    private final ObservableField<Boolean> imagesVisible;

    public DiscoverAdapter(ObservableField<Boolean> imagesVisible)
    {
        this.imagesVisible = imagesVisible;
    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RestaurantItemBinding binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setImagesVisible(imagesVisible);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position)
    {
        holder.bind(restaurantItemList.get(position));
    }

    void add(Restaurant restaurant) {
        restaurantItemList.add(restaurant);
        this.notifyItemInserted(restaurantItemList.size() - 1);
    }

    @Override
    public int getItemCount() {
        return restaurantItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RestaurantItemBinding binding;
        ViewHolder (RestaurantItemBinding binding) {
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
