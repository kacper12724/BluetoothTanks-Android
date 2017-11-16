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

public class second extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener  {

    public Button ster_forward;
    public Button ster_backward;
    public Button ster_left;
    public Button ster_right;
    public Button ret_ster_main;
    public Button disconnect;
    public Button config;
    public TextView dir_left;
    public TextView dir_right;
    public TextView dir_forward;
    public TextView dir_backward;

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = bnull;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /*public void init(){
        ret_ster_main = (Button)findViewById(R.id.ret_ster_main);
        ret_ster_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(second.this, first.class);
                startActivity(toy);
            }
        });

    }*/

    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //init();
        disconnect = (Button)findViewById(R.id.disconnect);
        disconnect.setOnClickListener((View.OnClickListener) this);

        //Pola tekstowe wokol pojazdu
        dir_left = (TextView)findViewById(R.id.dir_left);
        dir_right = (TextView)findViewById(R.id.dir_right);
        dir_forward = (TextView)findViewById(R.id.dir_forward);
        dir_backward = (TextView)findViewById(R.id.dir_backward);

        //Przyciski sterowania
        ster_right = (Button)findViewById(R.id.ster_right);
        ster_right.setOnTouchListener((View.OnTouchListener) this);
        ster_left = (Button)findViewById(R.id.ster_left);
        ster_left.setOnTouchListener((View.OnTouchListener) this);
        ster_forward = (Button)findViewById(R.id.ster_forward);
        ster_forward.setOnTouchListener((View.OnTouchListener) this);
        ster_backward = (Button)findViewById(R.id.ster_backward);
        ster_backward.setOnTouchListener((View.OnTouchListener) this);
        /*ster_backward = (Button)findViewById(R.id.ster_backward);
        ster_backward.setOnClickListener((View.OnClickListener) this);*/
        ret_ster_main = (Button) findViewById(R.id.ret_ster_main);
        ret_ster_main.setOnClickListener((View.OnClickListener) this);

        config = (Button)findViewById(R.id.config);
        config.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_ster_main:
                finish();
                break;
            case R.id.config:
                Intent toy = new Intent(second.this, config.class);
                startActivity(toy);
                break;
            case R.id.disconnect:
                Disconnect();
            break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN ) {
            switch (v.getId()) {
                case R.id.ster_right:
                    dir_right.setTextColor(Color.RED);
                    break;
                case R.id.ster_left:
                    dir_left.setTextColor(Color.RED);
                    break;
                case R.id.ster_forward:
                    dir_forward.setTextColor(Color.RED);
                    break;
                case R.id.ster_backward:
                    dir_backward.setTextColor(Color.RED);
                    break;
                default:
                    break;
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP ) {
            switch (v.getId()) {
                case R.id.ster_right:
                    dir_right.setTextColor(Color.CYAN);
                    break;
                case R.id.ster_left:
                    dir_left.setTextColor(Color.CYAN);
                    break;
                case R.id.ster_forward:
                    dir_forward.setTextColor(Color.CYAN);
                    break;
                case R.id.ster_backward:
                    dir_backward.setTextColor(Color.CYAN);
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(second.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout
    }

    private void turnRight()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TR".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
}
