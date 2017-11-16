package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class first extends AppCompatActivity implements View.OnClickListener {

    Button stering;
    Button spare;
    Button about;
    Button exit;
    Button laptop;
    Button warcaby;
    Button stering_info;
    Button warcaby_info;
    Button laptop_info;
    Button spare_info;
    /*public Button steering;
    public Button exit;*/

    /*public void init(){
        steering = (Button)findViewById(R.id.steering);
        exit = (Button)findViewById(R.id.exit);
        steering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(first.this, second.class);
                startActivity(toy);
            }
        });
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //init();
        laptop = (Button) findViewById(R.id.laptop);
        laptop.setOnClickListener((View.OnClickListener) this);
        warcaby = (Button) findViewById(R.id.warcaby);
        warcaby.setOnClickListener((View.OnClickListener) this);
        stering = (Button) findViewById(R.id.stering);
        stering.setOnClickListener((View.OnClickListener) this);
        spare = (Button) findViewById(R.id.spare);
        spare.setOnClickListener((View.OnClickListener) this);
        laptop_info = (Button) findViewById(R.id.laptop_info);
        laptop_info.setOnClickListener((View.OnClickListener) this);
        warcaby_info = (Button) findViewById(R.id.warcaby_info);
        warcaby_info.setOnClickListener((View.OnClickListener) this);
        stering_info = (Button) findViewById(R.id.stering_info);
        stering_info.setOnClickListener((View.OnClickListener) this);
        spare_info = (Button) findViewById(R.id.spare_info);
        spare_info.setOnClickListener((View.OnClickListener) this);
        about = (Button) findViewById(R.id.about);
        about.setOnClickListener((View.OnClickListener) this);
        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener((View.OnClickListener) this);


    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.laptop:
                Intent toy0 = new Intent(first.this, SterLaptop.class);
                startActivity(toy0);
                break;
            case R.id.laptop_info:
                Intent toy1337 = new Intent(first.this, laptop_infoActivity.class);
                startActivity(toy1337);
                break;
            case R.id.warcaby:
                Intent toy88 = new Intent(first.this, SterWarcaby.class);
                startActivity(toy88);
                break;
            case R.id.warcaby_info:
                Intent toy888 = new Intent(first.this, warcaby_infoActivity.class);
                startActivity(toy888);
                break;
            case R.id.stering:
                Intent toy1 = new Intent(first.this, second.class);
                startActivity(toy1);
                break;
            case R.id.stering_info:
                Intent toy1227 = new Intent(first.this, stering_infoActivity.class);
                startActivity(toy1227);
                break;
            case R.id.about:
                Intent toy2 = new Intent(first.this, about.class);
                startActivity(toy2);
                break;
            case R.id.spare:
                Intent toy3 = new Intent(first.this, DeviceList.class);
                startActivity(toy3);
                break;
            case R.id.spare_info:
                Intent toy420 = new Intent(first.this, stering_infoActivity.class);
                startActivity(toy420);
                break;
            case R.id.exit:
                AppExit();
            default:
                break;
        }
    }
    public void AppExit()

    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
