package com.terry.mac.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.terry.mac.criminalintent.CrimeLab;
import com.terry.mac.criminalintent.R;
import com.terry.mac.criminalintent.activity.CrimeActivity;
import com.terry.mac.criminalintent.bean.Crime;

import java.util.List;

/**
 * Created by mac on 2018/3/24.
 */

public class CrimeListFragment extends Fragment {

    private static final String TAG = "CrimeListFragment";
    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mCrimeAdapter;
    private int mIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecycleView = (RecyclerView) view.findViewById(R.id.crime_recycle_view);

        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecycleView.setAdapter(mCrimeAdapter);
        } else {
//            mCrimeAdapter.notifyDataSetChanged();
            mCrimeAdapter.notifyItemChanged(mIndex);
        }


    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCrimeTitle;
        private TextView mCrimeDate;
        private ImageView mCrimeSolved;


        private Crime mCrime;


        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            mCrimeTitle = (TextView) itemView.findViewById(R.id.crime_title);
            mCrimeDate = (TextView) itemView.findViewById(R.id.crime_date);
            mCrimeSolved = (ImageView) itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
        }

        public void bind(Crime crime) {
            this.mCrime = crime;
            mCrimeTitle.setText(mCrime.getTitle());
            mCrimeDate.setText(mCrime.getDate());
            mCrimeSolved.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            //            Toast.makeText(getActivity(), mCrime.getTitle()+"clicked!", Toast.LENGTH_SHORT).show();
            //            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            mIndex = getAdapterPosition();
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getMid());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimeList;

        public CrimeAdapter(List<Crime> crimes) {
            this.mCrimeList = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            holder.bind(mCrimeList.get(position));
        }

        @Override
        public int getItemCount() {
            return mCrimeList.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
