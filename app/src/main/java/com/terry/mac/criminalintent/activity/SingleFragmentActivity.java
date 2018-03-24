package com.terry.mac.criminalintent.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.terry.mac.criminalintent.R;

/**
 * Created by mac on 2018/3/24.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentById = fm.findFragmentById(R.id.fragment_container);
        if (fragmentById == null) {
            fragmentById = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragmentById)
                    .commit();
        }
    }
}
