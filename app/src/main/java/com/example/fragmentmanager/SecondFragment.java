package com.example.fragmentmanager;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private TextView tvUpdateInfo;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        tvUpdateInfo = root.findViewById(R.id.fragment_second__tv__update_info);

        return root;
    }

    public void updateInfo(int num) {
        tvUpdateInfo.setText(String.valueOf(num));
    }
}
