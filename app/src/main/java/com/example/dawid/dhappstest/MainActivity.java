package com.example.dawid.dhappstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void klikniecie(View view) {
        switch (view.getId()) {
            case R.id.mqtt_btn:
                intent = new Intent(MainActivity.this, MqttActivity.class);
                startActivity(intent);
                break;
        }
    }
}