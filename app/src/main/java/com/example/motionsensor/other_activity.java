package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class other_activity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mLight;
    List<Sensor> deviceSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        for (int i=0;i<deviceSensors.size();i++){
            String sensor = i + " " + deviceSensors.get(i);
            Log.i("SensorLoop ", sensor);

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            //Success
            Log.i("success","TYPE_ACCELEROMETER Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_ACCELEROMETER Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED) != null){
            //Success
            Log.i("success","TYPE_ACCELEROMETER_UNCALIBRATED Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_ACCELEROMETER_UNCALIBRATED Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
            //Success
            Log.i("success","TYPE_LINEAR_ACCELERATION Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_LINEAR_ACCELERATION Sensor absent");

        }



        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            //Success
            Log.i("success","TYPE_GRAVITY Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_GRAVITY Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED) != null){
            //Success
            Log.i("success","TYPE_GYROSCOPE_UNCALIBRATED Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_GYROSCOPE_UNCALIBRATED Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            //Success
            Log.i("success","TYPE_GYROSCOPE Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_GYROSCOPE Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            //Success
            Log.i("success","TYPE_STEP_COUNTER Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_STEP_COUNTER Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null){
            //Success
            Log.i("success","TYPE_STEP_DETECTOR Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_STEP_DETECTOR Sensor absent");

        }


        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            //Success
            Log.i("success","Gravity Sensor Present");
        }
        else{
            //Failure
            Log.i("failure","Gravity Sensor absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            //Success
            Log.i("success","Magnenometer Present");
        }
        else{
            //Failure
            Log.i("failure","magnenometer absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
            //Success
            Log.i("success","TYPE_LINEAR_ACCELERATION Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_LINEAR_ACCELERATION absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null){
            //Success
            Log.i("success","TYPE_LINEAR_ACCELERATION Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_LINEAR_ACCELERATION absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION) != null){
            //Success
            Log.i("success","TYPE_SIGNIFICANT_MOTION Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_SIGNIFICANT_MOTION absent");

        }


        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null){
            //Success
            Log.i("success","TYPE_STEP_DETECTOR Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_STEP_DETECTOR absent");

        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            //Success
            Log.i("success","TYPE_STEP_COUNTER Present");
        }
        else{
            //Failure
            Log.i("failure","TYPE_STEP_COUNTER absent");

        }




    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //Light sensor returns a single value
        //Many sensors return 3 values, one for each axis
        float lux = event.values[0];
        Log.i("LightSensorV", String.valueOf(lux));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do something here if sensor accuracy changes

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);

    }
}