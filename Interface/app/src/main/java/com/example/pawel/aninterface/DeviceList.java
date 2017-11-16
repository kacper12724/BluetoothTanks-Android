package com.example.pawel.aninterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.Set;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

public class DeviceList extends AppCompatActivity implements View.OnClickListener {
    Button btnPaired;
    ListView devicelist;
    Button ret_devl_main;
    private BluetoothAdapter myBluetooth = null;
    public static String EXTRA_ADDRESS = "device_address";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        ret_devl_main = (Button) findViewById(R.id.ret_devl_main);
        ret_devl_main.setOnClickListener((View.OnClickListener) this);
        btnPaired = (Button)findViewById(R.id.btnPaired);
        btnPaired.setOnClickListener((View.OnClickListener) this);
        devicelist = (ListView)findViewById(R.id.devicelist);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null)
        {
            //To sie wyswietli jak nie bedzie bluetooth'a na urzadzeniu
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

            finish();
        }
        else
        {
            if (myBluetooth.isEnabled())
            { }
            else
            {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ret_devl_main:
                finish();
                break;
            case R.id.btnPaired:
                pairedDevicesList();
                break;
            default:
                break;
        }
    }

    private void pairedDevicesList()
    {
        Set<BluetoothDevice> pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(DeviceList.this, second.class);
            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at second (class) Activity
            startActivity(i);
        }
    };
}
