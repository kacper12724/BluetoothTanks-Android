package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class warcaby_infoActivity extends AppCompatActivity implements View.OnClickListener {


    Button ret_aboutWarcaby_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warcaby_info);


        ret_aboutWarcaby_main = (Button) findViewById(R.id.ret_aboutWarcaby_main);
        ret_aboutWarcaby_main.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_aboutWarcaby_main:
                finish();
                break;
            default:
                break;
        }
    }

}
