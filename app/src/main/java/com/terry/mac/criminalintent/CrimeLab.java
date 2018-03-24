package com.terry.mac.criminalintent;

import android.content.Context;

import com.terry.mac.criminalintent.bean.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mac on 2018/3/24.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes
                ) {
            if (crime.getMid().equals(id)) {
                return crime;
            }
        }
        return null;
    }


}
