package com.example.fragmentmanager;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    interface IFirstFragmentCommChannel {
        void sendData(int num);
    }

    private IFirstFragmentCommChannel commChannel;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            commChannel = (IFirstFragmentCommChannel) context;
        } catch (ClassCastException e) {
            Log.e("onAttach", e.getMessage());
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        commChannel = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        final Button btnUpdate = root.findViewById(R.id.fragment_first__btn__update);
        btnUpdate.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragment_first__btn__update) {
            Log.i("FirstFragment", "button clicked");
            if (commChannel != null) {
                commChannel.sendData(new Random().nextInt(100));
            }
        }
    }
}
