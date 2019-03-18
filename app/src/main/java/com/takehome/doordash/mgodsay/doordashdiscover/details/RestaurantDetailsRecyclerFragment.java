package com.takehome.doordash.mgodsay.doordashdiscover.details;

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
import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;

public class RestaurantDetailsRecyclerFragment extends BaseFragment {

    private RecyclerView listView;
    private DetailsViewModel viewModel;
    private RestaurantDetailsAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView = view.findViewById(R.id.recycler_view_main);
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        Intent original = getActivity().getIntent();
        Bundle bundle = original.getExtras();
        String id = bundle.getString("RESTAURANT_ID");
        viewModel.setRestaurantId(id);
        adapter = new RestaurantDetailsAdapter(viewModel.imagesVisible);
        adapter.setHasStableIds(true);
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        //        getData();
        viewModel.getResponse().observe(this, this::onComponents);
    }

    private void onComponents(Restaurant response) {

        if(response != null && adapter.getItemCount() == 0)
        {
            adapter.add(response);
            listView.setVisibility(View.VISIBLE);
        }


    }
}
