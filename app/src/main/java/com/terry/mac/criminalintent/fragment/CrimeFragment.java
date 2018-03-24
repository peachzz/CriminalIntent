package com.terry.mac.criminalintent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.terry.mac.criminalintent.CrimeLab;
import com.terry.mac.criminalintent.R;
import com.terry.mac.criminalintent.activity.CrimeActivity;
import com.terry.mac.criminalintent.bean.Crime;

import java.util.UUID;


/**
 * Created by mac on 2018/3/24.
 */

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mCrimeDate;
    private CheckBox mCrimeSolved;

    private static final String ARG_CRIME_ID = "crime_id";

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
//        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getCrimeLab(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mCrimeTitle = (EditText) view.findViewById(R.id.crime_title);
        mCrimeDate = (Button) view.findViewById(R.id.crime_date);
        mCrimeSolved = (CheckBox) view.findViewById(R.id.crime_solved);

        mCrimeTitle.setFocusable(true);
        mCrimeTitle.setText(mCrime.getTitle());
        mCrimeTitle.setSelection(mCrime.getTitle().length());
        mCrimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mCrimeDate.setText(mCrime.getDate());
        mCrimeDate.setEnabled(false);

        mCrimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });
        mCrimeSolved.setChecked(mCrime.isSolved());
        mCrimeTitle.setText(mCrime.getTitle());
        return view;
    }


}
