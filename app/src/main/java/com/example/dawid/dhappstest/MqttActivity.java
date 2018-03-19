package com.example.dawid.dhappstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;

public class MqttActivity extends AppCompatActivity {

    String mqqthost;
    String username;
    String password;
    String topicStr;

    MqttAndroidClient client;

    EditText wiadomosc;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);
        try {
            mqqthost = Helper.getProperty("mqtthost",getApplicationContext());
            username = Helper.getProperty("username",getApplicationContext());
            password = Helper.getProperty("password",getApplicationContext());
            topicStr = Helper.getProperty("topicStr",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        wiadomosc = (EditText) findViewById(R.id.wiadomosc_txt);
        textView = (TextView) findViewById(R.id.textView);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), mqqthost, clientId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        try {

            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MqttActivity.this, "polaczono", Toast.LENGTH_LONG).show();
                    wlaczSubskrypcje();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MqttActivity.this, "problem przy polaczeniu", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                textView.setText(new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }



    public void pub(View v){
        String topic = topicStr;
        String message = wiadomosc.getText().toString();
        try {
            client.publish(topic, message.getBytes(), 0 ,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void wlaczSubskrypcje(){
        try {
            client.subscribe(topicStr, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
