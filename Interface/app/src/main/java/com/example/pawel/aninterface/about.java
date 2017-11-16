package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about extends AppCompatActivity implements View.OnClickListener {
    public Button ret_about_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //init();
        ret_about_main = (Button) findViewById(R.id.ret_about_main);
        ret_about_main.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_about_main:
                finish();
                break;
            default:
                break;
        }
    }
}
