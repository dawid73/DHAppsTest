package com.example.dawid.dhappstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MqttReciveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt_recive);
    }

    public void dupa(View v){
        Toast.makeText(MqttReciveActivity.this, "kliknieto guzik", Toast.LENGTH_LONG).show();
    }
}
