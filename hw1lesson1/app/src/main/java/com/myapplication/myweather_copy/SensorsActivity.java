package com.myapplication.myweather_copy;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class SensorsActivity extends Activity {

    TextView mSensTemp;
    TextView mSensHum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_layout);

        startSensors();
    }

    private void startSensors () {
        mSensTemp = findViewById(R.id.textViewSensorTemp);
        mSensHum = findViewById(R.id.textViewSensorHumid);

        final SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        Sensor sensorTemp = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Sensor sensorHumid = manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        if (sensorTemp == null)  mSensTemp.setText(R.string.tempSens);
        else {
            final SensorEventListener listenerTemp =  new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    mSensTemp.setText("Температура " + String.valueOf(sensorEvent.values[0]));
//                Log.i("SENSORS", "Temp = " + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            manager.registerListener(listenerTemp, sensorTemp,500);
        }

        if (sensorHumid == null)  mSensHum.setText("Датчик влажности отсутствует");
        else {
            SensorEventListener listenerHumid = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    mSensHum.setText("Влажность " + String.valueOf(sensorEvent.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            manager.registerListener(listenerHumid, sensorHumid,500);
        }
    }
}
