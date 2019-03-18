package com.takehome.doordash.mgodsay.doordashdiscover.details;

import android.os.Bundle;

import com.takehome.doordash.mgodsay.doordashdiscover.R;
import com.takehome.doordash.mgodsay.doordashdiscover.base.BaseActivity;
import com.takehome.doordash.mgodsay.doordashdiscover.base.BaseFragment;

public class RestaurantDetailsActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.main_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // rather than finding the fragment we are depending on the saved instance state to
        // determine if the fragment has been created
        if (savedInstanceState == null)
        {
            BaseFragment fragment = new RestaurantDetailsRecyclerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

}
