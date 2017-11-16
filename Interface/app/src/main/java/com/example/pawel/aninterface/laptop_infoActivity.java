package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class laptop_infoActivity extends AppCompatActivity implements View.OnClickListener {

    Button ret_aboutLaptop_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_info);

        ret_aboutLaptop_main = (Button) findViewById(R.id.ret_aboutLaptop_main);
        ret_aboutLaptop_main.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_aboutLaptop_main:
                finish();
                break;
            default:
                break;
        }
    }

}
