package com.example.fragmentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FirstFragment.IFirstFragmentCommChannel {

    private SecondFragment f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.f2 = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main__fr__second);
        } catch (ClassCastException e) {
            Log.e("onCreate", e.getMessage());
        }
    }

    @Override
    public void sendData(int num) {
        if (f2 != null) {
            f2.updateInfo(num);
        }
    }
}
