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
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class SterLaptop extends AppCompatActivity implements View.OnClickListener{
    public Button ster_forward;
    public Button ster_backward;
    public Button ster_left;
    public Button ster_right;
    public Button shoot;
    public Button ue;
    public Button weapon_switch;
    public Button armor_switch;
    public Button cruiser_switch;
    public Button ret_ster_laptop_main;
    public Button disconnect;
    public Button crosshair;
    public TextView dir_left;
    public TextView dir_right;
    public TextView dir_forward;
    public TextView dir_backward;
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
        setContentView(R.layout.activity_ster_laptop);

        ster_forward = (Button) findViewById(R.id.ster_forward);
        ster_backward = (Button) findViewById(R.id.ster_backward);
        ster_left = (Button) findViewById(R.id.ster_left);
        ster_right = (Button) findViewById(R.id.ster_right);
        shoot = (Button) findViewById(R.id.shoot);
        shoot.setOnClickListener((View.OnClickListener) this);
        ue = (Button) findViewById(R.id.ue);
        ue.setOnClickListener((View.OnClickListener) this);
        crosshair = (Button) findViewById(R.id.crosshair);
        crosshair.setOnClickListener((View.OnClickListener) this);
        armor_switch = (Button) findViewById(R.id.armor_switch);
        armor_switch.setOnClickListener((View.OnClickListener) this);
        cruiser_switch = (Button) findViewById(R.id.cruiser_switch);
        cruiser_switch.setOnClickListener((View.OnClickListener) this);
        weapon_switch = (Button) findViewById(R.id.weapon_switch);
        weapon_switch.setOnClickListener((View.OnClickListener) this);
        ret_ster_laptop_main = (Button) findViewById(R.id.ret_ster_laptop_main);
        ret_ster_laptop_main.setOnClickListener((View.OnClickListener) this);

        ster_right.setOnTouchListener(new View.OnTouchListener() {
            //private Handler mHandler;
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler() {
                            @Override
                            public void publish(LogRecord record) {
                            }
                            @Override
                            public void flush() {
                            }
                            @Override
                            public void close() throws SecurityException {
                            }
                        };
                        ster_right.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        ster_right.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("RGT");
                    ster_right.postDelayed(this, 100);
                }
            };
        });
        ster_left.setOnTouchListener(new View.OnTouchListener() {
            //private Handler mHandler;
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler() {
                            @Override
                            public void publish(LogRecord record) {
                            }
                            @Override
                            public void flush() {
                            }
                            @Override
                            public void close() throws SecurityException {
                            }
                        };
                        ster_left.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        ster_left.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("LFT");
                    ster_left.postDelayed(this, 100);
                }
            };
        });
        ster_forward.setOnTouchListener(new View.OnTouchListener() {
            //private Handler mHandler;
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler() {
                            @Override
                            public void publish(LogRecord record) {
                            }
                            @Override
                            public void flush() {
                            }
                            @Override
                            public void close() throws SecurityException {
                            }
                        };
                        ster_forward.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        ster_forward.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("FWD");
                    ster_forward.postDelayed(this, 100);
                }
            };
        });
        ster_backward.setOnTouchListener(new View.OnTouchListener() {
            //private Handler mHandler;
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler() {
                            @Override
                            public void publish(LogRecord record) {
                            }
                            @Override
                            public void flush() {
                            }
                            @Override
                            public void close() throws SecurityException {
                            }
                        };
                        ster_backward.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        ster_backward.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("BWD");
                    ster_backward.postDelayed(this, 100);
                }
            };
        });

        //Pola tekstowe wokol pojazdu
        dir_left = (TextView) findViewById(R.id.dir_left);
        dir_right = (TextView) findViewById(R.id.dir_right);
        dir_forward = (TextView) findViewById(R.id.dir_forward);
        dir_backward = (TextView) findViewById(R.id.dir_backward);

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
            case R.id.shoot:
                send("SHT");
                break;
            case R.id.armor_switch:
                send("ARMSW");
                break;
            case R.id.weapon_switch:
                send("WPNSW");
                break;
            case R.id.cruiser_switch:
                send("CRZSW");
                break;
            case R.id.ue:
                send("UE");
                break;
            case R.id.crosshair:
                send("CRSHR");
                break;
            case R.id.ret_ster_laptop_main:
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



    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN ) {
            switch (v.getId()) {
                case R.id.ster_right:
                    send("RGT");
                    break;
                default:
                    break;
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP ) {
            switch (v.getId()) {
                case R.id.ster_right:
                    send("RGT");
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
    }*/



}//finito
