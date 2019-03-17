package com.takehome.doordash.mgodsay.doordashdiscover.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.takehome.doordash.mgodsay.doordashdiscover.R;
import com.takehome.doordash.mgodsay.doordashdiscover.base.BaseActivity;
import com.takehome.doordash.mgodsay.doordashdiscover.base.BaseFragment;

public class MainActivity extends BaseActivity {

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
            BaseFragment fragment = new DiscoverRecyclerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
