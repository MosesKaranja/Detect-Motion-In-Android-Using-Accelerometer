package com.example.motionsensor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager = null;
    TextView textView1 = null;
    Button btnOtherActivity = null;
    List list;

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);


        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.i("sAccuracy", String.valueOf(accuracy));
            Log.i("sName",sensor.getName());
            Log.i("sVendor",sensor.getVendor());
            Log.i("sPower", String.valueOf(sensor.getPower()));
            Log.i("sId",String.valueOf(sensor.getId()));
            Log.i("sMaxRange", String.valueOf(sensor.getMaximumRange()));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView1 = findViewById(R.id.textViewData);
        btnOtherActivity = findViewById(R.id.buttonOtherActivity);

        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (list.size() > 0){
            sensorManager.registerListener(sensorEventListener,(Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);

        }
        else{
            Toast.makeText(getApplicationContext(), "Error: No Accelerometer", Toast.LENGTH_LONG).show();
        }

        btnOtherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), other_activity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (list.size() > 0){
            sensorManager.unregisterListener(sensorEventListener);

        }
    }
}