package com.example.pawel.aninterface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.bluetooth.BluetoothSocket;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.UUID;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class config extends AppCompatActivity implements View.OnClickListener {
    private SeekBar seekBar;
    private TextView underdog_l;
    private TextView underdog_r;
    private TextView textView1;
    public Button ret_conf_ster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView1 = (TextView) findViewById(R.id.textView1);
        underdog_l = (TextView) findViewById(R.id.underdog_l);
        underdog_r = (TextView) findViewById(R.id.underdog_r);

        ret_conf_ster = (Button)findViewById(R.id.ret_conf_ster);
        ret_conf_ster.setOnClickListener((View.OnClickListener) this);


        textView1.setText("Naped na PRZOD");
        underdog_l.setTextColor(Color.RED);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textView.setText("Covered: " + progress + "/" + seekBar.getMax());

                switch(progress){
                    case 0:
                        textView1.setText("Naped na PRZOD");
                        underdog_l.setTextColor(Color.RED);
                        underdog_r.setTextColor(Color.GREEN);
                        break;
                    case 1:
                        textView1.setText("Naped 4x4");
                        underdog_l.setTextColor(Color.RED);
                        underdog_r.setTextColor(Color.RED);
                        break;
                    case 2:
                        textView1.setText("Naped na TYL");
                        underdog_l.setTextColor(Color.GREEN);
                        underdog_r.setTextColor(Color.RED);
                        break;
                    default:
                        break;
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_conf_ster:
                finish();
                break;
            default:
                break;
        }

    }
}
