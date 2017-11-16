package com.example.pawel.aninterface;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.lang.Object;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class SterWarcaby extends AppCompatActivity implements View.OnClickListener{
    public Button ster_forward;
    public Button ster_backward;
    public Button ster_left;
    public Button ster_right;
    public Button ret_ster_warcaby_main;
    public Button click;
    public Button ster_forward_s;
    public Button ster_backward_s;
    public Button ster_left_s;
    public Button ster_right_s;
    public Button disconnect;
    public TextView out;
    String message;
    byte[] msgBuffer;

    public int i;
    private Handler mHandler;

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "18:CF:5E:B0:59:E2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ster_warcaby);

        ster_forward = (Button) findViewById(R.id.ster_forward);
        ster_forward.setOnClickListener((View.OnClickListener) this);
        ster_backward = (Button) findViewById(R.id.ster_backward);
        ster_backward.setOnClickListener((View.OnClickListener) this);
        ster_left = (Button) findViewById(R.id.ster_left);
        ster_left.setOnClickListener((View.OnClickListener) this);
        ster_right = (Button) findViewById(R.id.ster_right);
        ster_right.setOnClickListener((View.OnClickListener) this);
        click = (Button) findViewById(R.id.click);
        click.setOnClickListener((View.OnClickListener) this);
        ster_forward_s = (Button) findViewById(R.id.ster_forward_s);
        ster_forward_s.setOnClickListener((View.OnClickListener) this);
        ster_backward_s = (Button) findViewById(R.id.ster_backward_s);
        ster_backward_s.setOnClickListener((View.OnClickListener) this);
        ster_right_s = (Button) findViewById(R.id.ster_right_s);
        ster_right_s.setOnClickListener((View.OnClickListener) this);
        ster_left_s = (Button) findViewById(R.id.ster_left_s);
        ster_left_s.setOnClickListener((View.OnClickListener) this);




        ret_ster_warcaby_main = (Button) findViewById(R.id.ret_ster_warcaby_main);
        ret_ster_warcaby_main.setOnClickListener((View.OnClickListener) this);


        out = (TextView) findViewById(R.id.out);

        out.append("\n...In onCreate()...");

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        CheckBTState();

        // -
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            AlertBox("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        try {
            btSocket.connect();
            out.append("\n...Connection established and data link opened...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                AlertBox("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }
        // -

    }

    @Override
    public void onStart() {
        super.onStart();
        out.append("\n...In onStart()...");
    }


    @Override
    public void onStop() {
        super.onStop();
        out.append("\n...In onStop()...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        out.append("\n...In onDestroy()...");
    }

    private void CheckBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on

        // Emulator doesn't support Bluetooth and will return null
        if (btAdapter == null) {
            AlertBox("Fatal Error", "Bluetooth Not supported. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                out.append("\n...Bluetooth is enabled...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    public void AlertBox(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message + " Press OK to exit.")
                .setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ster_forward:
                send("FWD");
                break;
            case R.id.ster_backward:
                send("BWD");
                break;
            case R.id.ster_left:
                send("LFT");
                break;
            case R.id.ster_right:
                send("RGT");
                break;
            case R.id.click:
                send("CLK");
                break;
            case R.id.ster_forward_s:
                send("FWDs");
                break;
            case R.id.ster_backward_s:
                send("BWDs");
                break;
            case R.id.ster_left_s:
                send("LFTs");
                break;
            case R.id.ster_right_s:
                send("RGTs");
                break;
            case R.id.ret_ster_warcaby_main:
                finish();
                break;
            default:
                break;
        }
    }

    public void send(String param) {
        out.append("\n...In onResume...\n...Attempting client connect...");

        // Set up a pointer to the remote node using it's addres


        // Create a data stream so we can talk to server.
        out.append("\n...Sending message to server...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            AlertBox("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }

        message = param + "\n";
        msgBuffer = message.getBytes();
        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            if (address.equals("00:00:00:00:00:00"))
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            AlertBox("Fatal Error", msg);
        }
    }

}//finito
