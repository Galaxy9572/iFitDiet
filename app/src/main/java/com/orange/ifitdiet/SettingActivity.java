package com.orange.ifitdiet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity{
    private int bState;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter ba;
    private static final long SCAN_PERIOD = 10000;
    private Handler handler;

    public SettingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settimg);
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        ba = bluetoothManager.getAdapter();
        final Switch sw_bluetooth = (Switch) findViewById(R.id.switch_bluetooth);
        bState = ba.getState();
        if (bState == BluetoothAdapter.STATE_ON) {
            sw_bluetooth.setChecked(true);
        } else if (bState == BluetoothAdapter.STATE_OFF) {
            sw_bluetooth.setChecked(false);
        }
        final CompoundButton.OnCheckedChangeListener bt_listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ba.enable();
                } else {
                    ba.disable();
                }
            }
        };
        sw_bluetooth.setOnCheckedChangeListener(bt_listener);

    }



}
