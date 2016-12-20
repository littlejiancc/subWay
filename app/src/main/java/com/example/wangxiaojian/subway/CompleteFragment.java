package com.example.wangxiaojian.subway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */

public class CompleteFragment extends Fragment {
    public CompleteFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.notget_tickets, container, false);
        return view;
    }
}
