package com.takehome.doordash.mgodsay.doordashdiscover.discover;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.takehome.doordash.mgodsay.doordashdiscover.R;
import com.takehome.doordash.mgodsay.doordashdiscover.base.BaseFragment;
import com.takehome.doordash.mgodsay.doordashdiscover.base.ItemClickListener;
import com.takehome.doordash.mgodsay.doordashdiscover.details.RestaurantDetailsActivity;
import com.takehome.doordash.mgodsay.doordashdiscover.model.BaseResponse;
import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;

import java.util.List;


public class DiscoverRecyclerFragment extends BaseFragment implements ItemClickListener {

    private RecyclerView listView;
    private DiscoverViewModel viewModel;
    private DiscoverAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView = view.findViewById(R.id.recycler_view_main);
        viewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        adapter = new DiscoverAdapter(viewModel.imagesVisible, this::onItemClick);
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        //        getData();
        viewModel.getResponse().observe(this, this::onComponents);

    }

    private void onComponents(List<Restaurant> response) {

        if (response != null) {
            for (Restaurant restaurant : response)
                adapter.add(restaurant);
        }

        listView.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onItemClick(BaseResponse dataModel) {
        if (dataModel instanceof Restaurant) {
            Intent intent = new Intent(getActivity(), RestaurantDetailsActivity.class);
            intent.putExtra("RESTAURANT_ID", ((Restaurant) dataModel).getId());
            getActivity().startActivity(intent);
        }
        return false;
    }
}
