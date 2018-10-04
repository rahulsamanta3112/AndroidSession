package com.example.rahul.broadcasts_headset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MusicIntentReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MusicIntentReceiver();
    }

    @Override
    public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    private class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Log.d(TAG, "Headset is unplugged");
                        Toast.makeText(MainActivity.this,"Headset is unplugged",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Log.d(TAG, "Headset is plugged");
                             Toast.makeText(MainActivity.this,"Headset is plugged",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Log.d(TAG, "I have no idea what the headset state is");
                        Toast.makeText(MainActivity.this,"I have no idea what the headset state is",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }
}

