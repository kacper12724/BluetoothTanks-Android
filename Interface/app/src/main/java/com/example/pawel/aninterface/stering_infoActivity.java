package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class stering_infoActivity extends AppCompatActivity implements View.OnClickListener {

    Button ret_aboutStering_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stering_info);

        ret_aboutStering_main = (Button) findViewById(R.id.ret_aboutStering_main);
        ret_aboutStering_main.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_aboutStering_main:
                finish();
                break;
            default:
                break;
        }
    }


}
