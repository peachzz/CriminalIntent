package com.terry.mac.criminalintent.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.terry.mac.criminalintent.fragment.CrimeListFragment;

/**
 * Created by mac on 2018/3/24.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
