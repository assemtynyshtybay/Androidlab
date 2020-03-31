package com.example.todoanddao;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            MainActivityFragment fragment = new MainActivityFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, fragment, MainActivityFragment.TAG).commit();
        }else{

            MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG);
            getSupportFragmentManager().beginTransaction().add(fragment,MainActivityFragment.TAG).commit();

        }


    }
}
