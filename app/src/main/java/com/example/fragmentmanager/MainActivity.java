package com.example.fragmentmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FirstFragment.IFirstFragmentCommChannel, View.OnClickListener {

    private SecondFragment f2;
    private int data;
    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDualPane = getResources().getBoolean(R.bool.dual_pane);

        //if (!getResources().getBoolean(R.bool.dual_pane)) {
        if (!isDualPane) {
            Button btnSwap = findViewById(R.id.activity_main__btn__swap);
            btnSwap.setOnClickListener(this);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.activity_main__fl__root, new FirstFragment(), "fragment-one");
            ft.commit();
        } else {
            try {
                this.f2 = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main__fr__second);
            } catch (ClassCastException e) {
                Log.e("onCreate", e.getMessage());
            }
        }
    }

    @Override
    public void sendData(int num) {
        //if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        //if (getResources().getBoolean(R.bool.dual_pane)) {
        if (isDualPane) {
            if (f2 != null) {
                f2.updateInfo(num);
            }
        } else {
            data = num;
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_main__btn__swap) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("first-fragment");

            String tag;

            if (fragment != null) {
                fragment = new SecondFragment();
                Bundle bundle = new Bundle();
                if (data != 0) {
                    bundle.putInt("data", data);
                }
                fragment.setArguments(bundle);
                tag = "second-fragment";

            } else {
                fragment = new FirstFragment();
                tag = "first-fragment";

            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main__fl__root, fragment, tag)
                    .commit();

        }
    }


}
