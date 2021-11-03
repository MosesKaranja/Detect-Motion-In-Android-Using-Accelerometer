package com.example.motionsensor;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textViewShowStuff;
    private SensorManager sensorMan;
    private Sensor accelerometer;

    private float[] mGravity;
    private double mAccel;
    private double mAccelCurrent;
    private double mAccelLast;

    //private LogFragment mLogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewShowStuff = findViewById(R.id.textViewShowStuff);
        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        Log.i("mAccel", String.valueOf(mAccel));
        Log.i("mAccelCurrent", String.valueOf(mAccelCurrent));
        Log.i("mAccelLast", String.valueOf(mAccelLast));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.i("accMaxDelay", String.valueOf(accelerometer.getMaxDelay()));
        }
        Log.i("accMinDelay", String.valueOf(accelerometer.getMinDelay()));


        // Initialize the logging framework.
        //initializeLogging();
    }

    @Override
    public void onResume() {
        super.onResume();
//        sensorMan.registerListener(this, accelerometer,
//                SensorManager.SENSOR_DELAY_UI);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //This code below is aimed at decreasing the speed at which we get the sensor data to 1 second.
            //That sensor data is coming in at a crazily fast rate, am think this fast rate is likely to eat up the battery life.
            sensorMan.registerListener(this, accelerometer, 400000,400000);
        }
        else{
            //In the code below we simply get sensor data with its default input.
            sensorMan.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            double x = mGravity[0];
            double y = mGravity[1];
            double z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = Math.sqrt(x * x + y * y + z * z);
            double delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect
            String showStuff = "X value: "+x+"\n "+"Y value: "+y+"\n"+"Z value:"+z+"\n"+"Last Acc: "+mAccelLast+"\n"+"Curr Acc:"+mAccelCurrent+"\n"+"Ur Acc"+mAccel+"\n";

            textViewShowStuff.setText(showStuff);

            Log.i("mAccelInSensor", String.valueOf(mAccel));
            Log.i("mAccelCurrentInSensor", String.valueOf(mAccelCurrent));
            Log.i("mAccelLastInSensor", String.valueOf(mAccelLast));
            Log.i("----------------------","----------------------");
            Log.i("----------------------","----------------------");
            Log.i("----------------------","----------------------");
            Log.i("----------------------","----------------------");
            Log.i("----------------------","----------------------");
            Log.i("----------------------","----------------------");

            //The 1.0 below was 1.5
            if(Math.abs(mAccel) > 1.0){
                //Log.i(MainActivity.class.getName(), String.valueOf(mAccel));
                Log.i("Motion_Detected", String.valueOf(mAccel));
                // do something
                //Toast.makeText(getApplicationContext(), "MOvement Detected", Toast.LENGTH_SHORT).show();
                textViewShowStuff.setTextColor(Color.RED);
            }
            else{
                textViewShowStuff.setTextColor(Color.BLACK);

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method
    }



}

